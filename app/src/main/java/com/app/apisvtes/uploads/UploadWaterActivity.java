package com.app.apisvtes.uploads;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
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
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.adapter.UploadWaterBillAdapter;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.tapwater.MenuActivity;
import com.app.apisvtes.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.DistID;
import static com.app.apisvtes.conn.ClassLibs.VillID;
import static com.app.apisvtes.conn.ClassLibs.fCheck;

public class UploadWaterActivity extends BaseActivity {

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    private RecyclerView recyclerView;


    private ProgressBar pgsBar;
    private int k = 0;
    private Handler hdlr = new Handler();

    String  strACCOUNT="";
    String  strRENT="";
    String  RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,BILLNO,printer,CheckBill,BatchFile,VALIDATED,BPRINTED,First,moves;
    ImageView imgNoProduct;
    UploadWaterBillAdapter uploadWaterBillAdapter;

    TextView txtAddProdcut,txtLoadInfor, txtLoadInfor2,btn,txtView,icon_onlyss;

    FloatingActionButton txtAddhousehold;
    ImageView imgProduct, imgScanCode;
    String mediaPath, encodedImage = "N/A";
    ArrayAdapter<String> categoryAdapter, supplierAdapter, weightUnitAdapter,orderTypeAdapter;

    EditText  etxtprovice_id, etxtdistrict_id, etxtvillage_id, etxtben_hh_unit,etxtSearch;
    List<String> categoryNames,supplierNames;

    List<HashMap<String, String>> villageNames,productCategory2;

    ArrayAdapter<String> provAdapter,distAdapter,villAdapter,unitAdapter;
    List<String> provnames,distnames,villnames,unitnames;
    List<String> IdList = new ArrayList<>();
    //   List<HashMap<String, String>> ZONENAMEData;

    List<String> categoryNames2;
    String selectedDistID="";
    String selectedVillID="";

    List<HashMap<String, String>> productCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadwater);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ສົ່ງຂໍ້ມູນບິນແຈ້ງຄ່ານ້ຳ");

        pgsBar = (ProgressBar) findViewById(R.id.pBar);

        etxtprovice_id = findViewById(R.id.etxt_provice_id);
        etxtdistrict_id= findViewById(R.id.etxt_district_id);

        btn = findViewById(R.id.btnShow);
        txtView =  findViewById(R.id.tView);

        recyclerView = findViewById(R.id.product_recyclerview);
        imgNoProduct = findViewById(R.id.image_no_product);
        icon_onlyss = findViewById(R.id.icon_onlyss);



        categoryNames = new ArrayList<>();
        supplierNames = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(UploadWaterActivity.this);

        try {
            if (DistID!=""){
                databaseAccess.open();
                String strZoneName = databaseAccess.getZoneName(DistID.toString() );
                etxtprovice_id.setText(strZoneName);
            }

            if (VillID!=""){
                databaseAccess.open();
                String Khet_name = databaseAccess.getVillName(VillID.toString());
                etxtdistrict_id.setText(Khet_name);
            }


        } catch (Exception e) {

        }


        try {
            if (VillID!="") {
                List<HashMap<String, String>> waterData2;
                databaseAccess.open();
                waterData2 = databaseAccess.getAllWaterPending(VillID.toString());
                if (waterData2.size() <= 0) {
                    imgNoProduct.setImageResource(R.drawable.nvet);
                    icon_onlyss.setVisibility(View.VISIBLE);
                } else {
                    imgNoProduct.setVisibility(View.GONE);
                    icon_onlyss.setVisibility(View.GONE);
                    uploadWaterBillAdapter = new UploadWaterBillAdapter(UploadWaterActivity.this, waterData2);
                    recyclerView.setAdapter(uploadWaterBillAdapter);
                }
            }
            //  Toasty.success(UploadWaterActivity.this, tt , 0).show();
        }catch (Exception ex) {
            Toasty.error(UploadWaterActivity.this, ex.getMessage(), 0).show();

        }


        databaseAccess.open();
        productCategory = databaseAccess.getDistrict(Dist1);

        for (int i = 0; i < productCategory.size(); i++) {
            // Get the ID of selected Country
            categoryNames.add(productCategory.get(i).get("ZONENAME"));

        }


        etxtprovice_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etxtdistrict_id.setText("");
                provnames  = new ArrayList<>();

                databaseAccess.open();
                productCategory = databaseAccess.getDistrict(Dist1);


                for (int i=0;  i<productCategory.size();  i++) {

                    provnames.add(productCategory.get(i).get("ZONENAME"));
                }


                provAdapter   = new ArrayAdapter<String>(  UploadWaterActivity.this, android.R.layout.simple_list_item_1);
                provAdapter.addAll(provnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(UploadWaterActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText("ຂໍ້ມູນເມືອງ");
                dialog_title.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_button.setTextColor(Color.parseColor("#FFFFFF"));
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


                        for (int i = 0; i < productCategory.size(); i++) {
                            if (provnames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                prov_id = productCategory.get(i).get("ZONECODE");
                            }
                        }
                        DistID = prov_id;
                        selectedDistID  = prov_id;

                    }
                });
            }
        });

        etxtdistrict_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VillID="";

                distnames  = new ArrayList<>();
                databaseAccess.open();

                //get data from local database
                final List<HashMap<String, String>> dist_name;

                if(!DistID.equals("")){
                    dist_name = databaseAccess.getVill(DistID);
                }else{
                    dist_name = databaseAccess.getVill("00");
                }

                for (int i=0;  i<dist_name.size();  i++) {

                    distnames.add(dist_name.get(i).get("Khet_NameLao"));
                }


                distAdapter   = new ArrayAdapter<String>(UploadWaterActivity.this, android.R.layout.simple_list_item_1);
                distAdapter.addAll(distnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(UploadWaterActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);


                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);



                dialog_title.setText("ຂໍ້ມູນບ້ານ");
                dialog_title.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_button.setTextColor(Color.parseColor("#FFFFFF"));

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
                                dist_id = dist_name.get(i).get("Khet_ID");
                            }
                        }
                        VillID = dist_id;
                        try {
                            List<HashMap<String, String>> waterData2;
                            databaseAccess.open();
                            waterData2 = databaseAccess.getAllWaterPending(VillID.toString());

                            //    String  tt= Integer.toString(waterData2.size());


                            if (waterData2.size() <= 0) {
                                imgNoProduct.setImageResource(R.drawable.nvet);
                                icon_onlyss.setVisibility(View.VISIBLE);
                            } else {
                                imgNoProduct.setVisibility(View.GONE);
                                icon_onlyss.setVisibility(View.GONE);
                                uploadWaterBillAdapter = new UploadWaterBillAdapter(UploadWaterActivity.this, waterData2);
                                recyclerView.setAdapter(uploadWaterBillAdapter);
                            }
                            //   Toasty.success(UploadWaterActivity.this, tt , 0).show();
                        }catch (Exception ex) {
                            Toasty.error(UploadWaterActivity.this, ex.getMessage(), 0).show();

                        }

                        /////=====

                        //  Log.d("dist_id", dist_id);
                    }
                });
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=0;
                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                if(isconnected){
                    int prstt =0;
                    List<HashMap<String, String>> productCategory,waterData;
                    databaseAccess.open();
                    waterData= databaseAccess.getAllWaterUpload(VillID.toString());
                    k = pgsBar.getProgress();
                    prstt=waterData.size();
                    pgsBar.setMax(waterData.size());
                    int finalPrstt = prstt;
                    new Thread(new Runnable() {
                        @SuppressLint("SuspiciousIndentation")
                        public void run() {
                            for (int i = 0; i < waterData.size(); i++) {
                                try {
                                    k += 1;
                                    strRENT = waterData.get(i).get("RENT");
                                    strACCOUNT = waterData.get(i).get("ACCOUNT");
                                    RENT= waterData.get(i).get("RENT");
                                    ACCOUNT= waterData.get(i).get("ACCOUNT");
                                    Tcode= waterData.get(i).get("Tcode");
                                    NAME= waterData.get(i).get("NAME");
                                    RATRID= waterData.get(i).get("RATRID");
                                    PREVREAD= waterData.get(i).get("PREVREAD");
                                    PREVDATE= waterData.get(i).get("PREVDATE");
                                    PRESREAD= waterData.get(i).get("PRESREAD");
                                    PRESDATE= waterData.get(i).get("PRESDATE");
                                    Consumption= waterData.get(i).get("Consumption");
                                    Deviation= waterData.get(i).get("Deviation");
                                    Detive= waterData.get(i).get("Detive");
                                    waterBill= waterData.get(i).get("waterBill");
                                    Mrent= waterData.get(i).get("Mrent");
                                    Sewage= waterData.get(i).get("Sewage");
                                    Tax= waterData.get(i).get("Tax");
                                    Surcharge= waterData.get(i).get("Surcharge");
                                    Total_Bill= waterData.get(i).get("Total_Bill");
                                    TOTALDUE1= waterData.get(i).get("TOTALDUE1");
                                    KhetID= waterData.get(i).get("KhetID");
                                    AREACODE= waterData.get(i).get("AREACODE");
                                    ZONE= waterData.get(i).get("ZONE");
                                    TOTALDUE= waterData.get(i).get("TOTALDUE");
                                    Arrears= waterData.get(i).get("Arrears");
                                    Paid_date= waterData.get(i).get("Paid_date");
                                    ConNew= waterData.get(i).get("ConNew");
                                    MTRNUMBER= waterData.get(i).get("MTRNUMBER");
                                    MTRWIDTH= waterData.get(i).get("MTRWIDTH");
                                    AVGCONSUM= waterData.get(i).get("AVGCONSUM");
                                    Latitude= waterData.get(i).get("Latitude");
                                    Longitude= waterData.get(i).get("Longitude");
                                    BILLNO= waterData.get(i).get("BILLNO");
                                    databaseAccess.open();
                                    databaseAccess.updateWaterSVS(PREVREAD, PRESDATE, PRESREAD, Deviation, Consumption, waterBill, Mrent, Sewage, Tax,Surcharge, Total_Bill, TOTALDUE1, Detive,  ConNew, Paid_date,  ACCOUNT, Latitude,Longitude);

                                    databaseAccess.open();
                                    databaseAccess.updateMASTER(PRESDATE, KhetID, AREACODE, ACCOUNT,BILLNO);
                                    databaseAccess.open();
                                    databaseAccess.updateMASTERS(KhetID, AREACODE, ACCOUNT,BILLNO);

                                    //test
                                    // databaseAccess.updateWaterSVS(PRESREAD,ACCOUNT);

                                    if (fCheck==1) {
                                        databaseAccess.open();
                                        databaseAccess.updateUploade(ACCOUNT,"0");
                                    }else{
                                        databaseAccess.open();
                                        databaseAccess.updateUploade(ACCOUNT,"2");
                                    }
                                    try{
                                        if(k<=pgsBar.getMax()){
                                            try{
                                                hdlr.post(new Runnable() {
                                                    public void run() {
                                                        pgsBar.setProgress(k);
                                                        txtView.setText(k+"/"+pgsBar.getMax());
                                                        if(k==pgsBar.getMax()){
                                                            txtView.setText("ອັບໂຫລດຂໍ້ມູນສຳເລັດ!");
                                                            Toasty.success(UploadWaterActivity.this, "ອັບໂຫລດຂໍ້ມູນສຳເລັດ!", 0).show();
                                                        }
                                                    }
                                                });

                                            } catch (Exception e) {
                                                //  e.printStackTrace();
                                            }

                                            try {
                                                Thread.sleep(finalPrstt);
                                            } catch (Exception e) {
                                                //  e.printStackTrace();
                                            }
                                        }
                                    }catch (Exception e){

                                    }

                                }catch (Exception e){
                                    //  Toasty.error(LoadWaterActivity.this, e.getMessage(), 0).show();

                                }
                            }

                        }


                    }).start();


                    //   Toasty.success(LoadWaterActivity.this, R.string.successfully_load, 0).show();


                }else{
                    Toasty.error(UploadWaterActivity.this,"ກະລຸນາເຊື່ອມຕໍອີນເຕີເນັດ",Toasty.LENGTH_SHORT).show();
                    //  finish();
                }

            }
        });


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
