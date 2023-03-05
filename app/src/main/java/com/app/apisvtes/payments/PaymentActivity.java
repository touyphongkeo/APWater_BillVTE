package com.app.apisvtes.payments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.app.apisvtes.LoginActivity;
import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.paymentrepayment.RepaymentActivity;
import com.app.apisvtes.tapwater.BillTest2Activity;
import com.app.apisvtes.tapwater.EditWaterBillActivity;
import com.app.apisvtes.utils.BaseActivity;
import static com.app.apisvtes.conn.ClassLibs.CustID;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;


public class PaymentActivity extends BaseActivity {
    public  Button btnprice,btn_sarch,btn_sarchs;
    public DecimalFormat f1,f2;
    EditText TxtTcode,txtTotal,bbbs,TxtTcodesss,text_payment;
    String vv;
    String s1se;
    String TOTALDUES;
    double nn;
    String billno,Pay;
    String Paid_date;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payments);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ການຊຳລະເງີນ");

        btnprice = findViewById(R.id.btnprice);
        TxtTcode = findViewById(R.id.TxtTcode);
        txtTotal = findViewById(R.id.txtTotal);
        btn_sarch = findViewById(R.id.btn_sarch);
        btn_sarchs = findViewById(R.id.btn_sarchs);
        TxtTcodesss = findViewById(R.id.TxtTcodesss);
        text_payment = findViewById(R.id.payment);
        bbbs = findViewById(R.id.bbbs);
        btn_sarchs.setVisibility(View.GONE);
        TxtTcode.setText(CustID);
        bbbs.requestFocus();





            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PaymentActivity.this);
            databaseAccess.open();
            List<HashMap<String, String>> productData;
            productData = databaseAccess.getAllWaterpritse(CustID.toString());
            f1 = new DecimalFormat("####");
            f2 = new DecimalFormat("#,###");
            if (productData.size() > 0) {
                TxtTcodesss.setText(productData.get(0).get("NAME"));

              //  String s1s = productData.get(0).get("TOTALDUE");
                String s1s = productData.get(0).get("Arrears2");
                Double dses = Double.parseDouble(s1s);
                vv = String.valueOf(dses);
                billno = productData.get(0).get("BILLNO");
                Paid_date = productData.get(0).get("Paid_date");



                s1se = productData.get(0).get("Total_Bill");
                Double dsese = Double.parseDouble(s1se);


                // String s1 = productData.get(0).get("TOTALDUE1");
                Double dss = dses+dsese;
                txtTotal.setText(f1.format(dss));
                TOTALDUES = String.valueOf(dss);
                nn = dss-dss;


            btn_sarch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                String bnm = String.valueOf(dss);
                double bbdf = Double.parseDouble(bnm);
                bbbs.setText(f1.format(bbdf));
                btn_sarchs.setVisibility(View.VISIBLE);
                btnprice.setVisibility(View.VISIBLE);
                btn_sarch.setVisibility(View.GONE);
                String cc  = String.valueOf(nn);
                double nbtotall = Double.parseDouble(cc);
                text_payment.setText(f1.format(nbtotall));
                }
            });



            btn_sarchs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                bbbs.setEnabled(true);
                bbbs.getText().clear();
                text_payment.getText().clear();
                btn_sarchs.setVisibility(View.GONE);
                btn_sarch.setVisibility(View.VISIBLE);
                btnprice.setVisibility(View.VISIBLE);
                }
            });
        }


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (!bbbs.getText().toString().equals("")){
                        double sum_money1 = Double.parseDouble(TOTALDUES);
                        double bank_m1 = Double.parseDouble(bbbs.getText().toString());
                        if (sum_money1<bank_m1){
                          //  changeOnConfirms();
                            text_payment.setText("0");
                        }else {
                            text_payment.setText(String.valueOf(f1.format(sum_money1 - bank_m1)));
                        }
                    }
                }catch (Exception e){
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };


        bbbs.addTextChangedListener(textWatcher);
        long dates = System.currentTimeMillis();
        SimpleDateFormat sdfss = new SimpleDateFormat("yy");
        String dateStringss = sdfss.format(dates);
        String currentYear = new SimpleDateFormat("yy", Locale.ENGLISH).format(new Date());
        String rep = "Rep"+currentYear;
        List<HashMap<String, String>> PAY_NO;
        PAY_NO = databaseAccess.getPAY_NO(rep);
        int ss = Integer.parseInt(PAY_NO.get(0).get("asNo"));
        String bills="";
           if (ss<9){
                bills = "000";
            }else if (ss<99){
                bills = "00";
            }else if (ss<999){
                bills = "0";
            }else {
               bills = String.valueOf(ss);
            }
            ss++;
           String nn = "00";
           String PAY_NOS = ("Rep"+dateStringss+"."+nn+""+ss);


//        btnprice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnprice.setEnabled(false);
//                    try {
//
//                        if (Usr_id1.equals("")){
//                            Intent intent = new Intent(PaymentActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                        }else {
//
//                        String textpayment = text_payment.getText().toString();
//
//                        String bpaymets = String.valueOf(textpayment);
//
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
//                        String date = sdf.format(new Date());
//                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PaymentActivity.this);
//                        databaseAccess.open();
//                        String b = bbbs.getText().toString();
//                        double nb = Double.parseDouble(b);
//                        double nbs = Double.parseDouble(vv);
//
//
//
//                        if (nb<=nbs){
//                            bbbs.setError("ຈຳນວນເງີນຊຳລະນ້ອຍກວາໜີຄ້າງຈ່າຍ");
//                        }else {
//
//                        databaseAccess.open();
//                        databaseAccess.updatetbl_waterbv(textpayment, CustID);
//
//
//
//
//
//                        if (b.equals("")) {
//                            changeOnConfirm();
//                        } else {
//                            if (textpayment.equals("0")) {
//
//
//                                databaseAccess.open();
//                                databaseAccess.updateMaster(textpayment, CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateTOTALDUEBB(bpaymets, CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateMPay(CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateDBB(CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.UpdatePaysdd(CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateDBBD(CustID);
//                                databaseAccess.open();
//                                databaseAccess.updateDBBT(CustID, billno);
//                                databaseAccess.open();
//                                databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);
//
//                                databaseAccess.open();
//                                databaseAccess.updatBBCheckBill(CustID,billno);
//
//                                String PSTATUS = "1";
//
//                                double bb = Double.parseDouble(b);
//                                double pay = Double.parseDouble(bpaymets);
//
//                                //bb ຮັບຄາມາຈາກ ການຊຳລະ
//                                //pay ຮັບຄາມາຈາກຍອດເຫືອທັງໜົດ
//                                //vv ໜີຄ້າງຈ່າຍໃນເດືອນກອນ
//                                //s1se ມຸນຄ້ານ້ຳໃນເດືອນ
//
//                                double aount = bb+pay;
//                                String bv = String.valueOf(aount);
//
//
//                                double aa = Double.parseDouble(s1se);
//                                double bbs = Double.parseDouble(vv);
//
//                                double vbb2 = aa+bbs;
//                                String vvbb2s = String.valueOf(vbb2);
//
//                                double vbb = bb-bbs;
//
//                                double cb = bb-bbs;
//                                double cb1 = cb-cb;
//                                String cb11s = String.valueOf(cb);
//                                String cb11 = String.valueOf(vbb);
//
//
//                                databaseAccess.open();
//                                databaseAccess.addPAYMENT(CustID, PAY_NOS, Paid_date, date, b,bv,bpaymets, PSTATUS, billno, Usr_id1);
//
//
//                                String bvs = "0";
//
//                                databaseAccess.open();
//                            // databaseAccess.addPAYMENT(CustID, PAY_NOS, Paid_date, date, b,bv,bpaymets, PSTATUS, billno, Usr_id1);
//                              //  String pay_nos = databaseAccess.getPAY_NO2();
//
//                                if (PAY_NO.equals(pay_nos)){
//                                    long dates = System.currentTimeMillis();
//                                    SimpleDateFormat sdfss = new SimpleDateFormat("yy");
//                                    String dateStringss = sdfss.format(dates);
//                                    String currentYear = new SimpleDateFormat("yy", Locale.ENGLISH).format(new Date());
//                                    String rep = "Rep"+currentYear;
//                                    List<HashMap<String, String>> PAY_NO;
//                                    databaseAccess.open();
//                                    PAY_NO = databaseAccess.getPAY_NO(rep);
//                                    int ss = Integer.parseInt(PAY_NO.get(0).get("asNo"));
//                                    String bills="";
//                                    if (ss<9){
//                                        bills = "000";
//                                    }else if (ss<99){
//                                        bills = "00";
//                                    }else if (ss<999){
//                                        bills = "0";
//                                    }else {
//                                        bills = String.valueOf(ss);
//                                    }
//                                    ss++;
//                                    String nn = "00";
//                                    String PAY_NOSs = ("Rep"+dateStringss+"."+nn+""+ss);
//                                    databaseAccess.open();
//                                    databaseAccess.addPAYMENTssdfsf(CustID, PAY_NOSs, Paid_date, date, cb11,b,vv,b, PSTATUS, billno, Usr_id1);
//
//                                }else {
//                                 //   databaseAccess.open();
//                                    databaseAccess.addPAYMENTssdfsf(CustID, PAY_NOS, Paid_date, date, cb11,b,vv,b, PSTATUS, billno, Usr_id1);
//
//                              //  }
//
//
//                                Confirm();
//
//                            // Toast.makeText(PaymentActivity.this, "ເລກບີນ"+PAY_NOS, Toast.LENGTH_SHORT).show();
//                            // Toast.makeText(PaymentActivity.this, b, Toast.LENGTH_SHORT).show();
//                            // Intent intent = new Intent(PaymentActivity.this, BillTest2Activity.class);
//                            // startActivity(intent);
//
//                            } else {
//
//                                databaseAccess.open();
//                                databaseAccess.UpdatePaysdd(CustID);
//                                databaseAccess.open();
//                                databaseAccess.updateMaster2(textpayment, CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateTOTALDUEBB(bpaymets, CustID);
//
//                                databaseAccess.open();
//                                databaseAccess.updateMPay(CustID);
//                                databaseAccess.open();
//                                databaseAccess.updateDBB(CustID);
//                                databaseAccess.open();
//                                databaseAccess.updateDBBD(CustID);
//                                databaseAccess.open();
//                                databaseAccess.updateDBBT(CustID, billno);
//                                databaseAccess.open();
//                                databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);
//                                String PSTATUS = "1";
//                                double bb = Double.parseDouble(b);
//                                double pay = Double.parseDouble(bpaymets);
//
//                                double aount = bb+pay;
//                                String bv = String.valueOf(aount);
//
//                                databaseAccess.open();
//                                databaseAccess.addPAYMENT(CustID, PAY_NOS, Paid_date, date, b,bv,bpaymets, PSTATUS, billno, Usr_id1);
//
//
//                                String bvs = "0";
//
//                                double aa = Double.parseDouble(s1se);
//                                double bbs = Double.parseDouble(vv);
//
//                                double vbb2 = aa+bbs;
//                                String vvbb2s = String.valueOf(vbb2);
//
//                                double vbb = bb-bbs;
//
//                                double cb = bb-bbs;
//                                double cb1 = cb-cb;
//                                String cb11s = String.valueOf(cb);
//                                String cb11 = String.valueOf(vbb);
//
//
//                                databaseAccess.open();
//                                //    databaseAccess.addPAYMENT(CustID, PAY_NOS, Paid_date, date, b,bv,bpaymets, PSTATUS, billno, Usr_id1);
//
//                                String pay_nos = databaseAccess.getPAY_NO2();
//
//                                if (PAY_NO.equals(pay_nos)){
//                                    long dates = System.currentTimeMillis();
//                                    SimpleDateFormat sdfss = new SimpleDateFormat("yy");
//                                    String dateStringss = sdfss.format(dates);
//                                    String currentYear = new SimpleDateFormat("yy", Locale.ENGLISH).format(new Date());
//                                    String rep = "Rep"+currentYear;
//                                    List<HashMap<String, String>> PAY_NO;
//                                    databaseAccess.open();
//                                    PAY_NO = databaseAccess.getPAY_NO(rep);
//                                    int ss = Integer.parseInt(PAY_NO.get(0).get("asNo"));
//                                    String bills="";
//                                    if (ss<9){
//                                        bills = "000";
//                                    }else if (ss<99){
//                                        bills = "00";
//                                    }else if (ss<999){
//                                        bills = "0";
//                                    }else {
//                                        bills = String.valueOf(ss);
//                                    }
//                                    ss++;
//                                    String nn = "00";
//                                    String PAY_NOSs = ("Rep"+dateStringss+"."+nn+""+ss);
//                                    databaseAccess.open();
//                                    databaseAccess.addPAYMENTssdfsf(CustID, PAY_NOSs, Paid_date, date, cb11,b,vv,b, PSTATUS, billno, Usr_id1);
//
//                                }else {
//                                //    databaseAccess.open();
//                                    databaseAccess.addPAYMENTssdfsf(CustID, PAY_NOS, Paid_date, date, cb11,b,vv,b, PSTATUS, billno, Usr_id1);
//
//                               // }
//                                //    Toast.makeText(PaymentActivity.this, b, Toast.LENGTH_SHORT).show();
//                              //  Intent intent = new Intent(PaymentActivity.this, BillTest2Activity.class);
//                               // startActivity(intent);
//                                Confirm();
//                            }
//                             //   databaseAccess.open();
//                               // databaseAccess.updatemasterSV(textpayment, CustID);
//                        }
//                        }
//                        }
//                    } catch (Exception e) {
//                    }
//
//            }
//        });

      /*  btnprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnprice.setEnabled(false);


                try {
                    String textpayment = text_payment.getText().toString();
                    String bpaymets = String.valueOf(textpayment);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                    String date = sdf.format(new Date());
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PaymentActivity.this);
                    databaseAccess.open();
                    String b = bbbs.getText().toString();

                    databaseAccess.open();
                    databaseAccess.updatetbl_waterbv(textpayment, CustID);


                    databaseAccess.open();
                    databaseAccess.updateMaster223(textpayment, CustID);
                    //  Confirm();


                    databaseAccess.open();
                    databaseAccess.updatePayment(b,CustID, billno);


                    if (b.equals("")) {
                        changeOnConfirm();
                    } else {
                        if (textpayment.equals("0")) {
                            databaseAccess.open();
                            databaseAccess.updateMaster223(bpaymets, CustID);

                           *//* databaseAccess.open();
                            databaseAccess.updateTOTALDUEBB(bpaymets, CustID);*//*


                            databaseAccess.open();
                            databaseAccess.updateMPay(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBB(CustID);

                            databaseAccess.open();
                            databaseAccess.UpdatePaysdd(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBBD(CustID);
                            databaseAccess.open();
                            databaseAccess.updateDBBT(CustID, billno);
                            databaseAccess.open();
                            databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);

                            databaseAccess.open();
                            databaseAccess.updatBBCheckBill(CustID,billno);
                            String PSTATUS = "1";

                            double bbs = Double.parseDouble(vv);

                            double bb = Double.parseDouble(b);

                            if (bb<=bbs){
                                String vvvv= "0";
                                double vbb = bbs-bb;
                                double bhjf = Double.parseDouble(f1.format(vbb));
                                String cb11 = String.valueOf(vbb);
                                double kk = Double.parseDouble(f1.format(bbs));
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhjf,b,kk,b, PSTATUS, billno, Usr_id1);

                            }else {

                                String vvvv= "0";
                                double vbb = bb-bbs;
                                double bhjff = Double.parseDouble(f1.format(vbb));
                                String cb11 = String.valueOf(vbb);
                                double kk = Double.parseDouble(f1.format(bbs));
                                databaseAccess.open();

                                databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhjff,b,kk,b, PSTATUS, billno, Usr_id1);

                                //  databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhjff,b,vv,b, PSTATUS, billno, Usr_id1);

                            }

                            //   Toast.makeText(PaymentActivity.this, ""+bpaymets, Toast.LENGTH_SHORT).show();
                            Confirm();

                        } else {

                            databaseAccess.open();
                            databaseAccess.updateMaster223(bpaymets, CustID);

                          *//*  databaseAccess.open();
                            databaseAccess.updateTOTALDUEBB(bpaymets, CustID);*//*


                            databaseAccess.open();
                            databaseAccess.updateMPay(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBB(CustID);

                            databaseAccess.open();
                            databaseAccess.UpdatePaysdd(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBBD(CustID);
                            databaseAccess.open();
                            databaseAccess.updateDBBT(CustID, billno);
                            databaseAccess.open();
                            databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);

                            databaseAccess.open();
                            databaseAccess.updatBBCheckBill(CustID,billno);

                            double bbs = Double.parseDouble(vv);
                            String PSTATUS = "1";
                            double bb = Double.parseDouble(b);

                            if (bb<=bbs){
                                double vbb = bbs - bb;
                                double bhj = Double.parseDouble(f1.format(vbb));
                                String vvvv = "0";
                                String cb11 = String.valueOf(vbb);
                                double kk1 = Double.parseDouble(f1.format(bbs));
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhj, b, kk1, b, PSTATUS, billno, Usr_id1);

                            }else {
                                double vbb = bb-bbs;
                                double bhjs = Double.parseDouble(f1.format(vbb));
                                String vvvv= "0";
                                String cb11 = String.valueOf(vbb);
                                double kk1 = Double.parseDouble(f1.format(bbs));
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhjs,b,kk1,b, PSTATUS, billno, Usr_id1);

                            }

                            Toast.makeText(PaymentActivity.this, ""+bpaymets, Toast.LENGTH_SHORT).show();

                            Confirm();

                        }

                    }
                } catch (Exception e) {
                }

            }
        });*/




        btnprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnprice.setEnabled(false);


                try {
                    String textpayment = text_payment.getText().toString();
                    String bpaymets = String.valueOf(textpayment);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                    String date = sdf.format(new Date());
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(PaymentActivity.this);
                    databaseAccess.open();
                    String b = bbbs.getText().toString();

                    databaseAccess.open();
                    databaseAccess.updatetbl_waterbv(textpayment, CustID);


                    databaseAccess.open();
                    databaseAccess.updateMaster223(textpayment, CustID);
                    //  Confirm();


                    databaseAccess.open();
                    databaseAccess.updatePayment(b,CustID, billno);


                    if (b.equals("")) {
                        changeOnConfirm();
                    } else {
                        if (textpayment.equals("0")) {
                            databaseAccess.open();
                            databaseAccess.updateMaster223(bpaymets, CustID);

                           /* databaseAccess.open();
                            databaseAccess.updateTOTALDUEBB(bpaymets, CustID);*/


                            databaseAccess.open();
                            databaseAccess.updateMPay(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBB(CustID);

                            databaseAccess.open();
                            databaseAccess.UpdatePaysdd(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBBD(CustID);
                            databaseAccess.open();
                            databaseAccess.updateDBBT(CustID, billno);
                            databaseAccess.open();
                            databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);

                            databaseAccess.open();
                            databaseAccess.updatBBCheckBill(CustID,billno);
                            String PSTATUS = "1";

                            double bbs = Double.parseDouble(vv);

                            double bb = Double.parseDouble(b);

                            if (bb<=bbs){
                                String vvvv= "0";
                                double vbb = bbs-bb;
                                double bhjf = Double.parseDouble(f1.format(vbb));
                                double bhvv = Double.parseDouble(f1.format(bbs));
                                String cb11 = String.valueOf(vbb);
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22P(CustID, PAY_NOS, Paid_date, date, bhjf,b,bhvv,b, PSTATUS, billno, Usr_id1);

                            }else {

                                String vvvv= "0";
                                double vbb = bb-bbs;
                                double bhjff = Double.parseDouble(f1.format(vbb));
                                double bhjvv = Double.parseDouble(f1.format(bbs));
                                String cb11 = String.valueOf(vbb);
                                databaseAccess.open();

                                databaseAccess.addPAYMENT22P(CustID, PAY_NOS, Paid_date, date, bhjff,b,bhjvv,b, PSTATUS, billno, Usr_id1);

                                //  databaseAccess.addPAYMENT22(CustID, PAY_NOS, Paid_date, date, bhjff,b,vv,b, PSTATUS, billno, Usr_id1);

                            }

                            //   Toast.makeText(PaymentActivity.this, ""+bpaymets, Toast.LENGTH_SHORT).show();
                            Confirm();

                        } else {

                            databaseAccess.open();
                            databaseAccess.updateMaster223(bpaymets, CustID);

                          /*  databaseAccess.open();
                            databaseAccess.updateTOTALDUEBB(bpaymets, CustID);*/


                            databaseAccess.open();
                            databaseAccess.updateMPay(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBB(CustID);

                            databaseAccess.open();
                            databaseAccess.UpdatePaysdd(CustID);

                            databaseAccess.open();
                            databaseAccess.updateDBBD(CustID);
                            databaseAccess.open();
                            databaseAccess.updateDBBT(CustID, billno);
                            databaseAccess.open();
                            databaseAccess.updateDBBTAMOUNTS(date, Paid_date, CustID, billno);

                            databaseAccess.open();
                            databaseAccess.updatBBCheckBill(CustID,billno);

                            double bbs = Double.parseDouble(vv);
                            String PSTATUS = "1";
                            double bb = Double.parseDouble(b);

                            if (bb<=bbs){
                                double vbb = bbs - bb;
                                double bhj = Double.parseDouble(f1.format(vbb));
                                double bhjvv = Double.parseDouble(f1.format(bbs));
                                String vvvv = "0";
                                String cb11 = String.valueOf(vbb);
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22P(CustID, PAY_NOS, Paid_date, date, bhj, b, bhjvv, b, PSTATUS, billno, Usr_id1);

                            }else {
                                double vbb = bb-bbs;
                                double bhjs = Double.parseDouble(f1.format(vbb));
                                double bhjsv = Double.parseDouble(f1.format(bbs));
                                String vvvv= "0";
                                String cb11 = String.valueOf(vbb);
                                databaseAccess.open();
                                databaseAccess.addPAYMENT22P(CustID, PAY_NOS, Paid_date, date, bhjs,b,bhjsv,b, PSTATUS, billno, Usr_id1);

                            }

                            Toast.makeText(PaymentActivity.this, ""+bpaymets, Toast.LENGTH_SHORT).show();

                            Confirm();

                        }

                    }
                } catch (Exception e) {
                }

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

    public void changeOnConfirm() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("ຄຳເຕືອນ").setContentText("ກະລຸນາປ້ອນຈຳນວນເງີນ!").setConfirmText("Cancel").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            startActivity(getIntent());
            finish();
        }
        }).show();

    }


    public void Confirm() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("ຄຳເຕືອນ").setContentText("ບັນທືກສຳເລັດ!").setConfirmText("ພີມບີນ").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            startActivity(getIntent());
            finish();
            Intent intent = new Intent(PaymentActivity.this, BillTest2Activity.class);
            startActivity(intent);
        }
        }).show();

    }


    public void changeOnConfirms() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("ຄຳເຕືອນ").setContentText("ຈຳນວນເງີນທີທ່ານປ້ອນເກີນ!").setConfirmText("Cancel").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
        public void onClick(SweetAlertDialog sDialog) {
            startActivity(getIntent());
            finish();
        }
        }).show();

    }


}
