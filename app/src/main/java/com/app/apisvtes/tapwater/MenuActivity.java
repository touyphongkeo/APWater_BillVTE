package com.app.apisvtes.tapwater;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.apisvtes.LoginActivity;
import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.download.LoadWaterActivity;
import com.app.apisvtes.menudownload.dowlaodActivity;
import com.app.apisvtes.payments.Payment2Activity;
import com.app.apisvtes.repayment.Repayment_list_Activity;
import com.app.apisvtes.settings.backup.BackupActivity;
import com.app.apisvtes.uploads.UploadWaterActivity;
import com.app.apisvtes.utils.BaseActivity;
import com.app.apisvtes.utils.LocaleManager;
import com.app.apisvtes.utils.WoosimPrnMng;
import com.google.android.gms.ads.AdView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;


import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;
import static com.app.apisvtes.conn.ClassLibs.Usr_nm1;
import static com.app.apisvtes.conn.ClassLibs.fLogin;


public class MenuActivity extends BaseActivity {
    private WoosimPrnMng mPrnMng = null;
    public byte[] getBytes(String charsetName) {
        return new byte[0];
    }

    CardView testprinter, cardSalesReport,card_payment,  cardExpenseReport, cardGraphReport, cardPaymentReport,cardExpenseGraph,cardSettings;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    private AdView adView;
    ProgressBar progressBar2,progressBar_household1,progressBar_household2;
    TextView account;

    Animation fromtopbottom, fromtopbottomtwo, fromtopbottomthree;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuwater);

        fromtopbottom = AnimationUtils.loadAnimation(this, R.anim.fromtopbottom);
        fromtopbottomtwo = AnimationUtils.loadAnimation(this, R.anim.fromtopbottomtwo);
        fromtopbottomthree = AnimationUtils.loadAnimation(this, R.anim.fromtopbottomthree);


    //    getSupportActionBar().setHomeButtonEnabled(true); //for back button
   //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
   //     getSupportActionBar().setTitle(R.string.menuwater);

        getSupportActionBar().setTitle(R.string.app_name);

       // getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        progressBar2 = findViewById(R.id.progressBar_household);
        progressBar_household1 = findViewById(R.id.progressBar_household1);
        progressBar_household2 = findViewById(R.id.progressBar_household2);

        getSupportActionBar().setElevation(0);

        cardSalesReport = findViewById(R.id.card_sales_report);
        cardGraphReport = findViewById(R.id.card_graph_report);
        cardPaymentReport = findViewById(R.id.card_payment_report);
        cardExpenseGraph = findViewById(R.id.card_expense_graph);
        cardExpenseReport = findViewById(R.id.card_expense_report);
        cardSettings = findViewById(R.id.card_settings);
        card_payment = findViewById(R.id.card_payment);
        account = findViewById(R.id.account);





      /*cardSalesReport.startAnimation(fromtopbottom);
        cardExpenseReport.startAnimation(fromtopbottomtwo);
        cardPaymentReport.startAnimation(fromtopbottomthree);
        cardGraphReport.startAnimation(fromtopbottomthree);
        cardExpenseGraph.startAnimation(fromtopbottomthree);
        cardSettings.startAnimation(fromtopbottomthree);*/



            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            String acc = databaseAccess.getACCOUNT();
            account.setText("ລ້າງຂໍ້ມູນ/ມີຂໍ້ມູນທັງໜົດ:"+acc+" ລາຍການ");



        if (Build.VERSION.SDK_INT >= 17) //Android MarshMellow Version or above
        {
            requestPermission();

        }
            try{
                File folder = new File(Environment.getExternalStorageDirectory() + File.separator +"SmartPos/" );

                boolean success = true;
                if (!folder.exists())
                    success = folder.mkdirs();
                if (success) {

                }
            }catch (Exception e){

            }



        //for interstitial ads show,
//        Utils utils=new Utils();
//        utils.interstitialAdsShow(ReportActivity.this);


        if (Usr_id1.equals("")){
            cardSalesReport.setEnabled(false);
            cardExpenseReport.setEnabled(false);
            card_payment.setEnabled(false);
            cardPaymentReport.setEnabled(false);
            cardGraphReport.setEnabled(false);
            cardExpenseGraph.setEnabled(false);
            cardSettings.setEnabled(false);
            changeOnConfirm2();
        }else {
            cardSalesReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, ListWaterBillActivity.class);
                    startActivity(intent);
                    // progressBar2.setVisibility(View.VISIBLE);
                }
            });
            cardExpenseReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this,Receipt_list_Activity.class);
                    startActivity(intent);
                    //   progressBar_household1.setVisibility(View.VISIBLE);
                }
            });
            card_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, Repayment_list_Activity.class);
                    startActivity(intent);
                    //   progressBar_household1.setVisibility(View.VISIBLE);
                }
            });
            cardPaymentReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this,Accountlist_Activity.class);
                    startActivity(intent);
                    // progressBar_household2.setVisibility(View.VISIBLE);
                }
            });
            cardGraphReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, UploadWaterActivity.class);
                    startActivity(intent);

                }
            });
            cardExpenseGraph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent = new Intent(MenuActivity.this, LoadWaterActivity.class);
                    // startActivity(intent);
                    // new TestPrinter();

                    Intent intent = new Intent(MenuActivity.this, dowlaodActivity.class);
                    startActivity(intent);
                    //new TestPrinter();


                }
            });





        /*
            cardSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                    builder.setMessage("ທ່ານຕ້ອງການລ້າງຂໍ້ມູນແທ້ ຫຼື ບໍ ?")
                            .setCancelable(false)
                            .setPositiveButton("ຕົກລົງ", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MenuActivity.this);
                                    databaseAccess.open();
                                    boolean del = databaseAccess.deletetbl_water();
                                    if (del) {
                                        Toasty.error(MenuActivity.this, "ຜິດຜາດ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toasty.success(MenuActivity.this, "ລົບຂໍ້ມູນສຳເລັດ", Toast.LENGTH_SHORT).show();
                                        account.startAnimation(fromtopbottomthree);
                                    }
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("ຍົກເລີກ", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Perform Your Task Here--When No is pressed
                                    dialog.cancel();
                                }
                            }).show();
                }
            });*/


            cardSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MenuActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void printBytes(byte[] array, String name) {
        for (int k = 0; k < array.length; k++) {
            System.out.println(name + "[" + k + "] = " + "0x" + UnicodeFormatter.byteToHex(array[k]));
        }
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.language_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.local_french:
                setNewLocale(this, LocaleManager.FRENCH);
                return true;

            case R.id.local_english:
                setNewLocale(this, LocaleManager.ENGLISH);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    //double backpress to exit
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            finishAffinity();

        } else {
            changeOnConfirm();

        }
        back_pressed = System.currentTimeMillis();
    }



    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //do your action
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }



    public void changeOnConfirm() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("ຄຳເຕືອນ").setContentText("ທານຕ້ອງການອອກຈາກລົບແທ້ ຫຼື ບໍ!").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            finishAffinity();
        }
        }).show();
    }


    public void changeOnConfirm2() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("ຜິດພາດ").setContentText("ກະລຸນາເຂົ້າລະບົບອີກຄັ້ງ!").setConfirmText("Yes").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            finishAffinity();
        }
        }).show();
    }


    public void Confirm() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("ຄຳເຕືອນ").setContentText("ບັນທືກສຳເລັດ!").setConfirmText("ພີມບີນ").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            startActivity(getIntent());
            finish();
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        }).show();

    }
}
