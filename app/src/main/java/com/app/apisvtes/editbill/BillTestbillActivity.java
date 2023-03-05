package com.app.apisvtes.editbill;

import static com.app.apisvtes.conn.ClassLibs.CustID;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.print.PrintHelper;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.payments.Payment2Activity;
import com.app.apisvtes.payments.PaymentActivity;
import com.app.apisvtes.pdf_report.TemplatePDF;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class BillTestbillActivity extends AppCompatActivity {
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

    public DecimalFormat f1,f3;
    TextView txt_view0,txt_view1,txt_view2,txt_view3,txt_view4,txt_view5,txt_view6,txt_view7,txt_view8,txt_view9,txt_view10,text1,
            text_view19,text_view28,text_view14,text_view18,text_view27,text_view13,text_view17,text_view26,text,text3,office,text3s;

    TextView textfff,text33,txt_ZONEss,txt_Datess,txt_ACCOUNT,txt_NAME,
            txt_AREACODE,txt_KhetIDsss,staffss,txt_PREVDATEss,txt_PRESDATEsess,txt_PRESREADsss,
            txt_MTRNUMBERss,txtConsumptionss,text_view13s,text_view14s,text_view27s,text_view18s,text_view17s,text_view15s,
            text_Waters,textMrets,text_Taxs,textTotalBills,textDues,textTotals,bar_numbers,txtDateWarnings,text_view20,text_view29,text_view26s,
            text_view19s,text_view28s,text_view16s,text_view20s,text_view29s;


    String office_name="";
    String dts = "";
    TableRow bbb2,bbb1,bbb3;
    TableRow mbjects,njects,njectb;
    Double cons;
    String consumption;
    String rate1;
    String slab1,Pay;

    LinearLayout layout1s;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billtestbill);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ລະບົບພິມບິນນ້ຳປະປາ");

        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        layout1s= findViewById(R.id.layout1);




        //  getSupportActionBar().hide();
        //get the layout of visiting card
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_water_billsbills, null);

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
        text_view20 = view.findViewById(R.id.text_view20);
        text_view29 = view.findViewById(R.id.text_view29);
        text_view16 = view.findViewById(R.id.text_view16);

        //tablerow
        bbb2 = view.findViewById(R.id.bbb2);
        bbb1 = view.findViewById(R.id.bbb1);
        bbb3 = view.findViewById(R.id.bbb3);




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

        txtConsumptionss= views.findViewById(R.id.txtConsumptionss);
        text_view15s = views.findViewById(R.id.text_view15s);
        text_view19s = views.findViewById(R.id.text_view19s);
        text_view28s = views.findViewById(R.id.text_view28s);
        text_view14s = views.findViewById(R.id.text_view14s);
        text_view18s = views.findViewById(R.id.text_view18s);
        text_view27s = views.findViewById(R.id.text_view27s);
        text_view13s = views.findViewById(R.id.text_view13s);
        text_view17s = views.findViewById(R.id.text_view17s);
        text_view26s = views.findViewById(R.id.text_view26s);
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
        text_view16s = views.findViewById(R.id.text_view16s);
        mbjects = views.findViewById(R.id.mbjects);
        njects = views.findViewById(R.id.njects);
        njectb = views.findViewById(R.id.njectb);
        text_view20s = views.findViewById(R.id.text_view20s);
        text_view29s = views.findViewById(R.id.text_view29s);



        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestbillActivity.this);
        databaseAccess.open();
        List<HashMap<String, String>> Datass;
        Datass = databaseAccess.getoffices();
        if (Datass.size() > 0) {
            textfff.setText(Datass.get(0).get("Office_Name"));
            text33.setText("ເບີໂທ: "+Datass.get(0).get("Tel"));

        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);
                staff.setText(Usr_id1);
                try {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestbillActivity.this);
                    databaseAccess.open();
                    List<HashMap<String, String>> productData;
                    productData = databaseAccess.getAllWaterView(CustID.toString());
                    f1 = new DecimalFormat("#,###");
                    f3 = new DecimalFormat("####");
                    if (productData.size() > 0) {
                        txtCusName.setText(productData.get(0).get("NAME"));
                        txt_KhetID.setText(productData.get(0).get("RATRID"));
                        txtAccount.setText(productData.get(0).get("ACCOUNT"));
                        txt_PREVREAD.setText(productData.get(0).get("PREVREAD"));
                        //  txt_Tcode.setText(productData.get(0).get("Tcode"));
                        txtMTRNUMBER.setText(productData.get(0).get("MTRNUMBER"));



                        String datesv = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        txt_PREVDATE.setText(datesv);
                        String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());

                        String strM4 = new SimpleDateFormat("MM", Locale.getDefault()).format( new Date());
                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
                        textPrvWater.setText("0.0");

                        //textWater.setText(productData.get(0).get("waterBill"));
                        String s1sewe1 = productData.get(0).get("waterBill");
                        Double dsesewe1 = Double.parseDouble(s1sewe1);
                        textWater.setText(f1.format(dsesewe1));


                        //  text_view20.setText(f1.format(dsesewe1));

                        textSEWER1.setText(productData.get(0).get("Sewage"));



                        String strAcc=productData.get(0).get("ACCOUNT");
                        String strZone=productData.get(0).get("ZONE");

                        txt_PRESREADs.setText(productData.get(0).get("PRESREAD"));

                        consumption = productData.get(0).get("Consumption");
                        cons = Double.parseDouble(consumption);


                        //  text_view18.setText(f1.format(cons)+"ມ³");
                        //  text_view27.setText(f1.format(cons)+"ມ³");

//                        databaseAccess.open();
//                        String strZoneName = databaseAccess.getZoneName(strZone.toString());
//                        txtZONE.setText("ເມືອງ "+strZoneName);

                        databaseAccess.open();
                        String strZoneName = databaseAccess.getZoneNamessss(strZone.toString());
                        if (strZoneName.equals("ຫລັກ 52")){
                            txtZONE.setText(strZoneName);
                        }else {
                            txtZONE.setText("ເມືອງ "+strZoneName);
                        }

                        txtDate.setText(currentDate);


                        txtBillNo.setText("ໃບບີນແຈ້ງຄ່ານ້ຳປະປາ:"+strAcc+strM+strYear);

                        strBarcode=strM+strYear;

                        //  bar_number.setText(strBarcode);
                        //   bar_number.setText(strBarcode);
                        bar_number.setText(productData.get(0).get("ACCOUNT"));




                        //textTotalBill.setText(productData.get(0).get("Total_Bill"));
                        String s1se = productData.get(0).get("Total_Bill");
                        Double dsese = Double.parseDouble(s1se);
                        textTotalBill.setText(f1.format(dsese));


                        Double taloj = Double.parseDouble(f3.format(dsese));

                        databaseAccess.open();
                        databaseAccess.updatotal_Bill(taloj,CustID);

                        //textDue.setText(productData.get(0).get("TOTALDUE"));
                        String s1s = productData.get(0).get("Arrears2");
                        Double dses = Double.parseDouble(s1s);
                        textDue.setText(f1.format(dses));

                        double antotall = dsese+dses;

                        Double rs = Double.parseDouble(f3.format(dses));
                        databaseAccess.open();
                        databaseAccess.updateArrearsMaster(rs, CustID);

                        // textTotal.setText(productData.get(0).get("TOTALDUE1"));
                       // String s1 = productData.get(0).get("TOTALDUE1");
                     //   double dss = Double.parseDouble(antotall);
                        textTotal.setText(f1.format(antotall));


                        Double dut = Double.parseDouble(f3.format(antotall));

                        databaseAccess.open();
                        databaseAccess.updatotalTOTALDUE(dut,CustID);

                        databaseAccess.open();
                        databaseAccess.updatotalTOTALDUEMaster(dut,CustID);


                        // textTax.setText(productData.get(0).get("Tax"));
                        String s1sew = productData.get(0).get("Tax");
                        Double dsesew = Double.parseDouble(s1sew);
                        Double Tax = Double.parseDouble(f3.format(dsesew));

                        textTax.setText(f1.format(dsesew));

                        databaseAccess.open();
                        databaseAccess.updataxt(Tax,CustID);



                        //  txtMY4.setText( String.valueOf(  Integer.valueOf(strM4))  +"-" + strYear);
                        // txtMY3.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
                        txtMY2.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
                        txtMY1.setText(String.valueOf(  Integer.valueOf(strM4)-2)  +"-" + strYear);
                        txtDateWarning.setText(String.valueOf( "25-" + Integer.valueOf(strM4))  +"-" + strYear);



                        //textMret.setText(productData.get(0).get("Mrent"));
                        String s1sewe = productData.get(0).get("Mrent");
                        Double dsesewe = Double.parseDouble(s1sewe);
                        textMret.setText(f1.format(dsesewe));


                        String dtStarts = productData.get(0).get("PRESDATE");
                        dts = dtStarts.substring(8,10) +"/" +dtStarts.substring(5, 7) +"/" +dtStarts.substring(0, 4);
                        txt_PRESDATEse.setText(dts);

//                        String dtStart = productData.get(0).get("PREVDATE");
//                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
//                        txt_PREVDATE.setText(dt);

                        txtKheiID=productData.get(0).get("AREACODE").trim();
                        databaseAccess.open();
                        String Khet_name = databaseAccess.getVillName(txtKheiID.toString());
                        txtAREACODE.setText(Khet_name);
                        // txt_RATRIs.setText(productData.get(0).get("KhetID"));
                        ratrid = productData.get(0).get("RATRID");

                    }



                    databaseAccess.open();
                    List<HashMap<String, String>> Data;
                    Data = databaseAccess.getRATEbb1();
                    if (Data.size() > 0) {

                        if(cons<=5){
                            txtConsumption.setText("5"+" ມ³");
                            text_view14.setText("5");
                            rate1 = Data.get(0).get("RATE1");
                            double rat1 = Double.parseDouble(rate1);
                            double consup = Double.parseDouble(consumption);

                            double suobject = 5*rat1;

                            text1.setText(f1.format(rat1));
                            text_view16.setText(f1.format(suobject));

                            String rate2 = Data.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19.setText(f1.format(rat2));

                            String rate3 = Data.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28.setText(f1.format(rat3));

                            slab1 = Data.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);
                            // text_view14.setText(f1.format(sla));
                            text_view13.setText("0"+"-"+f1.format(sla)+" ມ³");

                            String slab2 = Data.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            text_view27.setText(f1.format(sla1));

                            double bb = sla1-sla;
                            //   String asv = String.valueOf(bb);
                            //  text_view18.setText(f1.format(bb));
                            text_view17.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26.setText(">"+f1.format(sla1)+"ມ³");


                            bbb1.setVisibility(View.VISIBLE);
                            bbb2.setVisibility(View.GONE);
                            bbb3.setVisibility(View.GONE);

                        }else if (cons<=7 && cons>5){
                            txtConsumption.setText(f1.format(cons)+" ມ³");
                            text_view14.setText(f1.format(cons));
                            rate1 = Data.get(0).get("RATE1");
                            double rat1 = Double.parseDouble(rate1);
                            double consup = Double.parseDouble(consumption);

                            double suobject = consup*rat1;

                            text1.setText(f1.format(rat1));
                            text_view16.setText(f1.format(suobject));

                            String rate2 = Data.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19.setText(f1.format(rat2));

                            String rate3 = Data.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28.setText(f1.format(rat3));

                            slab1 = Data.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);
                            // text_view14.setText(f1.format(sla));
                            text_view13.setText("0"+"-"+f1.format(sla)+" ມ³");

                            String slab2 = Data.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            text_view27.setText(f1.format(sla1));

                            double bb = sla1-sla;
                            //   String asv = String.valueOf(bb);
                            //  text_view18.setText(f1.format(bb));
                            text_view17.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26.setText(">"+f1.format(sla1)+"ມ³");


                            bbb1.setVisibility(View.VISIBLE);
                            bbb2.setVisibility(View.GONE);
                            bbb3.setVisibility(View.GONE);

                        }else if (cons>7 && cons<=15){
                            txtConsumption.setText(f1.format(cons)+" ມ³");
                            double seven = 7;
                            rate1 = Data.get(0).get("RATE1");
                            double rat1 = Double.parseDouble(rate1);
                            double consup = Double.parseDouble(consumption);

                            double suobject = seven*rat1;

                            text1.setText(f1.format(rat1));
                            text_view16.setText(f1.format(suobject));

                            String rate2 = Data.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19.setText(f1.format(rat2));

                            String rate3 = Data.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28.setText(f1.format(rat3));

                            slab1 = Data.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);
                            // text_view14.setText(f1.format(sla));
                            text_view13.setText("0"+"-"+f1.format(sla)+" ມ³");

                            String slab2 = Data.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            text_view27.setText(f1.format(sla1));

                            double bb = sla1-sla;
                            //   String asv = String.valueOf(bb);
                            text_view17.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26.setText(">"+f1.format(sla1)+"ມ³");

                            double jecttowe = cons-seven;

                            text_view18.setText(f1.format(jecttowe));

                            double subjectrate2 = rat2*jecttowe;
                            text_view20.setText(f1.format(subjectrate2));

                            text_view14.setText(f1.format(seven));

                            bbb1.setVisibility(View.VISIBLE);
                            bbb2.setVisibility(View.VISIBLE);
                            bbb3.setVisibility(View.GONE);

                        }else if (cons>15){
                            txtConsumption.setText(f1.format(cons)+" ມ³");
                            double seven = 7;
                            rate1 = Data.get(0).get("RATE1");
                            double rat1 = Double.parseDouble(rate1);
                            double consup = Double.parseDouble(consumption);

                            double suobject = seven*rat1;

                            text1.setText(f1.format(rat1));
                            text_view16.setText(f1.format(suobject));

                            String rate2 = Data.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19.setText(f1.format(rat2));

                            String rate3 = Data.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28.setText(f1.format(rat3));



                            slab1 = Data.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);
                            // text_view14.setText(f1.format(sla));
                            text_view13.setText("0"+"-"+f1.format(sla)+" ມ³");

                            String slab2 = Data.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);

                            double bbject = cons-sla1;
                            double aaject = rat3*bbject;

                            text_view27.setText(f1.format(bbject));
                            text_view29.setText(f1.format(aaject));

                            double bb = sla1-sla;
                            //   String asv = String.valueOf(bb);
                            text_view17.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26.setText(">"+f1.format(sla1)+"ມ³");

                            double jecttowe = 8;

                            text_view18.setText(f1.format(jecttowe));

                            double subjectrate2 = rat2*jecttowe;
                            text_view20.setText(f1.format(subjectrate2));

                            text_view14.setText(f1.format(seven));

                            bbb1.setVisibility(View.VISIBLE);
                            bbb2.setVisibility(View.VISIBLE);
                            bbb3.setVisibility(View.VISIBLE);
                        }
                    }


                    databaseAccess.open();
                    List<HashMap<String, String>> Datas;
                    Datas = databaseAccess.getoffice();
                    if (Datas.size() > 0) {
                        text.setText(Datas.get(0).get("Office_Name"));
                        office_name = Datas.get(0).get("Office_Name");
                        text3.setText("ເບີໂທ: "+Datas.get(0).get("Tel"));

                    }



                    databaseAccess.open();
                    databaseAccess.updateUploads(CustID);

                    databaseAccess.open();
                    databaseAccess.updatemaone(CustID);



                }catch (Exception ex) {
                //    Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
                }

                view.setDrawingCacheEnabled(true);
                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                view.buildDrawingCache(true);
                mView = view;
                doPhotoPrint();
            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffss.setText(Usr_id1);
                try {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestbillActivity.this);
                    databaseAccess.open();
                    List<HashMap<String, String>> Get_Date;
                    Get_Date = databaseAccess.getAllWaterViews(CustID.toString());
                    f1 = new DecimalFormat("#,###");
                    f3 = new DecimalFormat("####");
                    if (Get_Date.size() > 0) {

                        String strZone=Get_Date.get(0).get("ZONE");
                        databaseAccess.open();
                        String strZoneName = databaseAccess.getZoneName(strZone.toString());
                        txt_ZONEss.setText("ເມືອງ "+strZoneName);


                        String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
                        String strM4 = new SimpleDateFormat("MM", Locale.getDefault()).format( new Date());
                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());

                        txt_Datess.setText(currentDate);

                        txt_ACCOUNT.setText(Get_Date.get(0).get("ACCOUNT"));
                        txt_NAME.setText(Get_Date.get(0).get("NAME"));

                        txtKheiID=Get_Date.get(0).get("AREACODE").trim();
                        databaseAccess.open();
                        String Khet_name = databaseAccess.getVillName(txtKheiID.toString());
                        txt_AREACODE.setText(Khet_name);

                        txt_KhetIDsss.setText(Get_Date.get(0).get("RATRID"));

                        String dtStart = Get_Date.get(0).get("PREVDATE");
                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
                        txt_PREVDATEss.setText(dt);

                        txt_PREVREADss.setText(Get_Date.get(0).get("PREVREAD"));

                        String dtStarts = Get_Date.get(0).get("PRESDATE");
                        String dts = dtStarts.substring(8,10) +"/" +dtStarts.substring(5, 7) +"/" +dtStarts.substring(0, 4);
                        txt_PRESDATEsess.setText(dts);

                        txt_PRESREADsss.setText(Get_Date.get(0).get("PRESREAD"));

                        //txt_Tcode.setText(productData.get(0).get("Tcode"));
                        txt_MTRNUMBERss.setText(Get_Date.get(0).get("MTRNUMBER"));





                        //textWater.setText(productData.get(0).get("waterBill"));
                        String s1sewe1 = Get_Date.get(0).get("waterBill");
                        Double dsesewe1 = Double.parseDouble(s1sewe1);
                        text_Waters.setText(f1.format(dsesewe1));
                        //  text_view16.setText(f1.format(dsesewe1));



                        //textMret.setText(productData.get(0).get("Mrent"));
                        String s1sewe = Get_Date.get(0).get("Mrent");
                        Double dsesewe = Double.parseDouble(s1sewe);
                        textMrets.setText(f1.format(dsesewe));



                        // textTax.setText(productData.get(0).get("Tax"));
                        String s1sew = Get_Date.get(0).get("Tax");
                        Double dsesew = Double.parseDouble(s1sew);
                        text_Taxs.setText(f1.format(dsesew));



                        //textTotalBill.setText(productData.get(0).get("Total_Bill"));
                        String s1se = Get_Date.get(0).get("Total_Bill");
                        Double dsese = Double.parseDouble(s1se);
                        textTotalBills.setText(f1.format(dsese));

                        Double talosdb = Double.parseDouble(f3.format(dsese));

                        databaseAccess.open();
                        databaseAccess.updatotal_Bill(talosdb,CustID);

                        //textDue.setText(productData.get(0).get("TOTALDUE"));
                        String s1s = Get_Date.get(0).get("Arrears2");

                        Double dses = Double.parseDouble(s1s);
                        textDues.setText(f1.format(dses));

                        Double rs = Double.parseDouble(f3.format(dses));
                        databaseAccess.open();
                        databaseAccess.updateArrearsMaster(rs, CustID);


                        // textTotal.setText(productData.get(0).get("TOTALDUE1"));
                        String s1 = Get_Date.get(0).get("TOTALDUE1");
                        Double dss = Double.parseDouble(s1);
                        textTotals.setText(f1.format(dss));


                        Double dus = Double.parseDouble(f3.format(dss));

                        databaseAccess.open();
                        databaseAccess.updatotalTOTALDUE(dus,CustID);

                        databaseAccess.open();
                        databaseAccess.updatotalTOTALDUEMaster(dus,CustID);

                        //  strBarcode=strM+strYear;
                        //  bar_number.setText(strBarcode);
                        //   bar_number.setText(strBarcode);
                        bar_numbers.setText(Get_Date.get(0).get("ACCOUNT"));


                     /* txtMY4.setText( String.valueOf(  Integer.valueOf(strM4))  +"-" + strYear);
                        txtMY3.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);*/
                        txtMY2.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
                        txtMY1.setText(String.valueOf(  Integer.valueOf(strM4)-2)  +"-" + strYear);
                        txtDateWarnings.setText(String.valueOf( "25-" + Integer.valueOf(strM4))  +"-" + strYear);

                        /*
                        textPrvWater.setText("0.0");
                        textSEWER1.setText(productData.get(0).get("Sewage"));
                        String strAcc=productData.get(0).get("ACCOUNT");
                        txtBillNo.setText("ໃບບີນແຈ້ງຄ່ານ້ຳປະປາ:"+strAcc+strM+strYear);
                        //   txt_RATRIs.setText(productData.get(0).get("KhetID"));
                        ratrid = productData.get(0).get("RATRID");
                        */


                    }

                    DatabaseAccess databaseAccesss = DatabaseAccess.getInstance(BillTestbillActivity.this);
                    databaseAccesss.open();
                    List<HashMap<String, String>> RATES;
                    RATES = databaseAccesss.getRATEs();
                    if (RATES.size() > 0) {

                        String consumption = Get_Date.get(0).get("Consumption");
                        Double cons = Double.parseDouble(consumption);

                        text_view14s.setText(f1.format(cons)+"ມ³");

                        if (cons<=5){
                            String rate1 = RATES.get(0).get("RATE1");
                            Double rat1 = Double.parseDouble(rate1);
                            text_view15s.setText(f1.format(rat1));
                            double cject = rat1*5;
                            txtConsumptionss.setText("5"+" ມ³");
                            text_view14s.setText("5");
                            text_view16s.setText(f1.format(cject));
                            String rate2 = RATES.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19s.setText("0.00");

                            String rate3 = RATES.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28s.setText("0.00");

                            String slab1 = RATES.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);

                            text_view13s.setText("0"+"-"+f1.format(sla)+" ມ³");

                            String slab2 = RATES.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            //  text_view27s.setText(f1.format(sla1));


                            double bb = sla1-sla;

                            text_view18s.setText("0");
                            text_view17s.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26s.setText(">"+f1.format(sla1)+"ມ³");


                            njectb.setVisibility(View.VISIBLE);
                            njects.setVisibility(View.GONE);
                            mbjects.setVisibility(View.GONE);


                        }else if (cons<=7 && cons>5) {
                            txtConsumptionss.setText(cons+" ມ³");
                            String rate1 = RATES.get(0).get("RATE1");
                            double ratw = Double.parseDouble(rate1);
                            text_view15s.setText(f1.format(ratw));

                            double toject = ratw*cons;
                            text_view16s.setText(f1.format(toject));

                            text_view14s.setText(f1.format(cons));

                            String rate2 = RATES.get(0).get("RATE2");
                            Double rat2 = Double.parseDouble(rate2);
                            text_view19s.setText(f1.format(rat2));

                            String rate3 = RATES.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28s.setText(f1.format(rat3));

                            String slab1 = RATES.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);

                            text_view13s.setText("0"+"-"+f1.format(sla)+" ມ³");


                            String slab2 = RATES.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            //  text_view27s.setText(f1.format(sla1));


                            double bb = sla1-sla;

                            text_view18s.setText(f1.format(bb));
                            text_view17s.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26s.setText(">"+f1.format(sla1)+"ມ³");

                            njectb.setVisibility(View.VISIBLE);
                            njects.setVisibility(View.GONE);
                            mbjects.setVisibility(View.GONE);


                        }else if (cons>7 && cons<=15){
                            txtConsumptionss.setText(cons+" ມ³");
                            double sumject= 7;
                            String rate1 = RATES.get(0).get("RATE1");
                            double ratws = Double.parseDouble(rate1);
                            text_view15s.setText(f1.format(ratws));

                            double toject = ratws*sumject;
                            text_view16s.setText(f1.format(toject));

                            text_view14s.setText(f1.format(sumject));


                            double requre = cons-sumject;
                            text_view18s.setText(f1.format(requre));



                            String rate2 = RATES.get(0).get("RATE2");
                            double rateject = Double.parseDouble(rate2);
                            text_view19s.setText(f1.format(rateject));

                            double hosject = rateject*requre;
                            text_view20s.setText(f1.format(hosject));

                            String rate3 = RATES.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28s.setText(f1.format(rat3));

                            String slab1 = RATES.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);

                            text_view13s.setText("0"+"-"+f1.format(sla)+" ມ³");


                            String slab2 = RATES.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);
                            //  text_view27s.setText(f1.format(sla1));


                            double bb = sla1-sla;

                            text_view17s.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26s.setText(">"+f1.format(sla1)+"ມ³");

                            njectb.setVisibility(View.VISIBLE);
                            njects.setVisibility(View.VISIBLE);
                            mbjects.setVisibility(View.GONE);
                        }else if (cons>15){

                            txtConsumptionss.setText(cons+" ມ³");
                            double sumject= 7;
                            String rate1 = RATES.get(0).get("RATE1");
                            double ratws = Double.parseDouble(rate1);
                            text_view15s.setText(f1.format(ratws));

                            double toject = ratws*sumject;
                            text_view16s.setText(f1.format(toject));

                            text_view14s.setText(f1.format(sumject));


                            double requre = cons-sumject;

                            double Objectq = 8;

                            text_view18s.setText(f1.format(Objectq));


                            String rate2 = RATES.get(0).get("RATE2");
                            double rateject = Double.parseDouble(rate2);
                            text_view19s.setText(f1.format(rateject));

                            double Objecty = rateject*Objectq;
                            text_view20s.setText(f1.format(Objecty));


                            String rate3 = RATES.get(0).get("RATE3");
                            Double rat3 = Double.parseDouble(rate3);
                            text_view28s.setText(f1.format(rat3));

                            String slab1 = RATES.get(0).get("SLAB1");
                            Double sla = Double.parseDouble(slab1);

                            text_view13s.setText("0"+"-"+f1.format(sla)+" ມ³");


                            String slab2 = RATES.get(0).get("SLAB2");
                            Double sla1 = Double.parseDouble(slab2);

                            double Objectrq = cons-15;

                            text_view27s.setText(f1.format(Objectrq));

                            double Objectvb = rat3*Objectrq;
                            text_view29s.setText(f1.format(Objectvb));

                            double bb = sla1-sla;

                            text_view17s.setText(f1.format(bb)+"-"+f1.format(sla1)+"ມ³");
                            text_view26s.setText(">"+f1.format(sla1)+"ມ³");

                            njectb.setVisibility(View.VISIBLE);
                            njects.setVisibility(View.VISIBLE);
                            mbjects.setVisibility(View.VISIBLE);
                        }
                    }


                    databaseAccess.open();
                    databaseAccess.updateUploads(CustID);

                    databaseAccess.open();
                    databaseAccess.updatemaone(CustID);
                }catch (Exception ex) {
                  //  Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
                }

                views.setDrawingCacheEnabled(true);
                views.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                views.layout(0, 0, views.getMeasuredWidth(), views.getMeasuredHeight());
                views.buildDrawingCache(true);
                mView2 = views;
                doPhotoPrints();
            }
        });






        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);
                //change name

         /*       try {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestActivity.this);
                    databaseAccess.open();
                    List<HashMap<String, String>> productData;
                    productData = databaseAccess.getAllWaterpritse(CustID.toString());
                    f1 = new DecimalFormat("#,###");
                    if (productData.size() > 0) {
                        String strAcc=productData.get(0).get("ACCOUNT");
                        String strZone=productData.get(0).get("ZONE");
//                        //  String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;
//
                        txt_view3.setText(productData.get(0).get("ACCOUNT"));
                        txt_view6.setText(productData.get(0).get("MTRNUMBER"));

                        txt_view1.setText(productData.get(0).get("NAME"));
                        txt_view4.setText(productData.get(0).get("Tcode"));


                        //   txt_view8.setText(productData.get(0).get("TOTALDUE1"));
                        String s1g = productData.get(0).get("TOTALDUE1");
                        Double dssf = Double.parseDouble(s1g);
                        txt_view8.setText(f1.format(dssf));

                        txt_view7.setText(Usr_id1);
                        // txt_view9.setText(productData.get(0).get("TOTALDUE1"));
                        String s1 = productData.get(0).get("TOTALDUE1");
                        Double dss = Double.parseDouble(s1);
                        txt_view9.setText(f1.format(dss));
                        txtKheiID=productData.get(0).get("AREACODE").trim();





                        String dtStart = productData.get(0).get("Paid_date");
                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
                        txt_view10.setText(dt);


                        txt_view5.setText(strM+strYear);
                        databaseAccess.open();
                        String Khet_name = databaseAccess.getVillNamesss(txtKheiID.toString());
                        txt_view2.setText(Khet_name);
//
                        databaseAccess.open();
                        String strZoneName = databaseAccess.getZoneNamessss(strZone.toString());
                        txt_view0.setText("ເມືອງ "+strZoneName);
//                txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");



                        databaseAccess.open();
                        List<HashMap<String, String>> Datas;
                        Datas = databaseAccess.getoffice();
                        if (Datas.size() > 0) {
                            office.setText(Datas.get(0).get("Office_Name"));
                            text3s.setText("ເບີໂທ: "+Datas.get(0).get("Tel")+" ສາຍດ່ວນ:"+" 023 1169");

                        }


                    }

                }catch (Exception ex) {
                    Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
                }


*/


                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestbillActivity.this);
                databaseAccess.open();
                List<HashMap<String, String>> productDatas;
                productDatas = databaseAccess.getAllWaterViewrtd(CustID.toString());

                String pay=productDatas.get(0).get("pay");

                    ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo nInfo = cm.getActiveNetworkInfo();
                    boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

                    if(isconnected) {
                        if (pay.equals("1")){
                            Toasty.error(BillTestbillActivity.this, "ບີນນີ້ຊຳລະເງີນແລ້ວ!", Toast.LENGTH_SHORT).show();
                        }else if (pay.equals("0")){
                            Intent intent = new Intent(BillTestbillActivity.this, Payment2Activity.class);
                            startActivity(intent);
                        }

                    }else {
                        Toasty.error(BillTestbillActivity.this, "ກະລຸນາເຊື່ອມຕໍ່ອີນເຕີເນັດ", Toast.LENGTH_SHORT).show();
                    }



//                view1.setDrawingCacheEnabled(true);

//                view1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//
//                view1.layout(0, 0, view1.getMeasuredWidth(), view1.getMeasuredHeight());
//
//                view1.buildDrawingCache(true);
//
//                mView1 = view1;
                //  doPhotoPrint1();
            }
        });
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap bitmap = mView.getDrawingCache();

        //print
        photoPrinter.printBitmap("image.png_test_print", bitmap, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                //   Toast.makeText(BillTestActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();
                Toasty.success(BillTestbillActivity.this, "ພິມສຳເລັດ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void doPhotoPrints() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        //this is used for print drawable image
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.image);

        // get the layout in a bitmap
        Bitmap bitmap = mView2.getDrawingCache();

        //print
        photoPrinter.printBitmap("image.png_test_print", bitmap, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                //   Toast.makeText(BillTestActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();
                Toasty.success(BillTestbillActivity.this, "ພິມສຳເລັດ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void doPhotoPrint1() {
        PrintHelper photoPrinter1 = new PrintHelper(this);
        photoPrinter1.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        //this is used for print drawable image
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//        R.drawable.image);

        // get the layout in a bitmap
        Bitmap bitmap1 = mView1.getDrawingCache();

        //print
        photoPrinter1.printBitmap("image.png_test_print", bitmap1, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                // Toast.makeText(BillTestActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();
                Toasty.success(BillTestbillActivity.this, "ພິມສຳເລັດ", Toast.LENGTH_SHORT).show();

            }
        });

    }
    // code for button click
    //  public void onClick(View view) {
    //   doPhotoPrint();
    // }


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
