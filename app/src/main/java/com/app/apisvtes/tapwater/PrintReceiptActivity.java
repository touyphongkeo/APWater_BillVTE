package com.app.apisvtes.tapwater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.utils.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.app.apisvtes.pdf_report.BarCodeEncoder;
import com.app.apisvtes.pdf_report.TemplatePDF;
import com.app.apisvtes.utils.WoosimPrnMng;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;


import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.CustID;

public class PrintReceiptActivity extends BaseActivity {
    String txtKheiID="";
    String strBarcode="";
    TextView editText;
    ImageView imageView,bar_images;
    TextView txtUpdateExpense,txtUpdateExpense2;
    TextView bar_number,txtMTRNUMBER, txtBillNo,txtDate,txtZONE, txtAccount, txtTcode, txtCusName,txtAREACODE, txtRate, txtPreVread, txtPreSread,txtConsumption;
    TextView txtMY1,txtMY2,txtMY3,txtMY4,txtDateWarning;

    TextView  textPrvWater, textWater,textMret,textSEWER1,textTax,textTotalBill,textDue,textTotal;

    private TemplatePDF templatePDF;
    Bitmap bm = null;


    private static final int REQUEST_CONNECT = 100;
    private WoosimPrnMng mPrnMng = null;
    DecimalFormat f;
    Date d;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bills);


        txtUpdateExpense2=findViewById(R.id.txt_update_expense2);
        txtAccount = findViewById(R.id.txt_ACCOUNT);
        txtMTRNUMBER= findViewById(R.id.txt_MTRNUMBER);
         txtCusName = findViewById(R.id.txt_NAME);
        txtAREACODE = findViewById(R.id.txt_AREACODE);
        txtBillNo= findViewById(R.id.txt_BillNo);
        txtDate= findViewById(R.id.txt_Date);
        txtZONE= findViewById(R.id.txt_ZONE);
        bar_images= findViewById(R.id.bar_image);
        bar_number= findViewById(R.id.bar_number);
        txtConsumption= findViewById(R.id.txt_Consumption);

        txtMY1= findViewById(R.id.text_BillMY1);
        txtMY2= findViewById(R.id.text_BillMY2);
        txtMY3= findViewById(R.id.text_BillMY3);
        txtMY4= findViewById(R.id.text_BillMY4);
        txtDateWarning= findViewById(R.id.text_DateWarning);

        textPrvWater= findViewById(R.id.text_PrvWater);
        textWater= findViewById(R.id.text_Water);
        textMret= findViewById(R.id.text_Mret);
        textSEWER1= findViewById(R.id.text_SEWER1);
        textTax= findViewById(R.id.text_Tax);
        textTotalBill= findViewById(R.id.text_TotalBill);
        textDue= findViewById(R.id.text_Due);
        textTotal= findViewById(R.id.text_Total);






     //   txtUpdateExpense=findViewById(R.id.txt_update_expense);
        getSupportActionBar().setTitle("");
        getSupportActionBar().hide();
     //   getSupportActionBar().setIcon(R.drawable.pos);
       // getSupportActionBar().setBackgroundDrawable(nothing);

        try {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PrintReceiptActivity.this);
            databaseAccess.open();
            List<HashMap<String, String>> productData;
            productData = databaseAccess.getAllWaterView(CustID.toString());


            if (productData.size() > 0) {
                String strAcc=productData.get(0).get("ACCOUNT");
                String strZone=productData.get(0).get("ZONE");

                String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
                String strM4 = new SimpleDateFormat("MM", Locale.getDefault()).format( new Date());
                String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;
                txtAccount.setText(productData.get(0).get("ACCOUNT"));
                txtMTRNUMBER.setText(productData.get(0).get("MTRNUMBER"));
                txtCusName.setText(productData.get(0).get("NAME"));
                txtKheiID=productData.get(0).get("AREACODE");
                txtBillNo.setText(strAcc+strM+strYear);
                strBarcode=strAcc+strM+strYear;
                txtDate.setText(currentDate);

                textPrvWater.setText("0.0");
                textWater.setText(productData.get(0).get("waterBill"));
                textMret.setText(productData.get(0).get("Mrent"));
                textSEWER1.setText(productData.get(0).get("Sewage"));
                textTax.setText(productData.get(0).get("Tax"));
                textTotalBill.setText(productData.get(0).get("Total_Bill"));



                textDue.setText(productData.get(0).get("Surcharge"));

                textTotal.setText(productData.get(0).get("TOTALDUE"));


                txtMY4.setText( String.valueOf(  Integer.valueOf(strM4))  +"-" + strYear);
                txtMY3.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
                txtMY2.setText(String.valueOf(  Integer.valueOf(strM4)-1)  +"-" + strYear);
                txtMY1.setText(String.valueOf(  Integer.valueOf(strM4)-2)  +"-" + strYear);

                txtDateWarning.setText(String.valueOf( "30-" + Integer.valueOf(strM4))  +"-" + strYear);


                databaseAccess.open();
                String Khet_name = databaseAccess.getVillName(txtKheiID.toString());
                txtAREACODE.setText(Khet_name);


                bar_number.setText(strBarcode);

                databaseAccess.open();
                String strZoneName = databaseAccess.getZoneName(strZone.toString());
                txtZONE.setText(strZoneName);

                txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");

            }

        }catch (Exception ex) {
            Toasty.error(this, Objects.requireNonNull(ex.getMessage()), Toast.LENGTH_SHORT).show();
        }


        txtUpdateExpense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now = new Date();
               // android.text.format.DateFormat.format("hh:mm:ss", now);
                templatePDF = new TemplatePDF(getApplicationContext());
                templatePDF.openDocument();

                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    String mPath = Environment.getExternalStorageDirectory() + File.separator + "SmartPos/test.jpg";
                  //  txtUpdateExpense2.vi
                    txtUpdateExpense2.setVisibility(View.INVISIBLE);
                  //  txtUpdateExpense.setVisibility(View.INVISIBLE);
                    // create bitmap screen capture
                    View v1 = getWindow().getDecorView().getRootView();
                    v1.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                    v1.setDrawingCacheEnabled(false);

                    File imageFile = new File(mPath);

                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                    outputStream.flush();
                    outputStream.close();

                    templatePDF = new TemplatePDF(getApplicationContext());
                    templatePDF.openDocument();


                    BarCodeEncoder qrCodeEncoder = new BarCodeEncoder();
                    try {
                        bm = qrCodeEncoder.encodeAsBitmap(strBarcode, BarcodeFormat.CODE_128, 150, 50);
                        bar_images.setImageBitmap(bm);
                    } catch (WriterException e) {
                        Log.d("Data", e.toString());
                    }

                    Bitmap selectedImage = BitmapFactory.decodeFile(mPath);

                    //    templatePDF.addImageLogo(BitmapFactory.decodeResource(getResources(), R.drawable.pos2));
                    //  templatePDF.createTable(header, getOrdersData());
                    //  templatePDF.addRightParagraph(longText);
                    //  templatePDF.addImage(bm);
                    templatePDF.addImageBody(selectedImage);
                    //   templatePDF.addImage(bm);
                    templatePDF.closeDocument();
                    templatePDF.viewPDF();


                //    Intent intent = new Intent(PrintReceiptActivity.this,MenuActivity.class);
                 //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               //     startActivity(intent);

                } catch (Throwable e) {
                    // Several error may come out with file handling or DOM
                    Toasty.error(PrintReceiptActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    //  e.printStackTrace();
                }

            }
        });
    }


}