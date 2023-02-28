package com.app.apisvtes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.tapwater.MenuActivity;

import java.util.HashMap;
import java.util.List;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;
import static com.app.apisvtes.conn.ClassLibs.Usr_nm1;
import static com.app.apisvtes.conn.ClassLibs.fLogin;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Animation fromtopbottom, smtobig;

    ImageView images;
    TextView testv,buttonLogin,vs;
    CardView view_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        getSupportActionBar().hide();

        fromtopbottom = AnimationUtils.loadAnimation(this, R.anim.fromtopbottom);
        smtobig = AnimationUtils.loadAnimation(this, R.anim.smtobig);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        view_list = findViewById(R.id.view_list);
        images = findViewById(R.id.images);
        testv = findViewById(R.id.testv);
        buttonLogin = findViewById(R.id.buttonLogin);

        view_list.startAnimation(fromtopbottom);





    //    fimei=getimei();
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LoginActivity.this);
        databaseAccess.open();



        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    final String UserName = editTextUsername.getText().toString();
                    final String Password = editTextPassword.getText().toString();

                    //validating inputs
                    if (TextUtils.isEmpty(UserName)) {
                        editTextUsername.setError("ກະລຸນາປ້ອນບັນຊີຜູ້ໃຊ້");
                        editTextUsername.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(Password)) {
                        editTextPassword.setError("ກະລຸນາປ້ອນລະຫັດຜ່ານ");
                        editTextPassword.requestFocus();
                        return;
                    }


                        List<HashMap<String, String>> userData;
                        userData = databaseAccess.getAP_sale_item(UserName,Password);

                        if(userData.size()>0){
                            fLogin=true;
                            Usr_id1 = userData.get(0).get("Usr_id");
                            Usr_nm1 = userData.get(0).get("Usr_nm1");
                            Dist1 = userData.get(0).get("Dist");


                        }else{
                            fLogin=false;
                        }

                        if(fLogin){
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                            finish();

                        }else{

                            Toasty.error(getApplicationContext(), "ຊື່ຜູ້ນຳໃຊ້ ແລະ ລະຫັດຜ່ານບໍຖືກຕ້ອງ", 0).show();
                           // finish();
                            startActivity(getIntent());

                        }



            }

        });

    }



}