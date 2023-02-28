package com.app.apisvtes.download;

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

import com.app.apisvtes.R;
import com.app.apisvtes.adapter.ListWaterBillAdapter;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.tapwater.MenuActivity;
import com.app.apisvtes.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.VillID;
import static com.app.apisvtes.conn.ClassLibs.DistID;

public class LoadWaterActivity extends BaseActivity {

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    private ProgressBar pgsBar;
    private int k = 0;
    private Handler hdlr = new Handler();

    String  strACCOUNT="";
    String  strRENT="";
    String  RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO,moves,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,Pay,Arrears2;
    ImageView imgNoProduct;
    ListWaterBillAdapter listWaterBillAdapter;

    TextView txtAddProdcut,txtLoadInfor, txtLoadInfor2,btn,txtView;

    FloatingActionButton txtAddhousehold;
    ImageView imgProduct, imgScanCode;
    String mediaPath, encodedImage = "N/A";
    ArrayAdapter<String> categoryAdapter, supplierAdapter, weightUnitAdapter,orderTypeAdapter;

    EditText  etxtprovice_id, etxtdistrict_id, etxtvillage_id, etxtben_hh_unit,etxtSearch;
    List<String> categoryNames,categoryNames2, supplierNames, weightUnitNames,orderTypeNames;

    List<HashMap<String, String>> villageNames;

    ArrayAdapter<String> provAdapter,distAdapter,villAdapter,unitAdapter;
    List<String> provnames,distnames,villnames,unitnames;
    List<String> IdList = new ArrayList<>();
    //   List<HashMap<String, String>> ZONENAMEData;

    String selectedDistID="";
    String selectedVillID="";

    TextView text1sss,text_views,text1ss;


    List<HashMap<String, String>> productCategory,waterData,waterDatas,DATABTS,PAYMENT;

    //String master
    String MICODES,TCODES,BILLNOS,ACCOUNTS,NAMES,AREACODES,ZONES,RATRIDS,CCODES,HUNITS,CONADDR1,MAILADDR1,CUCODE,CNAME,CONADDR2,MAILADDR2,MEMONO,CONFEES,DEP_DT,CON_DT,SIZECODE,MTRWIDTHS,MTRNUMBERS,MTRMFGCODE,DMETHOD,LOCATION,ALTITUTE,LONGTITUTE,STATUS,MCODE,AVGCONSUMS,TOTALDUES,overpay,MOnno,PCAREA,COCODE,DMA,CmbAccount,chk,MUseDate,GroupBill,NPP,CallectID,BILLDATE,Cost1,Cost2,Cost3,Rem_Cost,Unit,Lst_Updt,Lst_Usr,PC_Nm,OLD_ACCOUNT,move,Cost4,CONTax,Page_Grp,MM,Pro_ID;

    //String bt
    String MICODE,TCODE,ACCOUNTT,BILLNOO,BILLDATEE,STATUSS,PREVREADD,PREVDATEE,PRESREADD,PRESDATEE,CONSUMP,Deviationn,WATERBILL,MRENT,TAX,Sewagee,Surchargee,Total_Billl,Arrearss,TOTALDUEE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetIDD,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODEE,RateID,Ram_Month,cnt,Detivee,DateBefore,Edit,CONNew,MTRMFGCODEE,GroupBilll,NPPP,STATU,Lst_Updtt,Lst_Usrr,PC_Nmm,callectid,DIS_Amt,Amt,Pro_IDD;

    //String payment
    String ETA,ACCOUNTTT,PAY_NO,Pay_MonthH,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,OverpayY,PSTATUS,Cmd,Bank,BILLNOOO,Location,BankNm,Lst_updt,Lst_usr,pc_nm,h,UserID,UserNm,Amt_letter,DD,VV,dr,Cr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadwater);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ດາວໂຫລດບິນແຈ້ງຄ່ານ້ຳ");

        pgsBar = (ProgressBar) findViewById(R.id.pBar);

        etxtprovice_id = findViewById(R.id.etxt_provice_id);
        etxtdistrict_id= findViewById(R.id.etxt_district_id);
        text1sss= findViewById(R.id.text1sss);
        text1ss= findViewById(R.id.text1ss);
        text_views= findViewById(R.id.text_views);


        btn = findViewById(R.id.btnShow);
        txtView =  findViewById(R.id.tView);


        categoryNames = new ArrayList<>();
        supplierNames = new ArrayList<>();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LoadWaterActivity.this);

        databaseAccess.open();
        productCategory = databaseAccess.getDistrict(Dist1);
     //   Toast.makeText(this, Dist1, Toast.LENGTH_SHORT).show();


        for (int i = 0; i < productCategory.size(); i++) {
            // Get the ID of selected Country
            categoryNames.add(productCategory.get(i).get("ZONENAME"));

        }


        etxtprovice_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //get data from local database
                DistID="";
                etxtdistrict_id.setText("");
                provnames  = new ArrayList<>();

                databaseAccess.open();
                productCategory = databaseAccess.getDistrict(Dist1);


                for (int i=0;  i<productCategory.size();  i++) {

                    provnames.add(productCategory.get(i).get("ZONENAME"));
                }


                provAdapter   = new ArrayAdapter<String>(  LoadWaterActivity.this, android.R.layout.simple_list_item_1);
                provAdapter.addAll(provnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoadWaterActivity.this);
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
                        text1sss.setVisibility(View.GONE);
                        etxtprovice_id.setError(null);



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


                distAdapter   = new ArrayAdapter<String>(LoadWaterActivity.this, android.R.layout.simple_list_item_1);
                distAdapter.addAll(distnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoadWaterActivity.this);
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
                        text1ss.setVisibility(View.GONE);
                        etxtdistrict_id.setError(null);


                        for (int i = 0; i < distnames.size(); i++) {
                            if (distnames.get(i).equalsIgnoreCase(selectedItem)) {
                                // Get the ID of selected Country
                                dist_id = dist_name.get(i).get("Khet_ID");
                            }
                        }
                        VillID = dist_id;
                        selectedVillID  = dist_id;
                        /////=====

                        //  Log.d("dist_id", dist_id);
                    }
                });
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String etxtprovice_ids = etxtprovice_id.getText().toString();
            String etxtdistrict_ids = etxtdistrict_id.getText().toString();
            if (TextUtils.isEmpty(etxtprovice_ids)){
                text1sss.setText("ກະລຸນາເລືອກເມືອງກອນ!");
                text1sss.setVisibility(View.VISIBLE);
                text1sss.setTextColor(Color.RED);
                etxtprovice_id.setError("ກະລຸນາເລືອກເມືອງ");
                etxtprovice_id.requestFocus();
            }else if (TextUtils.isEmpty(etxtdistrict_ids)) {
                text1ss.setText("ກະລຸນາເລືອກບ້ານກອນ!");
                text1ss.setVisibility(View.VISIBLE);
                text1ss.setTextColor(Color.RED);
                etxtdistrict_id.setError("ກະລຸນາເລືອກບ້ານກອນ!");
                etxtdistrict_id.requestFocus();
            }else {
                k=0;
                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                if(isconnected){
                    try {
                        databaseAccess.open();
                        databaseAccess.deleteWaterBill(VillID.toString());
                    }catch (Exception e){

                    }

                int prstt =0;


                databaseAccess.open();
            //    downloadMASTER(VillID.toString());

                databaseAccess.open();
                waterData= databaseAccess.getAllWaterLoadSV(VillID.toString());
                k = pgsBar.getProgress();
                prstt=waterData.size();
                pgsBar.setMax(waterData.size());
                int finalPrstt = prstt;
                new Thread(new Runnable() {
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
                                printer= waterData.get(i).get("printer");
                                BILLNO= waterData.get(i).get("BILLNO");
                                moves= waterData.get(i).get("move");
                                CheckBill= waterData.get(i).get("CheckBill");
                                VALIDATED= waterData.get(i).get("VALIDATED");
                                BPRINTED= waterData.get(i).get("BPRINTED");
                                First= waterData.get(i).get("First");
                                WALKSEQ= waterData.get(i).get("WALKSEQ");
                                Pay= waterData.get(i).get("pay");
                                Arrears2= waterData.get(i).get("Arrears2");

                                if (ZONE.equals("08") || ZONE.equals("10")){
                                   if (Pay.equals("1")){
                                        databaseAccess.open();
                                        boolean check = databaseAccess.addWaterBill(RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO,moves,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,Pay,Arrears2);
                                    }else {
                                        databaseAccess.open();
                                        boolean check = databaseAccess.addWaterBill2(RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO,moves,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,Arrears2);
                                    }
                                }else {
                                    if (Pay.equals("1")){
                                        databaseAccess.open();
                                        boolean check = databaseAccess.addWaterBillas(RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO,moves,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,Pay,Arrears2);

                                    }else {
                                        databaseAccess.open();
                                        boolean check = databaseAccess.addWaterBillas3(RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO,moves,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,Arrears2);

                                   }

                                }


                                try{
                                    if(k<=pgsBar.getMax()){
                                        try{
                                            hdlr.post(new Runnable() {
                                                public void run() {
                                                    pgsBar.setProgress(k);
                                                    txtView.setText(k+"/"+pgsBar.getMax());
                                                    if(k==pgsBar.getMax()){
                                                        txtView.setText("ດາວໂຫລດຂໍ້ມູນສຳເລັດ");
                                                        Intent intent = new Intent(LoadWaterActivity.this,MenuActivity.class);
                                                        startActivity(intent);
                                                        Toasty.success(LoadWaterActivity.this, "ດາວໂຫລດຂໍ້ມູນສຳເລັດ", 0).show();
                                                    }
                                                }

                                            });
                                }catch (Exception e) {
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
                    Toasty.error(LoadWaterActivity.this,R.string.please_connect_to_internet,Toasty.LENGTH_SHORT).show();
                  //  finish();
                }
            }
            }
        });
    }

    //GET DOWNLOAD MASTER
    private void downloadMASTER(String vill_id) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        waterDatas= databaseAccess.getLoadMASER(vill_id);
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < waterDatas.size(); i++) {
                    try {
                        MICODES = waterDatas.get(i).get("MICODE");
                        TCODES = waterDatas.get(i).get("TCODE");
                        BILLNOS = waterDatas.get(i).get("BILLNO");
                        ACCOUNTS = waterDatas.get(i).get("ACCOUNT");
                        NAMES = waterDatas.get(i).get("NAME");
                        AREACODES = waterDatas.get(i).get("AREACODE");
                        ZONES = waterDatas.get(i).get("ZONE");
                        RATRIDS = waterDatas.get(i).get("RATRID");
                        CCODES = waterDatas.get(i).get("CCODE");
                        HUNITS = waterDatas.get(i).get("HUNIT");
                        CONADDR1 = waterDatas.get(i).get("CONADDR1");
                        MAILADDR1 = waterDatas.get(i).get("MAILADDR1");
                        CUCODE = waterDatas.get(i).get("CUCODE");
                        CNAME = waterDatas.get(i).get("CNAME");
                        CONADDR2 = waterDatas.get(i).get("CONADDR2");
                        MAILADDR2 = waterDatas.get(i).get("MAILADDR2");
                        MEMONO = waterDatas.get(i).get("MEMONO");
                        CONFEES = waterDatas.get(i).get("CONFEES");
                        DEP_DT = waterDatas.get(i).get("DEP_DT");
                        CON_DT = waterDatas.get(i).get("CON_DT");
                        SIZECODE = waterDatas.get(i).get("SIZECODE");
                        MTRWIDTHS = waterDatas.get(i).get("MTRWIDTH");
                        MTRNUMBERS = waterDatas.get(i).get("MTRNUMBER");
                        MTRMFGCODE = waterDatas.get(i).get("MTRMFGCODE");
                        DMETHOD = waterDatas.get(i).get("DMETHOD");
                        LOCATION = waterDatas.get(i).get("LOCATION");
                        ALTITUTE = waterDatas.get(i).get("ALTITUTE");
                        LONGTITUTE = waterDatas.get(i).get("LONGTITUTE");
                        STATUS = waterDatas.get(i).get("STATUS");
                        WALKSEQ = waterDatas.get(i).get("WALKSEQ");
                        MCODE = waterDatas.get(i).get("MCODE");
                        AVGCONSUMS = waterDatas.get(i).get("AVGCONSUM");
                        TOTALDUES = waterDatas.get(i).get("TOTALDUE");
                        overpay = waterDatas.get(i).get("overpay");
                        MOnno = waterDatas.get(i).get("MOnno");
                        PCAREA = waterDatas.get(i).get("PCAREA");
                        COCODE = waterDatas.get(i).get("COCODE");
                        DMA = waterDatas.get(i).get("DMA");
                        CmbAccount = waterDatas.get(i).get("CmbAccount");
                        chk = waterDatas.get(i).get("chk");
                        MUseDate = waterDatas.get(i).get("MUseDate");
                        GroupBill = waterDatas.get(i).get("GroupBill");
                        NPP = waterDatas.get(i).get("NPP");
                        CallectID = waterDatas.get(i).get("CallectID");
                        BILLDATE = waterDatas.get(i).get("BILLDATE");
                        Cost1 = waterDatas.get(i).get("Cost1");
                        Cost2 = waterDatas.get(i).get("Cost2");
                        Cost3 = waterDatas.get(i).get("Cost3");
                        Rem_Cost = waterDatas.get(i).get("Rem_Cost");
                        Unit = waterDatas.get(i).get("Unit");
                        Lst_Updt = waterDatas.get(i).get("Lst_Updt");
                        Lst_Usr = waterDatas.get(i).get("Lst_Usr");
                        PC_Nm = waterDatas.get(i).get("PC_Nm");
                        OLD_ACCOUNT = waterDatas.get(i).get("OLD_ACCOUNT");
                        move = waterDatas.get(i).get("move");
                        Cost4 = waterDatas.get(i).get("Cost4");
                        CONTax = waterDatas.get(i).get("CONTax");
                        Page_Grp = waterDatas.get(i).get("Page_Grp");
                        MM = waterDatas.get(i).get("MM");
                        Pro_ID = waterDatas.get(i).get("Pro_ID");
                        databaseAccess.open();
                        boolean master = databaseAccess.addMASTERS(MICODES,TCODES,BILLNOS,ACCOUNTS,NAMES,AREACODES,ZONES,RATRIDS,CCODES,HUNITS,CONADDR1,MAILADDR1,CUCODE,CNAME,CONADDR2,MAILADDR2,MEMONO,CONFEES,DEP_DT,CON_DT,SIZECODE,MTRWIDTHS,MTRNUMBERS,MTRMFGCODE,DMETHOD,LOCATION,ALTITUTE,LONGTITUTE,STATUS,WALKSEQ,MCODE,AVGCONSUMS,TOTALDUES,overpay,MOnno,PCAREA,COCODE,DMA,CmbAccount,chk,MUseDate,GroupBill,NPP,CallectID,BILLDATE,Cost1,Cost2,Cost3,Rem_Cost,Unit,Lst_Updt,Lst_Usr,PC_Nm,OLD_ACCOUNT,move,Cost4,CONTax,Page_Grp,MM,Pro_ID);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }

    //GET DOWNLOAD BT
//    private void downloadBT(String s) {
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        DATABTS= databaseAccess.getLoadBT(s);
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < DATABTS.size(); i++) {
//                    try {
//                        MICODE = DATABTS.get(i).get("MICODE");
//                        TCODE = DATABTS.get(i).get("TCODE");
//                        ACCOUNTT = DATABTS.get(i).get("ACCOUNT");
//                        BILLNOO = DATABTS.get(i).get("BILLNO");
//                        BILLDATEE = DATABTS.get(i).get("BILLDATE");
//                        STATUSS = DATABTS.get(i).get("STATUS");
//                        PREVREADD = DATABTS.get(i).get("PREVREAD");
//                        PREVDATEE = DATABTS.get(i).get("PREVDATE");
//                        PRESREADD = DATABTS.get(i).get("PRESREAD");
//                        PRESDATEE = DATABTS.get(i).get("PREVDATE");
//                        CONSUMP = DATABTS.get(i).get("CONSUMP");
//                        Deviationn = DATABTS.get(i).get("Deviation");
//                        WATERBILL = DATABTS.get(i).get("WATERBILL");
//                        MRENT = DATABTS.get(i).get("MRENT");
//                        TAX = DATABTS.get(i).get("TAX");
//                        Sewagee = DATABTS.get(i).get("Sewage");
//                        Surchargee = DATABTS.get(i).get("Surcharge");
//                        Total_Billl = DATABTS.get(i).get("Total_Bill");
//                        Arrearss = DATABTS.get(i).get("Arrears");
//                        TOTALDUEE = DATABTS.get(i).get("TOTALDUE");
//                        AMOUNT = DATABTS.get(i).get("AMOUNT");
//                        PAYDATE = DATABTS.get(i).get("PAYDATE");
//                        Payarreas = DATABTS.get(i).get("Payarreas");
//                        Total = DATABTS.get(i).get("Total");
//                        Pay_Month = DATABTS.get(i).get("Pay_Month");
//                        Overpay = DATABTS.get(i).get("Overpay");
//                        Grand_total = DATABTS.get(i).get("Grand_total");
//                        KhetIDD = DATABTS.get(i).get("KhetID");
//                        areacode = DATABTS.get(i).get("areacode");
//                        Checks = DATABTS.get(i).get("Checks");
//                        Paid = DATABTS.get(i).get("Paid");
//                        R_Code = DATABTS.get(i).get("R_Code");
//                        Choose = DATABTS.get(i).get("Choose");
//                        ReadDateBill = DATABTS.get(i).get("ReadDateBill");
//                        CCODE = DATABTS.get(i).get("CCODE");
//                        SIZECODEE = DATABTS.get(i).get("SIZECODE");
//                        RateID = DATABTS.get(i).get("RateID");
//                        Ram_Month = DATABTS.get(i).get("Ram_Month");
//                        cnt = DATABTS.get(i).get("cnt");
//                        Detivee = DATABTS.get(i).get("Detive");
//                        DateBefore = DATABTS.get(i).get("DateBefore");
//                        Edit = DATABTS.get(i).get("Edit");
//                        CONNew = DATABTS.get(i).get("CONNew");
//                        MTRMFGCODEE = DATABTS.get(i).get("MTRMFGCODE");
//                        GroupBilll = DATABTS.get(i).get("GroupBill");
//                        NPPP = DATABTS.get(i).get("NPP");
//                        STATU = DATABTS.get(i).get("STATU");
//                        Lst_Updtt = DATABTS.get(i).get("Lst_Updt");
//                        Lst_Usrr = DATABTS.get(i).get("Lst_Usr");
//                        PC_Nmm = DATABTS.get(i).get("PC_Nm");
//                        callectid = DATABTS.get(i).get("callectid");
//                        DIS_Amt = DATABTS.get(i).get("DIS_Amt");
//                        Amt = DATABTS.get(i).get("Amt");
//                        Pro_IDD = DATABTS.get(i).get("Pro_ID");
//                        databaseAccess.open();
//                        boolean bt = databaseAccess.addBTS(MICODE,TCODE,ACCOUNTT,BILLNOO,BILLDATEE,STATUSS,PREVREADD,PREVDATEE,PRESREADD,PRESDATEE,CONSUMP,Deviationn,WATERBILL,MRENT,TAX,Sewagee,Surchargee,Total_Billl,Arrearss,TOTALDUEE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetIDD,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODEE,RateID,Ram_Month,cnt,Detivee,DateBefore,Edit,CONNew,MTRMFGCODEE,GroupBilll,NPPP,STATU,Lst_Updtt,Lst_Usrr,PC_Nmm,callectid,DIS_Amt,Amt,Pro_IDD);
//
//                    }catch (Exception e){
//                    }
//                }
//            }
//        }).start();
//    }
    //GET DOWNLOAD PAYMENT
/*
    private void downloadPAYMENT(String s) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        PAYMENT= databaseAccess.getLoadPAYMENT(s);
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < PAYMENT.size(); i++) {
                    try {
                        ETA = PAYMENT.get(i).get("ETA");
                        ACCOUNTTT = PAYMENT.get(i).get("ACCOUNT");
                        PAY_NO = PAYMENT.get(i).get("PAY_NO");
                        Pay_MonthH = PAYMENT.get(i).get("Pay_Month");
                        PAY_DATE = PAYMENT.get(i).get("PAY_DATE");
                        TOTALBill_Amt = PAYMENT.get(i).get("TOTALBill_Amt");
                        Arreas_Amt = PAYMENT.get(i).get("Arreas_Amt");
                        TOTAL_Amt = PAYMENT.get(i).get("TOTAL_Amt");
                        OverpayY = PAYMENT.get(i).get("Overpay");
                        PSTATUS = PAYMENT.get(i).get("PSTATUS");
                        Cmd = PAYMENT.get(i).get("Cmd");
                        Bank = PAYMENT.get(i).get("Bank");
                        BILLNOOO = PAYMENT.get(i).get("BILLNO");
                        Location = PAYMENT.get(i).get("Location");
                        BankNm = PAYMENT.get(i).get("BankNm");
                        Lst_updt = PAYMENT.get(i).get("Lst_updt");
                        Lst_usr = PAYMENT.get(i).get("Lst_usr");
                        pc_nm = PAYMENT.get(i).get("pc_nm");
                        h = PAYMENT.get(i).get("h");
                        UserID = PAYMENT.get(i).get("UserID");
                        UserNm = PAYMENT.get(i).get("UserNm");
                        Amt_letter = PAYMENT.get(i).get("Amt_letter");
                        DD = PAYMENT.get(i).get("DD");
                        VV = PAYMENT.get(i).get("VV");
                        dr = PAYMENT.get(i).get("dr");
                        Cr = PAYMENT.get(i).get("Cr");
                        databaseAccess.open();
                        boolean payment = databaseAccess.addPAYMENTSS(ETA,ACCOUNTTT,PAY_NO,Pay_MonthH,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,OverpayY,PSTATUS,Cmd,Bank,BILLNOOO,Location,BankNm,Lst_updt,Lst_usr,pc_nm,h,UserID,UserNm,Amt_letter,DD,VV,dr,Cr);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }

*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
