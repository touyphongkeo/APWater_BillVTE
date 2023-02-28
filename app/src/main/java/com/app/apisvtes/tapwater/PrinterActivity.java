package com.app.apisvtes.tapwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.print.PrintHelper;

import com.app.apisvtes.R;
import com.app.apisvtes.pdf_report.TemplatePDF;

import java.text.DecimalFormat;

import es.dmoral.toasty.Toasty;

public class PrinterActivity extends AppCompatActivity {

    String txtKheiID="";
    String strBarcode="";
    TextView editText;
    ImageView imageView,bar_images;
    TextView txtUpdateExpense;
    TextView bar_number,txt_KhetID,txt_RATRIs,txt_Tcode,txtMTRNUMBER,txt_PRESDATEse,txt_PREVDATE,txt_PREVREAD,txt_PRESREADs, txtBillNo,txtDate,txtZONE, txtAccount, txtTcode, txtCusName,txtAREACODE, txtRate, txtPreVread, txtPreSread,txtConsumption;
    TextView txtMY1,txtMY2,txtMY3,txtMY4,txtDateWarning;

    TextView  textPrvWater, textWater,textMret,textSEWER1,textTax,textTotalBill,textDue,textTotal;

    private TemplatePDF templatePDF;
    Bitmap bm = null;

    TextView text2,amontmoney,KhetID,Tcode,edit_Date_of_birth,Total_Bill;


    View mView,mView1;
    CardView button1,button2,button3;

    String CreateDate = "";
    String HH_YEAR = "";
    public DecimalFormat f1;
    TextView txt_view0,txt_view1,txt_view2,txt_view3,txt_view4,txt_view5,txt_view6,txt_view7,txt_view8,txt_view9,txt_view10;

    Animation fromtopbottom, fromtopbottomtwo, fromtopbottomthree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printerbill);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ລະບົບພິມບິນນ້ຳປະປາ");

        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
       
        //Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/notosanslaoui_regular.ttf");

        fromtopbottom = AnimationUtils.loadAnimation(this, R.anim.fromtopbottom);
        fromtopbottomtwo = AnimationUtils.loadAnimation(this, R.anim.fromtopbottomtwo);
        fromtopbottomthree = AnimationUtils.loadAnimation(this, R.anim.fromtopbottomthree);


        button1.startAnimation(fromtopbottom);
        button2.startAnimation(fromtopbottomtwo);
        button3.startAnimation(fromtopbottomthree);




        //  getSupportActionBar().hide();
        //get the layout of visiting card
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_printer_svk3, null);
        View contentView1 = inflater.inflate(R.layout.activity_printer_svk, null);
        View contentView2 = inflater.inflate(R.layout.activity_printer_svk2, null);


        //get the main view which will be printed
        RelativeLayout view = (RelativeLayout) contentView.findViewById(R.id.relatives);

        RelativeLayout view1 = (RelativeLayout) contentView1.findViewById(R.id.relatived);
        RelativeLayout view2 = (RelativeLayout) contentView2.findViewById(R.id.relative);

//        txtAccount = view.findViewById(R.id.txt_ACCOUNT);
//        txtMTRNUMBER= view.findViewById(R.id.txt_MTRNUMBER);
//        txtCusName = view.findViewById(R.id.txt_NAME);
//        txtAREACODE = view.findViewById(R.id.txt_AREACODE);
//        txtBillNo= view.findViewById(R.id.txt_BillNo);
//        txtDate= view.findViewById(R.id.txt_Date);
//        txtZONE= view.findViewById(R.id.txt_ZONE);
//        bar_images= view.findViewById(R.id.bar_image);
//        bar_number= view.findViewById(R.id.bar_number);
//        txtConsumption= view.findViewById(R.id.txt_Consumption);
//
//        txtMY1= view.findViewById(R.id.text_BillMY1);
//        txtMY2= view.findViewById(R.id.text_BillMY2);
//        txtMY3= view.findViewById(R.id.text_BillMY3);
//        txtMY4= view.findViewById(R.id.text_BillMY4);
//        txtDateWarning= view.findViewById(R.id.text_DateWarning);
//
//        textPrvWater= view.findViewById(R.id.text_PrvWater);
//        textWater= view.findViewById(R.id.text_Water);
//        textMret= view.findViewById(R.id.text_Mret);
//        textSEWER1= view.findViewById(R.id.text_SEWER1);
//        textTax= view.findViewById(R.id.text_Tax);
//        textTotalBill= view.findViewById(R.id.text_TotalBill);
//        textDue= view.findViewById(R.id.text_Due);
//        textTotal= view.findViewById(R.id.text_Total);
//        txt_PREVREAD = view.findViewById(R.id.txt_PREVREAD);
//        txt_PRESREADs = view.findViewById(R.id.txt_PRESREADs);
//        txt_PREVDATE = view.findViewById(R.id.txt_PREVDATE);
//        txt_PRESDATEse = view.findViewById(R.id.txt_PRESDATEse);
//        txt_KhetID = view.findViewById(R.id.txt_KhetID);
//        txt_Tcode = view.findViewById(R.id.txt_Tcode);
//        txt_RATRIs = view.findViewById(R.id.txt_RATRIs);
//
//
//
//        //ພາກສວນ view1
//        txt_view0 = view1.findViewById(R.id.text2);
//        txt_view1 = view1.findViewById(R.id.txt_NAME);
//        txt_view2 = view1.findViewById(R.id.txt_AREACODE);
//        txt_view3 = view1.findViewById(R.id.txt_ACCOUNT);
//        txt_view4 = view1.findViewById(R.id.Tcode);
//        txt_view5 = view1.findViewById(R.id.txt_BillNo);
//        txt_view6 = view1.findViewById(R.id.txt_MTRNUMBER);
//        txt_view7 = view1.findViewById(R.id.KhetID);
//        txt_view8 = view1.findViewById(R.id.Total_Bill);
//        txt_view9 = view1.findViewById(R.id.amontmoney);
//        txt_view10 = view1.findViewById(R.id.edit_Date_of_birth);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);

//                try {
//                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestActivity.this);
//                    databaseAccess.open();
//                    List<HashMap<String, String>> productData;
//                    productData = databaseAccess.getAllWaterView(CustID.toString());
//                    f1 = new DecimalFormat("#,###.0");
//
//                    if (productData.size() > 0) {
//                        String strAcc=productData.get(0).get("ACCOUNT");
//                        String strZone=productData.get(0).get("ZONE");
//
//                        String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
//                        String strM4 = new SimpleDateFormat("MM", Locale.getDefault()).format( new Date());
//                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
//                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
//
//                        txtAccount.setText(productData.get(0).get("ACCOUNT"));
//                        txtMTRNUMBER.setText(productData.get(0).get("MTRNUMBER"));
//                        txtCusName.setText(productData.get(0).get("NAME"));
//                        txt_PREVREAD.setText(productData.get(0).get("PREVREAD"));
//                        txt_PRESREADs.setText(productData.get(0).get("PRESREAD"));
//
//                        txt_KhetID.setText(productData.get(0).get("AREACODE"));
//                        txt_Tcode.setText(productData.get(0).get("Tcode"));
//
//                        txtKheiID=productData.get(0).get("AREACODE");
//                        txtBillNo.setText(strAcc+strM+strYear);
//
//                        strBarcode=strAcc+strM+strYear;
//                        txtDate.setText(currentDate);
//
//                        textPrvWater.setText("0.0");
//
//     //                 textWater.setText(productData.get(0).get("waterBill"));
//                        String s1sewe1 = productData.get(0).get("waterBill");
//                        Double dsesewe1 = Double.parseDouble(s1sewe1);
//                        textWater.setText(f1.format(dsesewe1));
//
//   //                   textMret.setText(productData.get(0).get("Mrent"));
//                        String s1sewe = productData.get(0).get("Mrent");
//                        Double dsesewe = Double.parseDouble(s1sewe);
//                        textMret.setText(f1.format(dsesewe));
//
//                        textSEWER1.setText(productData.get(0).get("Sewage"));
//
//                     // textTax.setText(productData.get(0).get("Tax"));
//                        String s1sew = productData.get(0).get("Tax");
//                        Double dsesew = Double.parseDouble(s1sew);
//                        textTax.setText(f1.format(dsesew));
//
//
//               //       textTotalBill.setText(productData.get(0).get("Total_Bill"));
//                        String s1se = productData.get(0).get("Total_Bill");
//                        Double dsese = Double.parseDouble(s1se);
//                        textTotalBill.setText(f1.format(dsese));
//
//
//                        //textDue.setText(productData.get(0).get("TOTALDUE"));
//                        String s1s = productData.get(0).get("TOTALDUE");
//                        Double dses = Double.parseDouble(s1s);
//                        textDue.setText(f1.format(dses));
//
//                     // textTotal.setText(productData.get(0).get("TOTALDUE1"));
//                        String s1 = productData.get(0).get("TOTALDUE1");
//                        Double dss = Double.parseDouble(s1);
//                        textTotal.setText(f1.format(dss));
//
//                        txtMY4.setText( String.valueOf(  Integer.valueOf(strM4))  +"-" + strYear);
//                        txtMY3.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
//                        txtMY2.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
//                        txtMY1.setText(String.valueOf(  Integer.valueOf(strM4)-2)  +"-" + strYear);
//                        txtDateWarning.setText(String.valueOf( "30-" + Integer.valueOf(strM4))  +"-" + strYear);
//
//
//
//
//                        String dtStart = productData.get(0).get("PREVDATE");
//                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
//                        txt_PREVDATE.setText(dt);
//
//                        String dtStarts = productData.get(0).get("PRESDATE");
//                        String dts = dtStarts.substring(8,10) +"/" +dtStarts.substring(5, 7) +"/" +dtStarts.substring(0, 4);
//                        txt_PRESDATEse.setText(dts);
//
//                        txt_RATRIs.setText(productData.get(0).get("KhetID"));
//
//                        databaseAccess.open();
//                        String Khet_name = databaseAccess.getVillName(txtKheiID.toString());
//                        txtAREACODE.setText(Khet_name);
//                        bar_number.setText(strBarcode);
//
//                        databaseAccess.open();
//                        String strZoneName = databaseAccess.getZoneName(strZone.toString());
//                        txtZONE.setText(strZoneName);
//
//                        txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");
//
//                    }
//
//                }catch (Exception ex) {
//                    Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
//                }



                //change name
                //  tv.setText("ເພັດສະໝອນ ສິລິວົງ");
                view.setDrawingCacheEnabled(true);
                // this is the important code
                // Without it the view will have a dimension of 0,0 and the bitmap will be null
                //set view hight, width
                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //layout(left, top, size from left to right, size from top to bottom)
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());


                //build the cache with autoscale enabled
                view.buildDrawingCache(true);

                //store the view for further use
                mView = view;
                doPhotoPrint();
            }
        });





        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);
                //change name

//                try {
//                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestActivity.this);
//                    databaseAccess.open();
//                    List<HashMap<String, String>> productData;
//                    productData = databaseAccess.getAllWaterpritse(CustID.toString());
//                    f1 = new DecimalFormat("#,###.00");
//                    if (productData.size() > 0) {
//                        String strAcc=productData.get(0).get("ACCOUNT");
//                        String strZone=productData.get(0).get("ZONE");
////                        //  String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
//                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
//                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;
////
//                        txt_view3.setText(productData.get(0).get("ACCOUNT"));
//                        txt_view6.setText(productData.get(0).get("MTRNUMBER"));
//
//                        txt_view1.setText(productData.get(0).get("NAME"));
//                        txt_view4.setText(productData.get(0).get("Tcode"));
//
//
//                   //   txt_view8.setText(productData.get(0).get("TOTALDUE1"));
//                        String s1g = productData.get(0).get("TOTALDUE1");
//                        Double dssf = Double.parseDouble(s1g);
//                        txt_view8.setText(f1.format(dssf));
//
//
//
//                        txt_view7.setText(productData.get(0).get("KhetID"));
//
//
//                     // txt_view9.setText(productData.get(0).get("TOTALDUE1"));
//                        String s1 = productData.get(0).get("TOTALDUE1");
//                        Double dss = Double.parseDouble(s1);
//                        txt_view9.setText(f1.format(dss));
//
//
//
//                        txtKheiID=productData.get(0).get("AREACODE");
//
//
//
//
//
//                        String dtStart = productData.get(0).get("Paid_date");
//                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
//                        txt_view10.setText(dt);
//
//
//                        txt_view5.setText(strAcc+strM+strYear);
//                        databaseAccess.open();
//                        String Khet_name = databaseAccess.getVillNamesss(txtKheiID.toString());
//                        txt_view2.setText(Khet_name);
////
//                        databaseAccess.open();
//                        String strZoneName = databaseAccess.getZoneNamessss(strZone.toString());
//                        txt_view0.setText(strZoneName);
////                txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");
//
//                    }
//
//                }catch (Exception ex) {
//                    Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
//                }



                //  tv.setText("ເພັດສະໝອນ ສິລິວົງ");
                view1.setDrawingCacheEnabled(true);
                // this is the important code
                // Without it the view will have a dimension of 0,0 and the bitmap will be null
                //set view hight, width
                view1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //layout(left, top, size from left to right, size from top to bottom)
                view1.layout(0, 0, view1.getMeasuredWidth(), view1.getMeasuredHeight());

                //build the cache with autoscale enabled
                view1.buildDrawingCache(true);

                //store the view for further use
                mView1 = view1;
                doPhotoPrint1();
            }
        });




        //==================================
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all the dynamic views which will be changed programmatically
                // TextView tv = (TextView) view.findViewById(R.id.textView3);
                //change name

//                try {
//                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(BillTestActivity.this);
//                    databaseAccess.open();
//                    List<HashMap<String, String>> productData;
//                    productData = databaseAccess.getAllWaterpritse(CustID.toString());
//                    f1 = new DecimalFormat("#,###.00");
//                    if (productData.size() > 0) {
//                        String strAcc=productData.get(0).get("ACCOUNT");
//                        String strZone=productData.get(0).get("ZONE");
////                        //  String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
//                        String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
//                        String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;
////
//                        txt_view3.setText(productData.get(0).get("ACCOUNT"));
//                        txt_view6.setText(productData.get(0).get("MTRNUMBER"));
//
//                        txt_view1.setText(productData.get(0).get("NAME"));
//                        txt_view4.setText(productData.get(0).get("Tcode"));
//
//
//                   //   txt_view8.setText(productData.get(0).get("TOTALDUE1"));
//                        String s1g = productData.get(0).get("TOTALDUE1");
//                        Double dssf = Double.parseDouble(s1g);
//                        txt_view8.setText(f1.format(dssf));
//
//
//
//                        txt_view7.setText(productData.get(0).get("KhetID"));
//
//
//                     // txt_view9.setText(productData.get(0).get("TOTALDUE1"));
//                        String s1 = productData.get(0).get("TOTALDUE1");
//                        Double dss = Double.parseDouble(s1);
//                        txt_view9.setText(f1.format(dss));
//
//
//
//                        txtKheiID=productData.get(0).get("AREACODE");
//
//
//
//
//
//                        String dtStart = productData.get(0).get("Paid_date");
//                        String dt = dtStart.substring(8,10) +"/" +dtStart.substring(5, 7) +"/" +dtStart.substring(0, 4);
//                        txt_view10.setText(dt);
//
//
//                        txt_view5.setText(strAcc+strM+strYear);
//                        databaseAccess.open();
//                        String Khet_name = databaseAccess.getVillNamesss(txtKheiID.toString());
//                        txt_view2.setText(Khet_name);
////
//                        databaseAccess.open();
//                        String strZoneName = databaseAccess.getZoneNamessss(strZone.toString());
//                        txt_view0.setText(strZoneName);
////                txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");
//
//                    }
//
//                }catch (Exception ex) {
//                    Toast.makeText(BillTestActivity.this, "", Toast.LENGTH_SHORT).show();
//                }



                //  tv.setText("ເພັດສະໝອນ ສິລິວົງ");
                view2.setDrawingCacheEnabled(true);
                // this is the important code
                // Without it the view will have a dimension of 0,0 and the bitmap will be null
                //set view hight, width
                view2.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //layout(left, top, size from left to right, size from top to bottom)
                view2.layout(0, 0, view2.getMeasuredWidth(), view2.getMeasuredHeight());

                //build the cache with autoscale enabled
                view2.buildDrawingCache(true);

                //store the view for further use
                mView1 = view2;
                doPhotoPrint1();
            }
        });












    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        //this is used for print drawable image
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.image);

        // get the layout in a bitmap
        Bitmap bitmap = mView.getDrawingCache();

        //print
        photoPrinter.printBitmap("image.png_test_print", bitmap, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                //   Toast.makeText(BillTestActivity.this, "Thank you!", Toast.LENGTH_SHORT).show();
                Toasty.success(PrinterActivity.this, "ພິມສຳເລັດ", Toast.LENGTH_SHORT).show();

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
                Toasty.success(PrinterActivity.this, "ພິມສຳເລັດ", Toast.LENGTH_SHORT).show();

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
