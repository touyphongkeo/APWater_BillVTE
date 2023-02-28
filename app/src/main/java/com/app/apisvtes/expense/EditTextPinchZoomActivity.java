package com.app.apisvtes.expense;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.Toast;

import com.app.apisvtes.R;
import com.app.apisvtes.tapwater.MenuActivity;
import com.app.apisvtes.utils.BaseActivity;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class EditTextPinchZoomActivity extends BaseActivity {
    TextView editText;
    ImageView imageView;
    TextView txtUpdateExpense,txtUpdateExpense2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printbill);
        txtUpdateExpense2=findViewById(R.id.txt_update_expense2);
     //   txtUpdateExpense=findViewById(R.id.txt_update_expense);
        getSupportActionBar().setTitle("");
     //   getSupportActionBar().setIcon(R.drawable.pos);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bill_title));


       //imageView = (ImageView) findViewById(R.id.image_view);
       // editText = (TextView) findViewById(R.id.edit_text);
     //   editText.buildDrawingCache();

       // imageView.setImageBitmap(editText.getDrawingCache());
        txtUpdateExpense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now = new Date();
               // android.text.format.DateFormat.format("hh:mm:ss", now);

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

                 //   Toasty.success(EditTextPinchZoomActivity.this, imageFile.toString(), Toast.LENGTH_SHORT).show();
                 //   imageView.setImageBitmap(bitmap);
                 //   byte[] bytes = Base64.decode(imageFile.toString(), Base64.DEFAULT);
                  //  imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

                  //  openScreenshot(imageFile);

                    Intent intent = new Intent(EditTextPinchZoomActivity.this,MenuActivity.class);
                 //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } catch (Throwable e) {
                    // Several error may come out with file handling or DOM
                    Toasty.error(EditTextPinchZoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    //  e.printStackTrace();
                }

            }
        });
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

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
            Toasty.success(EditTextPinchZoomActivity.this, imageFile.toString(), Toast.LENGTH_SHORT).show();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            Toasty.error(EditTextPinchZoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

          //  e.printStackTrace();
        }
    }


    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }



}