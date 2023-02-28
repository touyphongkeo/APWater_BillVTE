package com.app.apisvtes.downloadbtpayment;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.DistID;
import static com.app.apisvtes.conn.ClassLibs.VillID;

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

public class LoadBTPaymentActivity extends BaseActivity {

    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

    private ProgressBar pgsBar;
    private int k = 0;
    private Handler hdlr = new Handler();

    String  strACCOUNT="";
    String  strRENT="";
    String  RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,Consumption, Deviation, Detive, waterBill, Mrent,  Sewage, Tax,  Surcharge, Total_Bill,TOTALDUE1, KhetID, AREACODE,  ZONE,  TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,MTRWIDTH,AVGCONSUM,Latitude,Longitude,printer,BILLNO;
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
    String MICODES,TCODES,BILLNOS,ACCOUNTS,NAMES,AREACODES,ZONES,RATRIDS,CCODES,HUNITS,CONADDR1,MAILADDR1,CUCODE,CNAME,CONADDR2,MAILADDR2,MEMONO,CONFEES,DEP_DT,CON_DT,SIZECODE,MTRWIDTHS,MTRNUMBERS,MTRMFGCODE,DMETHOD,LOCATION,ALTITUTE,LONGTITUTE,STATUS,WALKSEQ,MCODE,AVGCONSUMS,TOTALDUES,overpay,MOnno,PCAREA,COCODE,DMA,CmbAccount,chk,MUseDate,GroupBill,NPP,CallectID,BILLDATE,Cost1,Cost2,Cost3,Rem_Cost,Unit,Lst_Updt,Lst_Usr,PC_Nm,OLD_ACCOUNT,move,Cost4,CONTax,Page_Grp,MM,Pro_ID;

    //String bt
    String MICODE,TCODE,ACCOUNTT,BILLNOO,BILLDATEE,STATUSS,PREVREADD,PREVDATEE,PRESREADD,PRESDATEE,CONSUMP,Deviationn,WATERBILL,MRENT,TAX,Sewagee,Surchargee,Total_Billl,Arrearss,TOTALDUEE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetIDD,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODEE,RateID,Ram_Month,cnt,Detivee,DateBefore,Edit,CONNew,MTRMFGCODEE,GroupBilll,NPPP,STATU,Lst_Updtt,Lst_Usrr,PC_Nmm,callectid,DIS_Amt,Amt,Pro_IDD;

    //String payment
    String ETA,ACCOUNTTT,PAY_NO,Pay_MonthH,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,OverpayY,PSTATUS,Cmd,Bank,BILLNOOO,Location,BankNm,Lst_updt,Lst_usr,pc_nm,h,UserID,UserNm,Amt_letter,DD,VV,dr,Cr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadwaterbt);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ດາວໂຫລດຂໍ້ມູນ BT,Payment");

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

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LoadBTPaymentActivity.this);

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


                provAdapter   = new ArrayAdapter<String>(  LoadBTPaymentActivity.this, android.R.layout.simple_list_item_1);
                provAdapter.addAll(provnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoadBTPaymentActivity.this);
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


                distAdapter   = new ArrayAdapter<String>(LoadBTPaymentActivity.this, android.R.layout.simple_list_item_1);
                distAdapter.addAll(distnames);

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoadBTPaymentActivity.this);
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
//                    try {
//                        databaseAccess.open();
//                        databaseAccess.deleteWaterBill(VillID.toString());
//                    }catch (Exception e){
//
//                    }

                int prstt =0;

        /*      databaseAccess.open();
                downloadPAYMENT(VillID.toString());

*/

                databaseAccess.open();
                DATABTS= databaseAccess.getLoadBT(VillID.toString());
                k = pgsBar.getProgress();
                prstt=DATABTS.size();
                pgsBar.setMax(DATABTS.size());
                int finalPrstt = prstt;
                new Thread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < DATABTS.size(); i++) {
                            try {
                            k += 1;
                        MICODE = DATABTS.get(i).get("MICODE");
                        TCODE = DATABTS.get(i).get("TCODE");
                        ACCOUNTT = DATABTS.get(i).get("ACCOUNT");
                        BILLNOO = DATABTS.get(i).get("BILLNO");
                        BILLDATEE = DATABTS.get(i).get("BILLDATE");
                        STATUSS = DATABTS.get(i).get("STATUS");
                        PREVREADD = DATABTS.get(i).get("PREVREAD");
                        PREVDATEE = DATABTS.get(i).get("PREVDATE");
                        PRESREADD = DATABTS.get(i).get("PRESREAD");
                        PRESDATEE = DATABTS.get(i).get("PREVDATE");
                        CONSUMP = DATABTS.get(i).get("CONSUMP");
                        Deviationn = DATABTS.get(i).get("Deviation");
                        WATERBILL = DATABTS.get(i).get("WATERBILL");
                        MRENT = DATABTS.get(i).get("MRENT");
                        TAX = DATABTS.get(i).get("TAX");
                        Sewagee = DATABTS.get(i).get("Sewage");
                        Surchargee = DATABTS.get(i).get("Surcharge");
                        Total_Billl = DATABTS.get(i).get("Total_Bill");
                        Arrearss = DATABTS.get(i).get("Arrears");
                        TOTALDUEE = DATABTS.get(i).get("TOTALDUE");
                        AMOUNT = DATABTS.get(i).get("AMOUNT");
                        PAYDATE = DATABTS.get(i).get("PAYDATE");
                        Payarreas = DATABTS.get(i).get("Payarreas");
                        Total = DATABTS.get(i).get("Total");
                        Pay_Month = DATABTS.get(i).get("Pay_Month");
                        Overpay = DATABTS.get(i).get("Overpay");
                        Grand_total = DATABTS.get(i).get("Grand_total");
                        KhetIDD = DATABTS.get(i).get("KhetID");
                        areacode = DATABTS.get(i).get("areacode");
                        Checks = DATABTS.get(i).get("Checks");
                        Paid = DATABTS.get(i).get("Paid");
                        R_Code = DATABTS.get(i).get("R_Code");
                        Choose = DATABTS.get(i).get("Choose");
                        ReadDateBill = DATABTS.get(i).get("ReadDateBill");
                        CCODE = DATABTS.get(i).get("CCODE");
                        SIZECODEE = DATABTS.get(i).get("SIZECODE");
                        RateID = DATABTS.get(i).get("RateID");
                        Ram_Month = DATABTS.get(i).get("Ram_Month");
                        cnt = DATABTS.get(i).get("cnt");
                        Detivee = DATABTS.get(i).get("Detive");
                        DateBefore = DATABTS.get(i).get("DateBefore");
                        Edit = DATABTS.get(i).get("Edit");
                        CONNew = DATABTS.get(i).get("CONNew");
                        MTRMFGCODEE = DATABTS.get(i).get("MTRMFGCODE");
                        GroupBilll = DATABTS.get(i).get("GroupBill");
                        NPPP = DATABTS.get(i).get("NPP");
                        STATU = DATABTS.get(i).get("STATU");
                        Lst_Updtt = DATABTS.get(i).get("Lst_Updt");
                        Lst_Usrr = DATABTS.get(i).get("Lst_Usr");
                        PC_Nmm = DATABTS.get(i).get("PC_Nm");
                        callectid = DATABTS.get(i).get("callectid");
                        DIS_Amt = DATABTS.get(i).get("DIS_Amt");
                        Amt = DATABTS.get(i).get("Amt");
                        Pro_IDD = DATABTS.get(i).get("Pro_ID");
                        databaseAccess.open();
                        boolean bt = databaseAccess.addBTS(MICODE,TCODE,ACCOUNTT,BILLNOO,BILLDATEE,STATUSS,PREVREADD,PREVDATEE,PRESREADD,PRESDATEE,CONSUMP,Deviationn,WATERBILL,MRENT,TAX,Sewagee,Surchargee,Total_Billl,Arrearss,TOTALDUEE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetIDD,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODEE,RateID,Ram_Month,cnt,Detivee,DateBefore,Edit,CONNew,MTRMFGCODEE,GroupBilll,NPPP,STATU,Lst_Updtt,Lst_Usrr,PC_Nmm,callectid,DIS_Amt,Amt,Pro_IDD);

                        try{
                                    if(k<=pgsBar.getMax()){
                                        try{
                                            hdlr.post(new Runnable() {
                                                public void run() {
                                                    pgsBar.setProgress(k);
                                                    txtView.setText(k+"/"+pgsBar.getMax());
                                                    if(k==pgsBar.getMax()){
                                                        txtView.setText("ດາວໂຫລດຂໍ້ມູນສຳເລັດ");
                                                        Toasty.success(LoadBTPaymentActivity.this, "ດາວໂຫລດຂໍ້ມູນສຳເລັດ", 0).show();
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
                    Toasty.error(LoadBTPaymentActivity.this,R.string.please_connect_to_internet,Toasty.LENGTH_SHORT).show();
                  //  finish();
                }
            }
            }
        });
    }



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
