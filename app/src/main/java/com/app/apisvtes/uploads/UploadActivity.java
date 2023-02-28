package com.app.apisvtes.uploads;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UploadActivity extends BaseActivity {
    ProgressDialog progressDialog,progressDialog2;
    //ImageView to display image selected
    ImageView imageView;
    String mediaPath, encodedImage = "N/A";
    Button b1, b2;


        @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_upload);

                //initializing views
                imageView = findViewById(R.id.imageView);

                b2 = findViewById(R.id.btnimage);
                b2.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(UploadActivity.this);
                        final int[] v_count_image_enroll = {0};
                        final int[] v_count_image_member = {0};
                        try {


                            databaseAccess.open();
                            //get data from local database
                            List<HashMap<String, String>> enrollData;
                            enrollData = databaseAccess.UploadImagesEnroll();
                            Log.d("data", "" + enrollData.size());

                            progressDialog = new ProgressDialog(UploadActivity.this);
                            progressDialog.setMax(enrollData.size()); // Progress Dialog Max Value
                            progressDialog.setMessage("Loading..."); // Setting Message
                            progressDialog.setTitle("Loading Enrollment Image"); // Setting Title
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
                            progressDialog.show(); // Display Progress Dialog
                            progressDialog.setCancelable(false);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String Image_Pinkbook_Immu_filename, Image_Pinkbook_Immu_url, Image_account_filename, Image_account_url, Image_enroll_paper_form_filename,Image_enroll_paper_form_url,BeneficarySignatureFilename_image,BeneficarySignatureUrl_image;
                                        //String hh_member_id;
                                        for (int i = 0; i < enrollData.size(); i++) {

                                            try {
                                                if (!enrollData.get(i).get("Image_Pinkbook_Immu_filename").equals(null))
                                                {
                                                    //hh_member_id = enrollData.get(i).get("hh_member_id");
                                                    Image_Pinkbook_Immu_filename = enrollData.get(i).get("Image_Pinkbook_Immu_filename");
                                                    Image_Pinkbook_Immu_url = enrollData.get(i).get("Image_Pinkbook_Immu_url");
                                                    UploadImage(Image_Pinkbook_Immu_filename, Image_Pinkbook_Immu_url, Image_Pinkbook_Immu_filename);
                                                    v_count_image_enroll[0] = v_count_image_enroll[0] +1;
                                                }

                                            } catch (Exception e) {
                                                //
                                                Log.d("data_image", e.getMessage());
                                            }

                                            try {
                                                if (!enrollData.get(i).get("Image_account_filename").equals(null)) {
                                                    Image_account_filename = enrollData.get(i).get("Image_account_filename");
                                                    Image_account_url = enrollData.get(i).get("Image_account_url");
                                                    UploadImage(Image_account_filename, Image_account_url, Image_account_filename);
                                                    v_count_image_enroll[0] = v_count_image_enroll[0] +1;
                                                }

                                            } catch (Exception e)
                                            {
                                                Log.d("data_image", e.getMessage());
                                                //
                                            }

                                            try {
                                                if (!enrollData.get(i).get("Image_enroll_paper_form_filename").equals(null)) {
                                                    Image_enroll_paper_form_filename = enrollData.get(i).get("Image_enroll_paper_form_filename");
                                                    Image_enroll_paper_form_url = enrollData.get(i).get("Image_enroll_paper_form_url");
                                                    UploadImage(Image_enroll_paper_form_filename, Image_enroll_paper_form_url, Image_enroll_paper_form_filename);
                                                    v_count_image_enroll[0] = v_count_image_enroll[0] +1;
                                                }

                                            } catch (Exception e)
                                            {
                                                Log.d("data_image", e.getMessage());
                                                //
                                            }

                                            try {
                                                if (!enrollData.get(i).get("BeneficarySignatureFilename_image").equals(null)) {
                                                    BeneficarySignatureFilename_image = enrollData.get(i).get("BeneficarySignatureFilename_image");
                                                    BeneficarySignatureUrl_image = enrollData.get(i).get("BeneficarySignatureUrl_image");
                                                    UploadImage(BeneficarySignatureFilename_image, BeneficarySignatureUrl_image, BeneficarySignatureFilename_image);
                                                    v_count_image_enroll[0] = v_count_image_enroll[0] +1;
                                                }

                                            } catch (Exception e)
                                            {
                                                Log.d("data_image", e.getMessage());
                                                //
                                            }

                                            //update_ok=databaseAccess.updateStatus(v_hh_member_id);


                                            Thread.sleep(500);
                                        //    handle.sendMessage(handle.obtainMessage());
                                            if (i == progressDialog.getMax()) {
                                                progressDialog.dismiss();
                                            }

                                        }
                                        progressDialog.dismiss();
                                        //Toasty.success(UploadActivity.this, "Upload " + v_count_image_member[0] + " Images Successfull!", Toast.LENGTH_SHORT).show();
                                        // Toasty.success(UploadActivity.this, "Please waiting for while Uploading progress!", Toast.LENGTH_SHORT).show();



                                    } catch (Exception e) {
                                        Log.d("data_image", e.getMessage());
                                        //  e.printStackTrace();
                                    }
                                }
                            }).start();
                        }catch (Exception e)
                        {
                            Log.d("data_image", e.getMessage());
                            //
                        }

                        //Upload member


                        try {


                            databaseAccess.open();
                            //get data from local database
                            List<HashMap<String, String>> memberData;
                            //List<ContentValues> memberData;

                            memberData = databaseAccess.UploadImagesMember();
                            Log.d("data", "" + memberData.size());

                            progressDialog2 = new ProgressDialog(UploadActivity.this);
                            progressDialog2.setMax(memberData.size()); // Progress Dialog Max Value
                            progressDialog2.setMessage("Loading..."); // Setting Message
                            progressDialog2.setTitle("Loading Member Image"); // Setting Title
                            progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
                            progressDialog2.show(); // Display Progress Dialog
                            progressDialog2.setCancelable(false);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String img_filename ="";
                                        String img_url, card_imgfilename_page1, card_img_url_page1;
                                        for (int i = 0; i < memberData.size(); i++) {

                                            try {
                                                if (!memberData.get(i).get("img_filename").equals(null)) {
                                                    img_filename = memberData.get(i).get("img_filename");
                                                    img_url = memberData.get(i).get("img_url");
                                                    UploadImage(img_filename, img_url, img_filename);
                                                    v_count_image_member[0] = v_count_image_member[0] +1;


                                                    Log.d("data_image",img_filename);
                                                    //Log.d("data_image",e.getMessage().toString());
                                                }

                                            } catch (Exception e)
                                            {
                                                Log.d("data_image",img_filename);
                                                Log.d("data_image", e.getMessage());
                                                //
                                            }

                                            try {
                                                if (!memberData.get(i).get("card_imgfilename_page1").equals(null)) {
                                                    card_imgfilename_page1 = memberData.get(i).get("card_imgfilename_page1");
                                                    card_img_url_page1 = memberData.get(i).get("card_img_url_page1");
                                                    UploadImage(card_imgfilename_page1, card_img_url_page1, card_imgfilename_page1);

                                                    v_count_image_member[0] = v_count_image_member[0] +1;
                                                }

                                                //databaseAccess.UpdateEnroll_status_all();
                                            } catch (Exception e)
                                            {
                                                Log.d("data_image", e.getMessage());
                                                //
                                            }

                                            Thread.sleep(500);
                                          //  handle2.sendMessage(handle2.obtainMessage());
                                            if (i == progressDialog2.getMax()) {
                                                progressDialog2.dismiss();
                                            }

                                        }
                                        //  progressDialog.dismiss();
                                        progressDialog2.dismiss();
                                        //Toasty.success(UploadActivity.this, "Upload " + v_count_image_member[0] + "  Images Successfull!", Toast.LENGTH_SHORT).show();


                                    } catch (Exception e)
                                    {
                                        Log.d("data_image", e.getMessage());
                                        //  e.printStackTrace();
                                    }
                                }
                            }).start();
                        } catch (Exception e)
                        {
                            Log.d("data_image", e.getMessage());
                            //
                        }



                        int v1 = v_count_image_member[0] + v_count_image_enroll[0];
                      //  Toasty.success(UploadActivity.this, R.string.UploadSuccess, Toast.LENGTH_SHORT).show();


                        onBackPressed();

                        //Toasty.success(UploadActivity.this, "Please waiting for while Uploading progress !", Toast.LENGTH_SHORT).show();
                    }



                });

                }

    private void UploadImage(String file, String fuplaodPath, String fimgname) {
        try {

            String path = Environment.getExternalStorageDirectory() + "/saved_images/" + file;
            File imgFile = new File(path);
            String url_photo = fuplaodPath;
            String fimgnames = fimgname;

            if (imgFile.exists()) {
                try {
                    Bitmap myBitmap = BitmapFactory.decodeFile(path);
                    //   ImageView imageView= findViewById(R.id.imageView);
                    //    imageView.setImageBitmap(myBitmap);
                    uploadBitmap(myBitmap, fuplaodPath, fimgnames);
                } catch (Exception e) {
                    //
                }

            }

        } catch (Exception e) {
            //   Toasty.error(UploadActivity.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    private void uploadBitmap(final Bitmap bitmap, String fPath, String fimgename) {

        //getting the tag from the edittext
        final String tags = fPath.trim();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject obj = new JSONObject(new String(response.data));
                    //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    Toasty.success(getApplicationContext(), "UploadSuccess", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    //    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tags", tags);
                return params;
            }


            @Override
            public Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(fimgename, getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        // cuongnv sua ngay 11/11 để giảm kích thước file khi upload
        return byteArrayOutputStream.toByteArray();
    }

}