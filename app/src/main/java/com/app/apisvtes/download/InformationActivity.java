package com.app.apisvtes.download;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.adapter.LoadMemberAdapter;
import com.app.apisvtes.tapwater.MenuActivity;
import com.app.apisvtes.uploads.RequestHandler;
import com.app.apisvtes.conn.URLss;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.DistID;
import static com.app.apisvtes.conn.ClassLibs.Have_Download;
import static com.app.apisvtes.conn.ClassLibs.ProvID;
import static com.app.apisvtes.conn.ClassLibs.VillID;
import static com.app.apisvtes.conn.ClassLibs.lng;

public class InformationActivity extends BaseActivity {

    ProgressDialog loading;
    private RecyclerView recyclerView;
 String  strhh_code="";
    ImageView imgNoProduct;
    ProgressDialog progressDialog;


    TextView txtAddProdcut,txtLoadInfor, txtChooseImage;

    FloatingActionButton txtAddhousehold;
    ImageView imgProduct, imgScanCode;
    String mediaPath, encodedImage = "N/A";
     ArrayAdapter<String> categoryAdapter, supplierAdapter, weightUnitAdapter,orderTypeAdapter;

    EditText  etxtprovice_id, etxtdistrict_id, etxtvillage_id, etxtben_hh_unit,etxtSearch;
    List<String> categoryNames, supplierNames, weightUnitNames,orderTypeNames;


    ArrayAdapter<String> provAdapter,distAdapter,villAdapter,unitAdapter;
    List<String> provnames,distnames,villnames,unitnames;
    List<String> IdList = new ArrayList<>();

    String selectedVillID="";
    String selectedDistID="";
    String selectedProveID="";


    List<HashMap<String, String>> productCategory, productSupplier, weightUnit, orderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle(R.string.add_customer);


        recyclerView = findViewById(R.id.recycler_view);
        imgNoProduct = findViewById(R.id.image_no_product);

        etxtprovice_id = findViewById(R.id.etxt_provice_id);
        etxtdistrict_id= findViewById(R.id.etxt_district_id);
        etxtvillage_id = findViewById(R.id.etxt_village_id);

        txtLoadInfor= findViewById(R.id.txt_load_infor);

        ProgressDialog progressDialog = new ProgressDialog(  InformationActivity.this);

        categoryNames = new ArrayList<>();
        supplierNames = new ArrayList<>();
        weightUnitNames = new ArrayList<>();

        ProvID="";
        DistID="";
        VillID="";


        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        if(isconnected){


        }else{
          //  Toasty.success(InformationActivity.this,R.string.please_connect_to_internet,Toasty.LENGTH_SHORT).show();
            finish();
        }


       DatabaseAccess databaseAccess = DatabaseAccess.getInstance(InformationActivity.this);

        //need to open database in every query to get data from local db


        etxtprovice_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //get data from local database
                ProvID="";
                DistID="";
                VillID="";

                etxtdistrict_id.setText("");
                etxtvillage_id.setText("");

                provnames  = new ArrayList<>();
                databaseAccess.open();

                //get data from local database
                final List<HashMap<String, String>> prov_name;

                prov_name = databaseAccess.getProductCategory();


                for (int i=0;  i<prov_name.size();  i++) {

                    // Get the ID of selected Country
                    if(lng.equals("la")){
                        provnames.add(prov_name.get(i).get("provname"));
                    }else{
                        provnames.add(prov_name.get(i).get("provnameeng"));
                    }


                }


                provAdapter   = new ArrayAdapter<String>(  InformationActivity.this, android.R.layout.simple_list_item_1);
                provAdapter.addAll(provnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(InformationActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText(R.string.add_customer);
                dialog_list.setVerticalScrollBarEnabled(true);
                dialog_list.setAdapter(provAdapter);

                dialog_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        provAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


                final AlertDialog alertDialog = dialog.create();

                dialog_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        alertDialog.dismiss();
                        final String selectedItem = provAdapter.getItem(position);

                        String prov_id = "0";

                        etxtprovice_id.setText(selectedItem);


                        for (int i = 0; i < provnames.size(); i++) {
                            if (provnames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                prov_id = prov_name.get(i).get("provid");
                            }
                        }
                        ProvID = prov_id;
                        selectedProveID  = prov_id;
                        /////=====

                        Log.d("prov_id", prov_id);
                    }
                });
            }
        });

        etxtdistrict_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DistID="";
                VillID="";

                etxtvillage_id.setText("");

                //get data from local database

                distnames  = new ArrayList<>();
                databaseAccess.open();

                //get data from local database
                final List<HashMap<String, String>> dist_name;

                if(!ProvID.equals("")){
                    dist_name = databaseAccess.getAllWater(ProvID);
                }else{
                    dist_name = databaseAccess.getAllWater("00");
                }



                for (int i=0;  i<dist_name.size();  i++) {

                    // Get the ID of selected Country
                    if(lng.equals("la")){
                        distnames.add(dist_name.get(i).get("distnamelao"));
                    }else{
                        distnames.add(dist_name.get(i).get("distnameeng"));
                    }


                }


                distAdapter   = new ArrayAdapter<String>(InformationActivity.this, android.R.layout.simple_list_item_1);
                distAdapter.addAll(distnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(InformationActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText(R.string.add_customer);
                dialog_list.setVerticalScrollBarEnabled(true);
                dialog_list.setAdapter(distAdapter);

                dialog_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        distAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


                final AlertDialog alertDialog = dialog.create();

                dialog_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        alertDialog.dismiss();
                        final String selectedItem = distAdapter.getItem(position);

                        String dist_id = "0";

                        etxtdistrict_id.setText(selectedItem);


                        for (int i = 0; i < distnames.size(); i++) {
                            if (distnames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                dist_id = dist_name.get(i).get("distid");
                            }
                        }
                        DistID = dist_id;
                        selectedDistID  = dist_id;
                        /////=====

                        Log.d("dist_id", dist_id);
                    }
                });
            }
        });

        etxtvillage_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                VillID="";

                //get data from local database

                villnames  = new ArrayList<>();
                databaseAccess.open();

                //get data from local database
                final List<HashMap<String, String>> vill_name;

                if(!DistID.equals("")){
                    vill_name = databaseAccess.getAllWater(DistID);
                }else{
                    vill_name = databaseAccess.getAllWater("0000");
                }



                for (int i=0;  i<vill_name.size();  i++) {

                    // Get the ID of selected Country
                    if(lng.equals("la")){
                        villnames.add(vill_name.get(i).get("villname"));
                    }else{
                        villnames.add(vill_name.get(i).get("villnameeng"));
                    }


                }


                villAdapter   = new ArrayAdapter<String>(InformationActivity.this, android.R.layout.simple_list_item_1);
                villAdapter.addAll(villnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(InformationActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText(R.string.add_customer);
                dialog_list.setVerticalScrollBarEnabled(true);
                dialog_list.setAdapter(villAdapter);

                dialog_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        villAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


                final AlertDialog alertDialog = dialog.create();

                dialog_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        alertDialog.dismiss();
                        final String selectedItem = villAdapter.getItem(position);

                        String vill_id = "0";

                        etxtvillage_id.setText(selectedItem);


                        for (int i = 0; i < villnames.size(); i++) {
                            if (villnames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                vill_id = vill_name.get(i).get("villid");
                            }
                        }
                        VillID = vill_id;
                        selectedVillID  = vill_id;
                        /////=====

                        Log.d("vill_id", vill_id);
                    }
                });
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        recyclerView.setHasFixedSize(true);
        imgNoProduct.setImageResource(R.drawable.no_data);

        txtLoadInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    viewinfor();
                }catch (Exception e){

                }

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_customer_menu, menu);
        return true;
    }*/


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_export_customer) {


            folderChooser();

            return true;
        }

        else if (id == android.R.id.home) {
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }*/



    /*public void folderChooser() {
        new ChooserDialog(InformationActivitys.this)

                .displayPath(true)
                .withFilter(true, false)

                // to handle the result(s)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        onExport(path);
                        Log.d("path",path);

                    }
                })
                .build()
                .show();
    }*/


    /*public void onExport(String path) {

        String directory_path = path;
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // Export SQLite DB as EXCEL FILE
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DatabaseOpenHelper.DATABASE_NAME, directory_path);
        sqliteToExcel.exportSingleTable("customers", "customers.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

                loading = new ProgressDialog(InformationActivitys.this);
                loading.setMessage(getString(R.string.data_exporting_please_wait));
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            public void onCompleted(String filePath) {

                Handler mHand = new Handler();
                mHand.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        loading.dismiss();
                        Toasty.success(InformationActivitys.this, R.string.data_successfully_exported, Toast.LENGTH_SHORT).show();



                    }
                }, 5000);

            }

            @Override
            public void onError(Exception e) {

                loading.dismiss();
                Toasty.error(InformationActivitys.this, R.string.data_export_fail, Toast.LENGTH_SHORT).show();

                Log.d("Error",e.toString());
            }
        });
    }*/

    private void viewinfor() {
        //first getting the values
        final String provice_id = etxtprovice_id.getText().toString();
        final String district_id = etxtdistrict_id.getText().toString();
        final String village_id = etxtvillage_id.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(provice_id)) {
            etxtprovice_id.setError(getString(R.string.please_change_order_status_to_complete_or_cancel));
            etxtprovice_id.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(district_id)) {
            etxtdistrict_id.setError(getString(R.string.admob_application_id));
            etxtdistrict_id.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(village_id)) {
            etxtvillage_id.setError(getString(R.string.add_customer));
            etxtvillage_id.requestFocus();
            return;
        }
        //if everything is fine

        @SuppressLint("StaticFieldLeak")
        class ViewInfor extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            String JSonResult;
            final Integer cnt=0;
            List<Information> inforList;
            List<HashMap<String, String>> searchInformationList;
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(InformationActivity.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
             //   progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //    progressBar.setVisibility(View.VISIBLE);
                progressDialog = new ProgressDialog(InformationActivity.this);
                progressDialog.setMessage((getString(R.string.add_category))); // Setting Message
                progressDialog.setTitle((getString(R.string.label_year))); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
             //   progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);


                    //if no error in response
                    if (!obj.getBoolean("error"))
                    {
                      //  progressDialog.dismiss();
                        Toasty.success(InformationActivity.this, obj.getString("infor"), 0).show();


                        String hh_code, hh_head_name, total_hh_members, total_hh_members_female, province_id, district_id, village_id,  unit,  hh_num,   hh_level,  PCCons,  Status_Complete_CCT;
                    //    String strhh_code="";
                        String  hh_member_id,hh_code2,village,unit2,hh_num2,hh_level2,pp_id,hh_first_last_name,hhmarital_status,hh_relation_to_head,hh_gender_hh,hh_birthdate,
                                hh_total_age_cal,hh_ethnic_origin,hh_pregnant_woman,first_name,member_surname,contact_number,phone_type,type_card_id,id_card_no,date_issue,
                                date_expired,Issued_by,img_filename,img_url,Status_Join_CTT, Status_member;
                        String Note,SupportingDocumentNo,SupportingDocumentType,SupportingDocumentFilename_image,SupportingDocumentUrl_image,SupportingDocument_DateIssue,SupportingDocument_DateExpire,SupportingDocument_IssueBy,hh_member_id_ontablet;

                        JSonResult=obj.getString("infor");

                        if(JSonResult!=null)
                        {
                            JSONArray jsonArray = null;

                            try {
                                jsonArray = new JSONArray(JSonResult);

                                JSONObject jsonObject;

                                for(int i=0; i<jsonArray.length(); i++)
                                {

                                  Boolean bl =false;
                                   jsonObject = jsonArray.getJSONObject(i);

                                   hh_code = jsonObject.getString("hh_code");
                                   hh_head_name = jsonObject.getString("hh_head_name");
                                   total_hh_members = jsonObject.getString("total_hh_members");
                                   total_hh_members_female = jsonObject.getString("total_hh_members_female");
                                   province_id = jsonObject.getString("province_id");
                                   district_id = jsonObject.getString("district_id");
                                   village_id = jsonObject.getString("village_id");
                                   unit = jsonObject.getString("unit");
                                   hh_num = jsonObject.getString("hh_num");
                                   hh_level = jsonObject.getString("hh_level");
                                   PCCons = jsonObject.getString("PCCons");
                                   Status_Complete_CCT = jsonObject.getString("Status_Complete_CCT");

                                   //===
                                    hh_member_id= jsonObject.getString("hh_member_id");
                                    pp_id= jsonObject.getString("pp_id");
                                    hh_first_last_name= jsonObject.getString("hh_first_last_name");
                                    hhmarital_status= jsonObject.getString("hhmarital_status");
                                    hh_relation_to_head= jsonObject.getString("hh_relation_to_head");
                                    hh_gender_hh= jsonObject.getString("hh_gender_hh");
                                    hh_birthdate= jsonObject.getString("hh_birthdate");
                                    hh_total_age_cal= jsonObject.getString("hh_total_age_cal");
                                    hh_ethnic_origin= jsonObject.getString("hh_ethnic_origin");
                                    hh_pregnant_woman= jsonObject.getString("hh_pregnant_woman");
                                    Status_member= jsonObject.getString("Status_member");

                                    Note= jsonObject.getString("Note");
                                    SupportingDocumentNo= jsonObject.getString("SupportingDocumentNo");
                                    SupportingDocumentType= jsonObject.getString("SupportingDocumentType");
                                    SupportingDocumentFilename_image= jsonObject.getString("SupportingDocumentFilename_image");
                                    SupportingDocumentUrl_image= jsonObject.getString("SupportingDocumentUrl_image");
                                    SupportingDocument_DateIssue= jsonObject.getString("SupportingDocument_DateIssue");
                                    SupportingDocument_DateExpire= jsonObject.getString("SupportingDocument_DateExpire");
                                    SupportingDocument_IssueBy= jsonObject.getString("SupportingDocument_IssueBy");
                                    hh_member_id_ontablet= jsonObject.getString("Status_member");

                                    if(jsonObject.getString("Note")!="null")
                                    {
                                        Note= jsonObject.getString("Note");
                                    }else{
                                        Note="";
                                    }

                                    if(jsonObject.getString("SupportingDocumentNo")!="null")
                                    {
                                        SupportingDocumentNo= jsonObject.getString("SupportingDocumentNo");
                                    }else{
                                        SupportingDocumentNo="";
                                    }

                                    if(jsonObject.getString("SupportingDocumentType")!="null")
                                    {
                                        SupportingDocumentType= jsonObject.getString("SupportingDocumentType");
                                    }else{
                                        SupportingDocumentType="";
                                    }
                                    if(jsonObject.getString("SupportingDocumentFilename_image")!="null")
                                    {
                                        SupportingDocumentFilename_image= jsonObject.getString("SupportingDocumentFilename_image");
                                    }else{
                                        SupportingDocumentFilename_image="";
                                    }
                                    if(jsonObject.getString("SupportingDocumentUrl_image")!="null")
                                    {
                                        SupportingDocumentUrl_image= jsonObject.getString("SupportingDocumentUrl_image");
                                    }else{
                                        SupportingDocumentUrl_image="";
                                    }
                                    if(jsonObject.getString("SupportingDocument_DateIssue")!="null")
                                    {
                                        SupportingDocument_DateIssue= jsonObject.getString("SupportingDocument_DateIssue");
                                    }else{
                                        SupportingDocument_DateIssue="";
                                    }

                                    if(jsonObject.getString("SupportingDocument_DateExpire")!="null")
                                    {
                                        SupportingDocument_DateExpire= jsonObject.getString("SupportingDocument_DateExpire");
                                    }else{
                                        SupportingDocument_DateExpire="";
                                    }
                                    if(jsonObject.getString("SupportingDocument_IssueBy")!="null")
                                    {
                                        SupportingDocument_IssueBy= jsonObject.getString("SupportingDocument_IssueBy");
                                    }else{
                                        SupportingDocument_IssueBy="";
                                    }
                                    if(jsonObject.getString("hh_member_id_ontablet")!="null")
                                    {
                                        hh_member_id_ontablet= jsonObject.getString("hh_member_id_ontablet");
                                    }else{
                                        hh_member_id_ontablet="";
                                    }



                                    if(jsonObject.getString("first_name")!="null")
                                    {
                                        first_name= jsonObject.getString("first_name");
                                    }else{
                                        first_name="";
                                    }
                                    if(jsonObject.getString("member_surname")!="null"){
                                        member_surname= jsonObject.getString("member_surname");
                                    }else{
                                        member_surname="";
                                    }
                                    if(jsonObject.getString("contact_number")!="null"){
                                        contact_number= jsonObject.getString("contact_number");
                                    }else{
                                        contact_number="";
                                    }
                                    if(jsonObject.getString("phone_type")!="null"){
                                        phone_type= jsonObject.getString("phone_type");
                                    }else{
                                        phone_type="";
                                    }
                                    if(jsonObject.getString("type_card_id")!="null"){
                                        type_card_id= jsonObject.getString("type_card_id");
                                    }else{
                                        type_card_id="";
                                    }
                                    if(jsonObject.getString("id_card_no")!="null"){
                                        id_card_no= jsonObject.getString("id_card_no");
                                    }else{
                                        id_card_no="";
                                    }
                                    if(jsonObject.getString("date_issue")!="null"){
                                        date_issue= jsonObject.getString("date_issue");
                                    }else{
                                        date_issue="";
                                    }
                                    if(jsonObject.getString("date_expired")!="null"){
                                        date_expired= jsonObject.getString("date_expired");
                                    }else{
                                        date_expired="";
                                    }
                                    if(jsonObject.getString("Issued_by")!="null"){
                                        Issued_by= jsonObject.getString("Issued_by");
                                    }else{
                                        Issued_by="";
                                    }
                                    if(jsonObject.getString("img_filename")!="null"){
                                        img_filename= jsonObject.getString("img_filename");
                                    }else{
                                        img_filename="";
                                    }
                                    if(jsonObject.getString("img_url")!="null"){
                                        img_url= jsonObject.getString("img_url");
                                    }else{
                                        img_url="";
                                    }
                                    if(jsonObject.getString("Status_Join_CTT")!="null"){
                                        Status_Join_CTT= jsonObject.getString("Status_Join_CTT");
                                    }else{
                                        Status_Join_CTT="";
                                    }

                                    if(strhh_code.equals("")) {
                                        try{
                                            databaseAccess.open();
                                         //   databaseAccess.addInfor(hh_code, hh_head_name, total_hh_members, total_hh_members_female, province_id, district_id,  village_id,  unit,  hh_num,   hh_level,  PCCons,  Status_Complete_CCT);

                                        }catch (Exception e){
                                            Toasty.success(getApplicationContext(), Objects.requireNonNull(e.getMessage()), 0).show();
                                        }
                                        strhh_code=hh_code;
                                    }else{
                                        if  (!strhh_code.equals(hh_code)){
                                            try{
                                                databaseAccess.open();
                                           //     databaseAccess.addInfor(hh_code, hh_head_name, total_hh_members, total_hh_members_female, province_id, district_id,  village_id,  unit,  hh_num,   hh_level,  PCCons,  Status_Complete_CCT);

                                            }catch (Exception e){
                                                Toasty.error(getApplicationContext(), Objects.requireNonNull(e.getMessage()), 0).show();
                                            }
                                            strhh_code=hh_code;
                                     }

                            //Add Household
                                        try{
                                            databaseAccess.open();
                                          //  databaseAccess.addHousehold(hh_member_id,hh_code,province_id,district_id,village_id, unit, hh_num, hh_level,pp_id,hh_first_last_name,hhmarital_status,hh_relation_to_head,hh_gender_hh,hh_birthdate,hh_total_age_cal,hh_ethnic_origin,hh_pregnant_woman,first_name,member_surname,contact_number,phone_type,type_card_id,id_card_no,date_issue,date_expired,Issued_by,img_filename,img_url,Status_Join_CTT,Status_member, Note, SupportingDocumentNo,SupportingDocumentType, SupportingDocumentFilename_image, SupportingDocumentUrl_image, SupportingDocument_DateIssue, SupportingDocument_DateExpire, SupportingDocument_IssueBy, hh_member_id_ontablet);

                                        }catch (Exception e){
                                            Toasty.success(getApplicationContext(), Objects.requireNonNull(e.getMessage()), 0).show();
                                        }
                                   }

                                }

                                progressDialog.dismiss();
                                ShowMembers();
                               // Toasty.success(InformationActivitys.this, R.string.DonwloadSuccess, 0).show();
                            }
                            catch (JSONException e) {
                                progressDialog.dismiss();
                              //  Toasty.success(InformationActivitys.this, Objects.requireNonNull(e.getMessage()), 0).show();
                            }
                        }

                    } else {
                        // Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                       // Toasty.error(getApplicationContext(), R.string.Nodatatodownload, 0).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters

                HashMap<String, String> params = new HashMap<>();
                // Cuongnv sua ngay 29/12

                //ContentValues params = new ContentValues(); provice

                params.put("province_id", ProvID);
                params.put("district_id", DistID);
                params.put("village_id", VillID);

                //returing the response
                return requestHandler.sendPostRequest(URLss.URL_VIEW, params);
            }
        }

        ViewInfor ul = new ViewInfor();
        ul.execute();
    }


    private  void  ShowMembers(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(InformationActivity.this);
        databaseAccess.open();
        //get data from local database
        List<HashMap<String, String>> searchInformationList;

        List<HashMap<String, String>> enrollmemberData;

        try {
            String s="";

            //  Toasty.success(ViewEnrollmentActivity.this, rdGroupView.toString() , Toast.LENGTH_SHORT).show();

          enrollmemberData = databaseAccess.getLoadView();

           Log.d("data", "" + enrollmemberData.size());

            if (enrollmemberData.size() <= 0) {
                //   loading.dismiss();
            //    Toasty.error(InformationActivitys.this, R.string.no_product_found, Toast.LENGTH_SHORT).show();
                imgNoProduct.setImageResource(R.drawable.no_data);
            } else {

                //   loading.dismiss();
                //   imgNoProduct.setVisibility(View.GONE);

           //     Toasty.success(InformationActivitys.this,R.string.total_record + ftotal.toString(), Toast.LENGTH_SHORT).show();
                LoadMemberAdapter  LoadMemberAdapter = new LoadMemberAdapter(InformationActivity.this, enrollmemberData);
                Have_Download = "0";
                recyclerView.setAdapter(LoadMemberAdapter);

            }

        }catch (Exception e){
        //    Toasty.success(InformationActivitys.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //check internet
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                //this.finish();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refreshActivity() {
        Intent i = new Intent(this, MenuActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        refreshActivity();
        super.onBackPressed();
    }

}
