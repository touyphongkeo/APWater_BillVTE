package com.app.apisvtes.uploads;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.tapwater.MenuActivity;
import com.app.apisvtes.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class LoginActivitys extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LoginActivitys.this);
        databaseAccess.open();


   /*     //get data from local database
        List<HashMap<String, String>> userData;
        userData = databaseAccess.CheckLogins();

        if(userData.size()>0){
            userID= userData.get(0).get("UserName");
            fullname= userData.get(0).get("FullName");
            IDuser= userData.get(0).get("UserId");
            fLogin=true;
        }else{
            userID= "";
            fullname= "";
            fLogin=false;
        }*/
/*  if(fLogin){
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                finish();

             }else{
                if(isconnected){
                  //  Toasty.success(LoginActivitys.this,"OK",Toasty.LENGTH_SHORT).show();
                }else{
                    Toasty.success(LoginActivitys.this,"Please Connected to Internet!",Toasty.LENGTH_SHORT).show();
                }

             }*/




        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                boolean isconnected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

                if(isconnected){
                    userLogin();
                }else{
                    Toasty.success(LoginActivitys.this,"Please Connected to Internet!",Toasty.LENGTH_SHORT).show();
                }

            }
        });

       /* //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });*/
    }

    private void userLogin() {
        //first getting the values
        final String UserName = editTextUsername.getText().toString();
        final String Password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(UserName)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                    //    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Toasty.success(getApplicationContext(), "ເຂົ້າລະບົບສຳເລັດແລ້ວ", 0).show();

                        //getting the user from the response
                     //   JSONObject userJson = obj.getJSONObject("users");

                        /*DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LoginActivitys.this);
                        databaseAccess.open();
                        databaseAccess.addUsers(userJson.getString("UserId"), userJson.getString("UserName"),userJson.getString("FullName"));
*/
                        //creating a new user object
               /*        Users users = new Users(
                                userJson.getInt("UserId"),
                                userJson.getString("UserName"),
                                userJson.getString("FullName"),
                                userJson.getString("Email")
                        );

                        userID=userJson.getString("UserName");
                        fullname=userJson.getString("FullName");
                        IDuser=userJson.getString("UserId");
*/
                        //storing the user in shared preferences
                 //       SharedPrefManagers.getInstance(getApplicationContext()).userLogin(users);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    } else {
                       // Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        Toasty.error(getApplicationContext(), "Invalid username or password", 0).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("UserName", UserName);
                params.put("Password", Password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}