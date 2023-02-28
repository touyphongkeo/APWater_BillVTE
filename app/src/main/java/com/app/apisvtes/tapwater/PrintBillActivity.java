package com.app.apisvtes.tapwater;

import android.annotation.SuppressLint;
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
import com.app.apisvtes.pdf_report.BarCodeEncoder;
import com.app.apisvtes.pdf_report.TemplatePDF;
import com.app.apisvtes.utils.BaseActivity;
import com.app.apisvtes.utils.WoosimPrnMng;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static com.app.apisvtes.conn.ClassLibs.CustID;

public class PrintBillActivity extends BaseActivity {
    String txtKheiID="";

    String CreateDate = "";
    String HH_YEAR = "";
    ImageView imageView,bar_images;
    TextView txtUpdateExpense,txtUpdateExpense2;
    TextView text2,amontmoney,KhetID,Tcode,edit_Date_of_birth,bar_number,Total_Bill,txtMTRNUMBER, txtBillNo,txtZONE, txtAccount, txtTcode, txtCusName,txtAREACODE, txtRate, txtPreVread, txtPreSread,txtConsumption;

    private TemplatePDF templatePDF;
    Bitmap bm = null;


    private static final int REQUEST_CONNECT = 100;
    private WoosimPrnMng mPrnMng = null;
    DecimalFormat f;
    Date d;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);
        txtUpdateExpense2=findViewById(R.id.txt_update_expense2);
     //   txtUpdateExpense=findViewById(R.id.txt_update_expense);
        txtAccount = findViewById(R.id.txt_ACCOUNT);
        txtMTRNUMBER= findViewById(R.id.txt_MTRNUMBER);
        txtCusName = findViewById(R.id.txt_NAME);
        txtAREACODE = findViewById(R.id.txt_AREACODE);
        txtBillNo= findViewById(R.id.txt_BillNo);
        Total_Bill= findViewById(R.id.Total_Bill);
        Tcode= findViewById(R.id.Tcode);
        KhetID= findViewById(R.id.KhetID);
        bar_images= findViewById(R.id.bar_image);
        edit_Date_of_birth = findViewById(R.id.edit_Date_of_birth);
        txtConsumption= findViewById(R.id.txt_Consumption);
       // amontmoney= findViewById(R.id.amontmoney);
        text2= findViewById(R.id.text2);
        getSupportActionBar().setTitle("");
        getSupportActionBar().hide();
     //   getSupportActionBar().setIcon(R.drawable.pos);
       // getSupportActionBar().setBackgroundDrawable(nothing);

        String System_datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        HH_YEAR = System_datetime.substring(0,4);
        edit_Date_of_birth.setText(System_datetime.substring(0,10));
        CreateDate = System_datetime;
        edit_Date_of_birth.setEnabled(false);


        try {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PrintBillActivity.this);
            databaseAccess.open();
            List<HashMap<String, String>> productData;
            productData = databaseAccess.getAllWaterpritse(CustID.toString());

            if (productData.size() > 0) {
                String strAcc=productData.get(0).get("ACCOUNT");
                String strZone=productData.get(0).get("ZONE");
              //  String currentDate = new SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(new Date());
                String strM=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                String strYear=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());;

                txtAccount.setText(productData.get(0).get("ACCOUNT"));
                txtMTRNUMBER.setText(productData.get(0).get("MTRNUMBER"));
                txtCusName.setText(productData.get(0).get("NAME"));
                Tcode.setText(productData.get(0).get("Tcode"));
                Total_Bill.setText(productData.get(0).get("TOTALDUE"));
                KhetID.setText(productData.get(0).get("KhetID"));
            //    amontmoney.setText(productData.get(0).get("TOTALDUE"));
                txtKheiID=productData.get(0).get("AREACODE");
                txtBillNo.setText(strAcc+strM+strYear);
                databaseAccess.open();
                String Khet_name = databaseAccess.getVillName(txtKheiID.toString());
                txtAREACODE.setText(Khet_name);

                databaseAccess.open();
                String strZoneName = databaseAccess.getZoneName(strZone.toString());
                text2.setText(strZoneName);
//                txtConsumption.setText(productData.get(0).get("Consumption")+" ມ³");

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
                        bm = qrCodeEncoder.encodeAsBitmap("apwater", BarcodeFormat.CODE_128, 600, 300);
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
                    Toasty.error(PrintBillActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    //  e.printStackTrace();
                }

            }
        });
    }


}