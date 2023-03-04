package com.app.apisvtes.editbill;

import static com.app.apisvtes.conn.ClassLibs.CustID;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;
import static com.app.apisvtes.conn.ClassLibs.fCheck;
import static com.app.apisvtes.conn.ClassLibs.flatitude;
import static com.app.apisvtes.conn.ClassLibs.flongitude;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.tapwater.BillTestActivity;
import com.app.apisvtes.tapwater.BillTestsActivity;
import com.app.apisvtes.utils.BaseActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;


public class EditWaterbbBillActivity extends BaseActivity {
    String flag = "";
    String date_time = "";
    String svl="";

    String strRate="";
    int mYear,mMonth,mDay,mHour,mMinute;
    // ImageView imgScanner,imgScanner2;
    public static EditText etxtSearch;
    String  AREACODE="";
    String khetID = "";
    String account = "";
    EditText txtAccount, txtTcode, txtCusName, txtRate, txtPreVread, txtPreSread;
    EditText txtConSump,txtDeviation,txtDetive,txtWater,txtWater1,txtMret,txtMret1,txtSEWER1,txtTax,txtTax1,TextBox4,txtTotalBill,txtTotalBill1,txtDue,txtDue1,txtTotal,txtTotal1;
    TextView txtEditExpense,txtUpdateExpense,txtUpdateExpense2,txtPrint,distct;
    public DecimalFormat f;
    public DecimalFormat f1;
    public DecimalFormat f3;

    String  txtSlab3="0";
    String  txtCurCode="0";
    String  txtSlab2="0";
    String  txtCCODE="0";
    String  txtCATName="0";
    String  txtRate1="0";
    String  txtSlab1 ="0";
    String  txtRate2 ="0";
    String  txtSday2 ="0";
    String  txtRate3="0";
    String  txtRate4="0";
    String  txtSLAB4="0";
    String  txtSLAB5="0";
    String  txtRATE5="0";
    String  txtMincons="0";
    String  ComboBox1="0";
    String  CmbSurCH="0";
    String  txtSUR1="0";
    String  txtSDay1="0";
    String  txtSUR2="0";
    String  txtSUR3="0";
    String  txtSday3="0";
    String  txtSEWER="0";
    String  txtVAT="0";
    String billno;
    String PREDATES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbiil_waterbill);
        try{
            getSupportActionBar().setHomeButtonEnabled(true); //for back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
            getSupportActionBar().setTitle(R.string.edit_waterbill);
            f = new DecimalFormat("##0.00");

            txtAccount = findViewById(R.id.txtAccount);
            txtTcode = findViewById(R.id.TxtTcode);
            txtCusName = findViewById(R.id.txtCusName);
            txtRate = findViewById(R.id.txtRate);

            txtPreVread = findViewById(R.id.txtPreVread);
            txtPreSread = findViewById(R.id.txtPreSread);

            txtConSump = findViewById(R.id.txtConSump);
            txtDeviation = findViewById(R.id.txtDeviation);
            txtDetive = findViewById(R.id.txtDetive);
            txtWater = findViewById(R.id.txtWater);
            txtWater1 = findViewById(R.id.txtWater1);
            txtMret = findViewById(R.id.txtMret);
            txtMret1 = findViewById(R.id.txtMret1);

            txtSEWER1 = findViewById(R.id.txtSEWER1);
            txtTax = findViewById(R.id.txtTax);
            txtTax1 = findViewById(R.id.txtTax1);
            TextBox4 = findViewById(R.id.TextBox4);
            txtTotalBill =findViewById(R.id.txtTotalBill);
            txtTotalBill1 =findViewById(R.id.txtTotalBill1);
            txtDue =findViewById(R.id.txtDue);
            txtDue1 =findViewById(R.id.txtDue1);
            txtTotal = findViewById(R.id.txtTotal);
            txtTotal1 = findViewById(R.id.txtTotal1);
            txtEditExpense = findViewById(R.id.txt_edit_expense);
            txtUpdateExpense=findViewById(R.id.txt_update_expense);
            txtUpdateExpense2=findViewById(R.id.txt_update_expense2);
            txtPrint=findViewById(R.id.txt_print);
            distct=findViewById(R.id.distct);
            //etxtSearch = findViewById(R.id.etxt_search);
            String strAccount="";

            if (CustID==""){
                CustID= "";
            }else{
                strAccount=CustID;
            }
            LoadView();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(EditWaterbbBillActivity.this);
            try {
                    databaseAccess.open();
                    List<HashMap<String, String>> productData1;
                    productData1 = databaseAccess.getDataratejfg(strRate.toString());
                    String tt = String.valueOf(productData1.size());
                    if (productData1.size() > 0) {
                        txtSlab3=productData1.get(0).get("SUR3");
                        txtCurCode=productData1.get(0).get("CURCODE");
                        txtSlab2=productData1.get(0).get("SLAB2");
                        txtCCODE=productData1.get(0).get("CCODE");
                        txtCATName=productData1.get(0).get("CATNAME");
                        txtRate1=productData1.get(0).get("RATE1");
                        txtSlab1 =productData1.get(0).get("SLAB1");
                        txtRate2 =productData1.get(0).get("RATE2");
                        txtRate3=productData1.get(0).get("RATE3");
                        txtSlab3=productData1.get(0).get("SLAB3");
                        txtRate4=productData1.get(0).get("RATE4");
                        txtSLAB4=productData1.get(0).get("SLAB4");
                        txtSLAB5=productData1.get(0).get("SLAB5");
                        txtRATE5=productData1.get(0).get("RATE5");
                        txtMincons=productData1.get(0).get("MINCONSUMP");
                        ComboBox1=productData1.get(0).get("CURNCYTYPE");
                        CmbSurCH=productData1.get(0).get("SURCHARGE");
                        txtSUR1=productData1.get(0).get("SUR1");
                        txtSDay1=productData1.get(0).get("SDAY1");
                        txtSUR2=productData1.get(0).get("SUR2");
                        txtSday2=productData1.get(0).get("SDAY2");
                        txtSUR3=productData1.get(0).get("SUR3");
                        txtSday3=productData1.get(0).get("SDAY3");
                        txtSEWER=productData1.get(0).get("SEWER");
                        txtVAT=productData1.get(0).get("VAT");
                }


            }catch (Exception ex) {
                Toasty.error(this, Objects.requireNonNull(ex.getMessage()), Toast.LENGTH_SHORT).show();
            }
            if(flag=="Save"){
                txtPrint.setVisibility(View.VISIBLE);
                txtUpdateExpense.setVisibility(View.GONE);
            }else{
                txtPrint.setVisibility(View.GONE);
                txtUpdateExpense.setVisibility(View.VISIBLE);
            }


            txtAccount.setEnabled(false);
            //   txtPreSread.requestFocus();
            txtUpdateExpense2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   Intent intent=new Intent(EditWaterBillActivity.this, MenuActivity.class);
                    //    startActivity(intent);
                    //  finish();
                    // super.onBackPressed();
                    onBackPressed();
                }
            });

            txtPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toasty.success(EditWaterBillActivity.this, PREDATES, Toast.LENGTH_SHORT).show();
                    if (strRate.equals("01")){
                     // Toast.makeText(EditWaterbbBillActivity.this, strRate, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditWaterbbBillActivity.this, BillTestbillActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(strRate.equals("02")){
                        Intent intent = new Intent(EditWaterbbBillActivity.this, BillTestbill2Activity.class);
                        startActivity(intent);
                        finish();
                    }else if (strRate.equals("03")){
                        Intent intent = new Intent(EditWaterbbBillActivity.this, BillTestbillActivity.class);
                        startActivity(intent);
                        finish();
                    }else if ((strRate.equals("04"))){
                        Intent intent = new Intent(EditWaterbbBillActivity.this, BillTestbillActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

            txtUpdateExpense.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //    Toast.makeText(EditWaterBillActivity.this, AREACODE+""+khetID, Toast.LENGTH_SHORT).show();
                    String PREVREAD, PRESDATE, PRESREAD, Deviation, Consumption, waterBill, Mrent, Sewage, Tax,Surcharge, Total_Bill, Arrears, TOTALDUE1, Detive,  ConNew, Paid_date,  ACCOUNT;
                    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                    try{
                        PREVREAD=txtPreVread.getText().toString();
                        PRESDATE=currentDate.toString();
                        PRESREAD=txtPreSread.getText().toString();
                        Deviation=txtDeviation.getText().toString();
                        Consumption=txtConSump.getText().toString();
                        waterBill=txtWater.getText().toString();
                        Mrent=txtMret.getText().toString();
                        Sewage=txtSEWER1.getText().toString();
                        Tax=txtTax.getText().toString();
                        Surcharge=TextBox4.getText().toString();
                        Total_Bill=txtTotalBill.getText().toString();
                        Arrears= txtDue.getText().toString();
                        TOTALDUE1=txtTotal.getText().toString();
                        Detive=txtDetive.getText().toString();
                        ConNew=txtConSump.getText().toString();
                        Paid_date=currentDate.toString();
                        ACCOUNT=txtAccount.getText().toString();

                        double dPRESREAD=Double.parseDouble(PRESREAD);
                        double dPREVREAD=Double.parseDouble(PREVREAD);
                        Consumption=Double.toString(dPRESREAD-dPREVREAD);

                        try{
                            double  AVGCon=30;
                            double w1 = Double.parseDouble(Consumption);
                            double w2 = w1/AVGCon;

                            Detive = String.valueOf(f.format(w2));
                            Deviation = "0";


                            try{
                                double ject = Double.parseDouble(Consumption);
                                String jectfire = "5";
                                if (ject<5){
                                    waterBill= CslBill(jectfire);
                                }else {
                                    waterBill= CslBill(Consumption);
                                }

                            }catch (Exception e){

                            }



                            double  Vat=7;
                            double  dTax =  Double.parseDouble(waterBill) + Double.parseDouble(Mrent);
                            double vTax= dTax * Vat / 100;
                          //  double vTaxs= (double) (Math.ceil(vTax/100.0))*100;
                            Tax=String.valueOf(vTax);


                            double  dTotal_Bill =  Double.parseDouble(waterBill) + Double.parseDouble(Mrent) + Double.parseDouble(Tax)   + Double.parseDouble(Surcharge)  ;
                            Total_Bill =  Double.valueOf(dTotal_Bill).toString();


                            if (Arrears!="") {
                                double  dTOTALDUE = dTotal_Bill+ Double.parseDouble(Arrears);
                                TOTALDUE1 = String.valueOf(dTOTALDUE);
                            }else{
                                TOTALDUE1 = Total_Bill;
                            }


                        }catch (Exception e){
                          //  Toasty.error(EditWaterBillActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();

                        }



                        if (PRESREAD.isEmpty()) {
                            txtPreSread.setError("ຕ້ອງປ້ອນເລກຈົດຄັ້ງນີ້");
                            txtPreSread.requestFocus();

                        } else if(dPRESREAD<dPREVREAD) {
                            txtPreSread.setError("ຕົວເລກຈົດຄັ້ງນີ້ຕ້ອງຫຼາຍກວ່າຄັ້ງກ່ອນ");
                            txtPreSread.requestFocus();
                        }else{
                            try {

                                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(EditWaterbbBillActivity.this);
                                databaseAccess.open();
                                String bb = "1";
                                boolean check = databaseAccess.updateWater(PREVREAD, PRESDATE, PRESREAD, Deviation, Consumption, waterBill, Mrent, Sewage, Tax,Surcharge, Total_Bill, TOTALDUE1, Detive,  ConNew, Paid_date,  ACCOUNT,flatitude,flongitude);

                                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                                boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                                if(isconnected){
                                    String version = "numpapavte27022023.apk";

                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                                    String dateF = sdf.format(new Date());

                                    databaseAccess.open();
                                    databaseAccess.updateWaterSV(PREVREAD, PRESDATE, PRESREAD, Deviation, Consumption, waterBill, Mrent, Sewage, Tax, Surcharge, Total_Bill, TOTALDUE1, Detive, ConNew, Paid_date, ACCOUNT, flatitude, flongitude,version);
                                    //move =0 BB
                                    databaseAccess.updatemasterMOVE(ACCOUNT,billno);
                                    //move=0 MASTER
                                    databaseAccess.updatemasterMOVEone(ACCOUNT,billno);

                                    PREDATES = databaseAccess.getPROSDATES();

                                    databaseAccess.updatemasterMOVACCOUNT(khetID, AREACODE, ACCOUNT,billno);
                                    databaseAccess.open();
                                    databaseAccess.updateMASTER(PRESDATE, khetID, AREACODE, ACCOUNT,billno);

                                    databaseAccess.open();
                                    databaseAccess.updateMASTERS(khetID, AREACODE, ACCOUNT,billno);

                                    databaseAccess.open();
                                    databaseAccess.updateMASTERSBill_no(ACCOUNT,billno,dateF);



                                    databaseAccess.open();
                                    databaseAccess.updateMaster22(TOTALDUE1, ACCOUNT);
//                                    databaseAccess.open();
//                                    databaseAccess.updateMASTERTOTALDUE1(TOTALDUE1, khetID, AREACODE, ACCOUNT,billno);

                                    String android = "Android";
                                    databaseAccess.open();
                                    databaseAccess.addBT(PRESDATE, Usr_id1, android, khetID, AREACODE,billno);

                                    databaseAccess.open();
                                    databaseAccess.addBTArrears(Arrears, khetID, AREACODE,billno);


                                    databaseAccess.open();
                                    databaseAccess.updateBT();

                                    if (fCheck==1) {
                                        databaseAccess.open();
                                        databaseAccess.updateUpload(ACCOUNT,"0");
                                    }else{
                                        databaseAccess.open();
                                        databaseAccess.updateUpload(ACCOUNT,"2");
                                    }
                                }else{
                                    databaseAccess.open();
                                    databaseAccess.updateUpload(ACCOUNT,"2");
                                    //move =0 tbl_water
                                    databaseAccess.open();
                                    databaseAccess.updatemasterMOVESS(ACCOUNT,billno);
                                    //move=0 MASTER
                                    databaseAccess.open();
                                    databaseAccess.updatemasterMOVEoneSS(ACCOUNT,billno);
                                    //LOCK ITEM
                                    databaseAccess.open();
                                    databaseAccess.updatemasterMOVACCOUNTSS(khetID, AREACODE, ACCOUNT,billno);
                                    //UPDTE MASTER :PRESDATE, khetID, AREACODE, ACCOUNT,billno
                                    databaseAccess.open();
                                    databaseAccess.updateMASTERSS(PRESDATE, khetID, AREACODE, ACCOUNT,billno);
                                   /* databaseAccess.open();
                                    databaseAccess.Updatemasterandtbl_water(khetID, AREACODE, ACCOUNT,billno);*/
                                }

                                if (check) {
                                    Toasty.success(EditWaterbbBillActivity.this, R.string.update_successfully, Toast.LENGTH_SHORT).show();

                                    txtPrint.setVisibility(View.VISIBLE);
                                    txtUpdateExpense.setVisibility(View.GONE);
                                    LoadView();
                                }else{
                                    txtPrint.setVisibility(View.GONE);
                                    txtUpdateExpense.setVisibility(View.VISIBLE);
                                }

                            }catch (Exception e){
                                Toasty.error(EditWaterbbBillActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();
                            }

                        }

                    }catch (Exception e){
                        Toasty.error(EditWaterbbBillActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }


            });


        }catch (Exception e){
            Toasty.error(EditWaterbbBillActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    public static Bitmap convertViewToBitmap(View view)
    {
        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    @Override
    public void onBackPressed() {
        // This will be called either automatically for you on 2.0
        // or later, or by the code above on earlier versions of the
        // platform.
        super.onBackPressed();
        return;
    }

    public void LoadView(){
        try {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(EditWaterbbBillActivity.this);
            databaseAccess.open();
            List<HashMap<String, String>> productData;
            productData = databaseAccess.getAllWaterView(CustID.toString());
            f1 = new DecimalFormat("#,###.00");
            if (productData.size() > 0) {

                account = productData.get(0).get("ACCOUNT");
                txtAccount.setText(productData.get(0).get("ACCOUNT"));
                txtTcode.setText(productData.get(0).get("Tcode"));
                txtCusName.setText(productData.get(0).get("NAME"));
                txtRate.setText(productData.get(0).get("RATRID"));
                strRate=productData.get(0).get("RATRID").trim();
                txtPreVread.setText(productData.get(0).get("PREVREAD"));
                txtPreSread.setText(productData.get(0).get("PRESREAD"));
                txtConSump.setText(productData.get(0).get("Consumption"));
                txtDeviation.setText(productData.get(0).get("Deviation"));
                txtDetive.setText(productData.get(0).get("Detive"));

                txtWater.setText(productData.get(0).get("waterBill"));
                //format Decimal
                txtWater1.setText(productData.get(0).get("waterBill"));
                String s11e1c = productData.get(0).get("waterBill");
                Double dss1e1s = Double.parseDouble(s11e1c);
                txtWater1.setText(f1.format(dss1e1s));

                txtMret.setText(productData.get(0).get("RENT"));
                //format Decimal
                txtMret1.setText(productData.get(0).get("Mrent"));
                String s11e1 = productData.get(0).get("Mrent");
                Double dss1e1 = Double.parseDouble(s11e1);
                txtMret1.setText(f1.format(dss1e1));


                txtSEWER1.setText(productData.get(0).get("Sewage"));

                txtTax.setText(productData.get(0).get("Tax"));
                txtTax1.setText(productData.get(0).get("Tax"));
                String s11e = productData.get(0).get("Tax");
                Double dss1e = Double.parseDouble(s11e);
                txtTax1.setText(f1.format(dss1e));


                khetID = productData.get(0).get("KhetID");
                AREACODE = productData.get(0).get("AREACODE");
                TextBox4.setText(productData.get(0).get("Surcharge"));

                txtTotalBill.setText(productData.get(0).get("Total_Bill"));
                txtTotalBill1.setText(productData.get(0).get("Total_Bill"));
                String s11 = productData.get(0).get("Total_Bill");
                Double dss1 = Double.parseDouble(s11);
                txtTotalBill1.setText(f1.format(dss1));

                txtDue.setText(productData.get(0).get("TOTALDUE"));
                txtDue1.setText(productData.get(0).get("TOTALDUE"));
                String s1 = productData.get(0).get("TOTALDUE");
                Double dss = Double.parseDouble(s1);
                txtDue1.setText(f1.format(dss));

                txtTotal.setText(productData.get(0).get("TOTALDUE1"));
                txtTotal1.setText(productData.get(0).get("TOTALDUE1"));
                String s = productData.get(0).get("TOTALDUE1");
                Double ds = Double.parseDouble(s);
                txtTotal1.setText(f1.format(ds));

                billno = productData.get(0).get("BILLNO");

                svl=txtPreSread.getText().toString();
                double d=Double.parseDouble(svl);
                if(d>0){
                    flag="Save";
                }else{
                    flag="";
                }

            }

        }catch (Exception ex) {
            Toasty.error(this, Objects.requireNonNull(ex.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    public String CslBill(String str) {
        Double dstr = Double.parseDouble(str);
        Double B1 = 0.0;
        Double B2 = 0.0;
        Double B3 = 0.0;
        Double B4 = 0.0;
        Double Bs = 0.0;
        try {


            if (dstr <= Double.parseDouble(txtSlab1)) {

                if( txtMincons !="0") {

                    if(dstr * Double.parseDouble(txtRate1) <= Double.parseDouble(txtMincons)){
                        B1 = Double.parseDouble(txtMincons) ;
                        B2 = 0.0;
                        B3 = 0.0;
                        B4 = 0.0;
                    }else{
                        B1 =Double.parseDouble(txtRate1) * dstr;
                        B2 = 0.0;
                    }

                }else{
                    B1 =  Double.parseDouble(txtRate1) * dstr;
                    B2 = 0.0;
                }


            } else if (dstr <= Double.parseDouble(txtSlab2)) {

                B1 =Double.parseDouble(txtRate1) * Double.parseDouble(txtSlab1);
                B2 =Double.parseDouble(txtRate2) * ( dstr- Double.parseDouble(txtSlab1));
                B3 = 0.0;
                B4 = 0.0;

            } else if (dstr < Double.parseDouble(txtSlab3)) {
                B1 = Double.parseDouble(txtRate1) * Double.parseDouble(txtSlab1);
                B2=Double.parseDouble(txtSlab2) - ( Double.parseDouble(txtSlab1)*Double.parseDouble(txtRate2));
                B3 =Double.parseDouble(txtRate3) * ( dstr- Double.parseDouble(txtSlab2));
                B4 = 0.0;
            }else{
                if(Double.parseDouble(txtSlab3)==0){
                    B1 = Double.parseDouble(txtRate1) * Double.parseDouble(txtSlab1);
                    B2=Double.parseDouble(txtRate2) * ( Double.parseDouble(txtSlab2)-Double.parseDouble(txtSlab1));
                    B3 =Double.parseDouble(txtRate3) * ( dstr- Double.parseDouble(txtSlab2));
                    B4 = 0.0;
                }else{
                    B1 = Double.parseDouble(txtRate1) * Double.parseDouble(txtSlab1);
                    B2=Double.parseDouble(txtRate2) * ( Double.parseDouble(txtSlab2)-Double.parseDouble(txtSlab1));
                    B3 =Double.parseDouble(txtRate3) * ( Double.parseDouble(txtSlab3)- Double.parseDouble(txtSlab2));
                    B4 = Double.parseDouble(txtRate4) *( dstr- Double.parseDouble(txtSlab3));
                }
            }

            Bs = B1 + B2 + B3 + B4;
        }catch (Exception e){
        }
        String vl=  String.valueOf(f.format(Bs));
        return  vl;
    }

}
