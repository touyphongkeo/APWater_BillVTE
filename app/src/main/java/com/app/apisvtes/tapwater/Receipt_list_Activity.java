package com.app.apisvtes.tapwater;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.adapter.Receipt_list_dapter;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.utils.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.DistID;
import static com.app.apisvtes.conn.ClassLibs.VillID;
import static com.app.apisvtes.conn.ClassLibs.flatitude;
import static com.app.apisvtes.conn.ClassLibs.flongitude;

public class Receipt_list_Activity extends BaseActivity {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;

    protected boolean gps_enabled, network_enabled;
    private GpsTracker gpsTracker;

    private RecyclerView recyclerView;
    Receipt_list_dapter receipt_list_dapter;

    ImageView imgNoProduct,imgscancode;
    EditText etxtDistrict, etxtVillage, etxtSearch;

    // FloatingActionButton fabAdd;

    ArrayAdapter<String> categoryAdapter;
    List<String> categoryNames, categoryNames2;

    String selectedCategoryID, selectedCategoryID2, selectedWeightUnitID;
    //get data from local database
    List<HashMap<String, String>> productData, productCategory, productCategory2;
    List<HashMap<String, String>> searchExpenseList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_list);
        //  txtLat = (TextView) findViewById(R.id.tvLatitude);
        // tvLatitude = (TextView)findViewById(R.id.latitude);
        //  tvLongitude = (TextView)findViewById(R.id.longitude);


        //  fabAdd=findViewById(R.id.fab_add);
        etxtSearch=findViewById(R.id.etxt_search);
        etxtDistrict=findViewById(R.id.etxt_district);
        etxtVillage=findViewById(R.id.etxt_village);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ໃບຮັບເງີນຄ່ານ້ຳ");

        recyclerView = findViewById(R.id.product_recyclerview);
        imgNoProduct = findViewById(R.id.image_no_product);

        imgscancode = findViewById(R.id.img_scan_code);




        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        gpsTracker = new GpsTracker(Receipt_list_Activity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            flatitude = String.valueOf(latitude);
            flongitude =String.valueOf(longitude);
            //       txtLat.setText("lat:" + flatitude +" , long:"+flongitude);
        }else{
            gpsTracker.showSettingsAlert();
        }



        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        imgscancode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gpsTracker = new GpsTracker(Receipt_list_Activity.this);
                if(gpsTracker.canGetLocation()){
                    double latitude = gpsTracker.getLatitude();
                    double longitude = gpsTracker.getLongitude();
                    flatitude = String.valueOf(latitude);
                    flongitude =String.valueOf(longitude);
                    //  txtLat.setText("lat:" + flatitude +" , long:"+flongitude);
                    //   Toasty.info(ListWaterBillActivity.this, "lat:" + flatitude +" , long:"+flongitude, Toast.LENGTH_SHORT).show();
                }else{
                    gpsTracker.showSettingsAlert();
                }

                try{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Receipt_list_Activity.this);
                    databaseAccess.open();
                    //get data from local database
                    String lt =flatitude.substring(0, 6);
                    String lng =flongitude.substring(0, 6);
                    List<HashMap<String, String>> searchExpenseList2;
                    searchExpenseList2 = databaseAccess.searchListrecept_listsbbb(lt , lng,VillID);
                    //  Toasty.info(ListWaterBillActivity.this, fstr, Toast.LENGTH_SHORT).show();

                    if (searchExpenseList2.size() <= 0) {

                        recyclerView.setVisibility(View.GONE);
                        imgNoProduct.setVisibility(View.VISIBLE);
                        imgNoProduct.setImageResource(R.drawable.nvet);

                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        imgNoProduct.setVisibility(View.GONE);
                        receipt_list_dapter = new Receipt_list_dapter(Receipt_list_Activity.this, searchExpenseList2);
                        recyclerView.setAdapter(receipt_list_dapter);

                    }


                }catch (Exception e){

                }
            }
        });
        selectedCategoryID2="00";
        categoryNames = new ArrayList<>();
        categoryNames2 = new ArrayList<>();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Receipt_list_Activity.this);
        databaseAccess.open();
        try {
            productCategory = databaseAccess.getProductCategory();

            for (int i = 0; i < productCategory.size(); i++) {
                // Get the ID of selected Country
                categoryNames.add(productCategory.get(i).get("category_name"));

            }
        }catch (Exception e){

        }


        try {
            if (DistID!=""){
                databaseAccess.open();
                String strZoneName = databaseAccess.getZoneName(DistID.toString() );
                etxtDistrict.setText(strZoneName);

                selectedCategoryID = DistID;
                databaseAccess.open();
                productCategory2 = databaseAccess.getProductCategory2(selectedCategoryID.toString());

                categoryNames2.clear();
                for (int i = 0; i < productCategory2.size(); i++) {
                    // Get the ID of selected Country
                    categoryNames2.add(productCategory2.get(i).get("category_name"));

                }
            }

            if (VillID!=""){
                databaseAccess.open();
                String Khet_name = databaseAccess.getVillName(VillID.toString());
                etxtVillage.setText(Khet_name);

                selectedCategoryID2 = VillID;

              /*  productData = databaseAccess.getAllWater(selectedCategoryID2.toString());

                if (productData.size() <= 0) {
                    imgNoProduct.setImageResource(R.drawable.no_data);
                } else {
                    imgNoProduct.setVisibility(View.GONE);
                    listWaterBillAdapter = new ListWaterBillAdapter(ListWaterBillActivity.this, productData);
                    recyclerView.setAdapter(listWaterBillAdapter);
                }*/


            }


        } catch (Exception e) {

        }



        //   Toasty.info(this, productData.size(), Toast.LENGTH_SHORT).show();
        //need to open database in every query to get data from local db




        try {

            databaseAccess.open();
            productData = databaseAccess.searchListreceipsnnbb("",selectedCategoryID2.toString());


            if (productData.size() <= 0) {
                //  Toasty.info(this, R.string.no_data_found, Toast.LENGTH_SHORT).show();
                imgNoProduct.setImageResource(R.drawable.nvet);
            } else {
                imgNoProduct.setVisibility(View.GONE);
                receipt_list_dapter = new Receipt_list_dapter(Receipt_list_Activity.this, productData);
                recyclerView.setAdapter(receipt_list_dapter);
                // Toasty.info(this, R.string.Load_successfully, Toast.LENGTH_SHORT).show();
            }

        }catch (Exception ex) {
            Toasty.error(this, Objects.requireNonNull(ex.getMessage()), Toast.LENGTH_SHORT).show();
        }




        etxtDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryAdapter = new ArrayAdapter<String>(Receipt_list_Activity.this, android.R.layout.simple_list_item_1);
                categoryAdapter.addAll(categoryNames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Receipt_list_Activity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText("ສາຂາ/ໜ່ວຍ");
                dialog_title.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_button.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_list.setVerticalScrollBarEnabled(true);
                dialog_list.setAdapter(categoryAdapter);

                dialog_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        categoryAdapter.getFilter().filter(charSequence);
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
                        final String selectedItem = categoryAdapter.getItem(position);

                        String category_id = "0";
                        etxtDistrict.setText(selectedItem);


                        for (int i = 0; i < categoryNames.size(); i++) {
                            if (categoryNames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                category_id = productCategory.get(i).get("category_id");
                            }
                        }

                        DistID= category_id;
                        selectedCategoryID = category_id;

                        //get data from local database
                        databaseAccess.open();
                        productCategory2 = databaseAccess.getProductCategory2(selectedCategoryID.toString());

                        categoryNames2.clear();
                        for (int i = 0; i < productCategory2.size(); i++) {
                            // Get the ID of selected Country
                            categoryNames2.add(productCategory2.get(i).get("category_name"));

                        }


                        //  Log.d("category_id", category_id);
                    }
                });
            }
        });

        etxtVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryAdapter = new ArrayAdapter<String>(Receipt_list_Activity.this, android.R.layout.simple_list_item_1);
                categoryAdapter.addAll(categoryNames2);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Receipt_list_Activity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_list_search, null);
                dialog.setView(dialogView);
                dialog.setCancelable(false);

                Button dialog_button = dialogView.findViewById(R.id.dialog_button);
                EditText dialog_input = dialogView.findViewById(R.id.dialog_input);
                TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
                ListView dialog_list = dialogView.findViewById(R.id.dialog_list);


                dialog_title.setText("ເລືອກບ້ານ");
                dialog_title.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_button.setTextColor(Color.parseColor("#FFFFFF"));
                dialog_list.setVerticalScrollBarEnabled(true);
                dialog_list.setAdapter(categoryAdapter);

                dialog_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        categoryAdapter.getFilter().filter(charSequence);
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
                        final String selectedItem = categoryAdapter.getItem(position);

                        String category_id = "0";
                        etxtVillage.setText(selectedItem);


                        for (int i = 0; i < categoryNames2.size(); i++) {
                            if (categoryNames2.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                category_id = productCategory2.get(i).get("category_id");
                            }
                        }

                        VillID= category_id;
                        selectedCategoryID2 = category_id;

                        try {

                            databaseAccess.open();

                            if(selectedCategoryID2==""){
                                selectedCategoryID2="00";
                            }
                            if(productData.size()>0){
                                productData.clear();
                            }
                            productData = databaseAccess.getAllWaterSSbbvv(selectedCategoryID2.toString());

                            if (productData.size() <= 0) {
                                imgNoProduct.setImageResource(R.drawable.nvet);
                            } else {
                                imgNoProduct.setVisibility(View.GONE);
                                receipt_list_dapter = new Receipt_list_dapter(Receipt_list_Activity.this, productData);
                                recyclerView.setAdapter(receipt_list_dapter);
                            }

                        }catch (Exception ex) {

                        }


                    }
                });
            }
        });

        etxtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //  searchData(s.toString());
                try{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Receipt_list_Activity.this);
                    databaseAccess.open();
                    //get data from local database
                    searchExpenseList = databaseAccess.search_receipt_list_activityfff(s.toString());


                    if (searchExpenseList.size() <= 0) {

                        recyclerView.setVisibility(View.GONE);
                        imgNoProduct.setVisibility(View.VISIBLE);
                        imgNoProduct.setImageResource(R.drawable.nvet);

                    } else {

                        recyclerView.setVisibility(View.VISIBLE);
                        imgNoProduct.setVisibility(View.GONE);
                        receipt_list_dapter = new Receipt_list_dapter(Receipt_list_Activity.this, searchExpenseList);
                        recyclerView.setAdapter(receipt_list_dapter);

                    }


                }catch (Exception e){

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}