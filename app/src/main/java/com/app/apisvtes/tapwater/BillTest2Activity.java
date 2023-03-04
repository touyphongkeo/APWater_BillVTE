package com.app.apisvtes.tapwater;

import static com.app.apisvtes.conn.ClassLibs.CustID;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.print.PrintHelper;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.download.LoadWaterActivity;
import com.app.apisvtes.pdf_report.TemplatePDF;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class BillTest2Activity extends AppCompatActivity {
    String txtKheiID="";
    String strBarcode="";
    TextView editText;
    ImageView imageView,bar_images;
    TextView txtUpdateExpense;
    TextView bar_number,txt_KhetID,txt_RATRIs,txt_Tcode,txtMTRNUMBER,txt_PRESDATEse,txt_PREVDATE,txt_PREVREAD,txt_PRESREADs, txtBillNo,txtDate,txtZONE, txtAccount, txtTcode, txtCusName,txtAREACODE, txtRate, txtPreVread, txtPreSread,txtConsumption;
    TextView txtMY1,txtMY2,txtMY3,txtMY4,txtDateWarning;

    TextView  textPrvWater, textWater,textMret,textSEWER1,textTax,textTotalBill,textDue,textTotal,staff,txt_PREVREADss,text_view16;

    private TemplatePDF templatePDF;
    Bitmap bm = null;

    TextView text2,amontmoney,KhetID,Tcode,edit_Date_of_birth,Total_Bill;


    View mView,mView1,mView2;
    CardView button1,button2,button3;

    String CreateDate = "";
    String HH_YEAR = "";
    String ratrid = "";
    public DecimalFormat f1,f12;
    TextView txt_view0,txt_view1,txt_view2,txt_view3,txt_view4,txt_view5,txt_view6,txt_view7,txt_view8,txt_view9,txt_view10,text1,
             text_view19,text_view28,text_view14,text_view18,text_view27,text_view13,text_view17,text_view26,text,text3,office,text3s;

    TextView textfff,text33,txt_ZONEss,txt_Datess,txt_ACCOUNT,txt_NAME,
            txt_AREACODE,txt_KhetIDsss,staffss,txt_PREVDATEss,txt_PRESDATEsess,txt_PRESREADsss,
            txt_MTRNUMBERss,txtConsumptionss,text_view13s,text_view14s,text_view27s,text_view18s,text_view15s,
            text_Waters,textMrets,text_Taxs,textTotalBills,textDues,textTotals,bar_numbers,txtDateWarnings,text_TOTALDUE,TOTALDUE,txt_datepay,repayb;


    String office_name="";
    String dts = "";
    String billno="";
    double bbv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billtest2);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ພິມບິນນ້ຳປະປາ");

        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);

        //  getSupportActionBar().hide();
        //get the layout of visiting card
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_water_bills, null);

        View contentViews = inflater.inflate(R.layout.activity_water_billss, null);

        View contentView1 = inflater.inflate(R.layout.activity_payment_receipt, null);


        //get the main view which will be printed
        RelativeLayout view = (RelativeLayout) contentView.findViewById(R.id.relatived);
        RelativeLayout view1 = (RelativeLayout) contentView1.findViewById(R.id.relative);
        RelativeLayout views = (RelativeLayout) contentViews.findViewById(R.id.relatives);



        txtCusName = view.findViewById(R.id.txt_NAME);
        txtAREACODE = view.findViewById(R.id.txt_AREACODE);
        txtAccount = view.findViewById(R.id.txt_ACCOUNT);
        txtMTRNUMBER= view.findViewById(R.id.txt_MTRNUMBER);
        txtMY1 = view.findViewById(R.id.text_BillMY1);
        txtMY2 = view.findViewById(R.id.text_BillMY2);
        textPrvWater = view.findViewById(R.id.text_PrvWater);
        txtMY3 = view.findViewById(R.id.text_BillMY3);
        txtMY4 = view.findViewById(R.id.text_BillMY4);
        textWater = view.findViewById(R.id.text_Water);
        textTotalBill = view.findViewById(R.id.text_TotalBill);
        bar_number = view.findViewById(R.id.bar_number);
        txtBillNo = view.findViewById(R.id.txt_BillNo);
        txtDate = view.findViewById(R.id.txt_Date);
        txtZONE = view.findViewById(R.id.txt_ZONE);
        staff = view.findViewById(R.id.staff);
        text = view.findViewById(R.id.text);
        text3 = view.findViewById(R.id.text3);
        txtConsumption= view.findViewById(R.id.txt_Consumption);
        text1= view.findViewById(R.id.text_view15);
        text_view19 = view.findViewById(R.id.text_view19);
        text_view28 = view.findViewById(R.id.text_view28);
        text_view14 = view.findViewById(R.id.text_view14);
        text_view18 = view.findViewById(R.id.text_view18);
        text_view27 = view.findViewById(R.id.text_view27);
        text_view13 = view.findViewById(R.id.text_view13);
        text_view17 = view.findViewById(R.id.text_view17);
        text_view26 = view.findViewById(R.id.text_view26);
        txt_PRESREADs = view.findViewById(R.id.txt_PRESREADs);
        txtDateWarning= view.findViewById(R.id.text_DateWarning);
        textTotal= view.findViewById(R.id.text_Total);
        textDue= view.findViewById(R.id.text_Due);
        textTax= view.findViewById(R.id.text_Tax);
        textSEWER1= view.findViewById(R.id.text_SEWER1);
        textMret= view.findViewById(R.id.text_Mret);
        txt_PREVREAD = view.findViewById(R.id.txt_PREVREAD);
        txt_PRESDATEse = view.findViewById(R.id.txt_PRESDATEse);
        txt_KhetID = view.findViewById(R.id.txt_KhetID);
        txt_PREVDATE = view.findViewById(R.id.txt_PREVDATE);




          txt_NAME = views.findViewById(R.id.txt_NAME);
          txt_AREACODE = views.findViewById(R.id.txt_AREACODE);
          txt_ACCOUNT = views.findViewById(R.id.txt_ACCOUNT);
          txt_MTRNUMBERss= views.findViewById(R.id.txt_MTRNUMBERss);
//        txtMY1 = views.findViewById(R.id.text_BillMY1);
//        txtMY2 = views.findViewById(R.id.text_BillMY2);
//        textPrvWater = views.findViewById(R.id.text_PrvWater);
//        txtMY3 = views.findViewById(R.id.text_BillMY3);
//        txtMY4 = views.findViewById(R.id.text_BillMY4);
          text_Waters = views.findViewById(R.id.text_Waters);
          textTotalBills = views.findViewById(R.id.text_TotalBills);
          bar_numbers = views.findViewById(R.id.bar_numbers);
//        txtBillNo = views.findViewById(R.id.txt_BillNo);
          txt_Datess = views.findViewById(R.id.txt_Datess);
          txt_ZONEss = views.findViewById(R.id.txt_ZONEss);
          staffss = views.findViewById(R.id.staffss);
          textfff = views.findViewById(R.id.textfff);
          text33 = views.findViewById(R.id.text33);
          text_view16 = views.findViewById(R.id.text_view16);
          txtConsumptionss= views.findViewById(R.id.txtConsumptionss);
          text_view15s = views.findViewById(R.id.text_view15s);
//        text_view19 = views.findViewById(R.id.text_view19);
//        text_view28 = views.findViewById(R.id.text_view28);
          text_view14s = views.findViewById(R.id.text_view14s);
          text_view18s = views.findViewById(R.id.text_view18s);
          text_view27s = views.findViewById(R.id.text_view27s);
          text_view13s = views.findViewById(R.id.text_view13s);
//        text_view17 = views.findViewById(R.id.text_view17);
//        text_view26 = views.findViewById(R.id.text_view26);
          txt_PRESREADsss = views.findViewById(R.id.txt_PRESREADsss);
          txtDateWarnings= views.findViewById(R.id.txtDateWarnings);
          textTotals= views.findViewById(R.id.textTotals);
          textDues= views.findViewById(R.id.textDues);
          text_Taxs= views.findViewById(R.id.text_Taxs);
//        textSEWER1= views.findViewById(R.id.text_SEWER1);
          textMrets= views.findViewById(R.id.textMrets);
          txt_PREVREADss = views.findViewById(R.id.txt_PREVREADss);
          txt_PRESDATEsess = views.findViewById(R.id.txt_PRESDATEsess);
          txt_KhetIDsss = views.findViewById(R.id.txt_KhetIDsss);
          txt_PREVDATEss = views.findViewById(R.id.txt_PREVDATEss);



        //ພາກສວນ view1
        txt_view0 = view1.findViewById(R.id.text5s);
        txt_view1 = view1.findViewById(R.id.txt_NAME);
        txt_view2 = view1.findViewById(R.id.txt_AREACODES);
        txt_view3 = view1.findViewById(R.id.txt_ACCOUNT);
        txt_view4 = view1.findViewById(R.id.Tcode);
        txt_view5 = view1.findViewById(R.id.txt_BillNo);
        txt_view6 = view1.findViewById(R.id.txt_MTRNUMBER);
        txt_view7 = view1.findViewById(R.id.KhetID);
        txt_view8 = view1.findViewById(R.id.Total_Bill);
        repayb = view1.findViewById(R.id.repayb);
       // txt_view9 = view1.findViewById(R.id.amontmoney);
        txt_view10 = view1.findViewById(R.id.edit_Date_of_birth);
        office = view1.findViewById(R.id.office);
        text3s = view1.findViewById(R.id.text3s);
        text_TOTALDUE = view1.findViewById(R.id.text_TOTALDUE);
        TOTALDUE = view1.findViewById(R.id.TOTALDUE);
        txt_datepay = view1.findViewById(R.id.txt_datepay);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTest2Activity.this);
        databaseAccess.open();
        List<HashMap<String, String>> Datass;
        Datass = databaseAccess.getoffices();
        if (Datass.size() > 0) {
            textfff.setText(Datass.get(0).get("Office_Name"));
            text33.setText("ເບີໂທ: "+Datass.get(0).get("Tel"));

        }







        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);
                //change name

              try {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTest2Activity.this);
                    databaseAccess.open();
                    List<HashMap<String, String>> productData;
                    productData = databaseAccess.getAllWaterpritse(CustID.toString());
                    f1 = new DecimalFormat("#,###");

                    if (productData.size() > 0) {
                        String strAcc=productData.get(0).get("ACCOUNT");
                        String strZone=productData.get(0).get("ZONE");

                        databaseAccess.open();
                        String strZoneName = databaseAccess.getZoneNamessss(strZone.toString());
                        if (strZoneName.equals("ຫລັກ 52")){
                            txt_view0.setText(strZoneName);
                        }else {
                            txt_view0.setText("ເມືອງ "+strZoneName);
                        }

//                      String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
                        String cur = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        txt_datepay.setText(cur);
                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;
//
                        txt_view3.setText(productData.get(0).get("ACCOUNT"));
                        txt_view6.setText(productData.get(0).get("MTRNUMBER"));

                        txt_view1.setText(productData.get(0).get("NAME"));
                        txt_view4.setText(productData.get(0).get("Tcode"));


                        //   txt_view8.setText(productData.get(0).get("TOTALDUE1"));


                        txt_view7.setText(Usr_id1);
                        // txt_view9.setText(productData.get(0).get("TOTALDUE1"));
                        String s1 = productData.get(0).get("TOTALDUE1");
                        Double dss = Double.parseDouble(s1);
                      //  txt_view9.setText(f1.format(dss));
                        txtKheiID=productData.get(0).get("AREACODE").trim();



                        String dtStart = productData.get(0).get("Paid_date");
                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
                        txt_view10.setText(dt);


                        txt_view5.setText(strM+strYear);
                        databaseAccess.open();
                        String Khet_name = databaseAccess.getVillNamesss(txtKheiID.toString());
                        txt_view2.setText(Khet_name);


                        billno = productData.get(0).get("BILLNO").trim();
                        String s1g = productData.get(0).get("Total_Bill");
                      //  String tota = productData.get(0).get("TOTALDUE");
                        String tota = productData.get(0).get("Arrears2");
                        Double dssf = Double.parseDouble(s1g);
                        Double dsss= Double.parseDouble(tota);
                        bbv = dssf+dsss;
                        txt_view8.setText(f1.format(bbv));
//


//                      txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");

                        databaseAccess.open();
                        String tject = databaseAccess.getTOTALDUESS(CustID,billno);


                    /*    databaseAccess.open();
                        String tject22 = databaseAccess.getTOTALDUESSsfdsdfsf(CustID,billno);
                        double v3 = Double.valueOf(tject22);*/

                        double v1 = Double.valueOf(tject);

                        text_TOTALDUE.setText(f1.format(v1));
                        double vb = bbv-v1;
                        double vb2 = v1-bbv;
                        f12 = new DecimalFormat("####");

                        String bvc = String.valueOf(vb);
                        String bvcf = String.valueOf(f12.format(vb2));

                        Double ars = Double.valueOf(f12.format(vb));
                        databaseAccess.open();
                        databaseAccess.updateTOTALDUEBBBB(ars, CustID);

                        if (bbv>v1){
                            TOTALDUE.setText(f1.format(vb));
                            repayb.setText("ຍອດເຫຼື່ອ:");


                        }else {
                            repayb.setText("ຍອດຊຳລະເກີນ:");
                            TOTALDUE.setText(f1.format(vb2));


                        }
                        if (v1>bbv){
                            databaseAccess.open();
                            databaseAccess.updatePaymentOverpay(bvcf, CustID,billno);
                        }





                        databaseAccess.open();
                        List<HashMap<String, String>> Datas;
                        Datas = databaseAccess.getoffice();
                        if (Datas.size() > 0) {
                            office.setText(Datas.get(0).get("Office_Name"));
                            text3s.setText("ເບີໂທ: "+Datas.get(0).get("Tel"));

                        }


                    }

                }catch (Exception ex) {
                  //  Toast.makeText(BillTest2Activity.this, "", Toast.LENGTH_SHORT).show();
                }




                view1.setDrawingCacheEnabled(true);

                view1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                view1.layout(0, 0, view1.getMeasuredWidth(), view1.getMeasuredHeight());

                view1.buildDrawingCache(true);

                mView1 = view1;
                doPhotoPrint();
            }
        });
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap bitmap = mView1.getDrawingCache();

        //print
        photoPrinter.printBitmap("image.png_test_print", bitmap, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                changeOnCon();
            }
        });
    }






    // code for button click
    //  public void onClick(View view) {
    //   doPhotoPrint();
    // }



    public void changeOnCon() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Successfuly").setContentText("ພີມສຳເລັດ!").setConfirmText("ຕົກລົງ").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            try {
                Intent intent = new Intent(BillTest2Activity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }catch (Exception e){
            }
        }
        }).show();
    }


    //for back button






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
