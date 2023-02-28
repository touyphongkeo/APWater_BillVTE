package com.app.apisvtes.menudownload;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.download.LoadWaterActivity;
import com.app.apisvtes.downloadbtpayment.LoadBTPaymentActivity;
import com.app.apisvtes.settings.backup.BackupActivity;
import com.app.apisvtes.tapwater.MenuActivity;

import es.dmoral.toasty.Toasty;

public class dowlaodActivity extends BackupActivity {
    CardView card_expense_graph,card_settings,card_graph_report;
    TextView account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memudownload);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ດາວໂຫລດຂໍ້ມູນຈາກ Server");


        card_expense_graph = findViewById(R.id.card_expense_graph);
        card_settings = findViewById(R.id.card_settings);
        card_graph_report = findViewById(R.id.card_graph_report);
        account = findViewById(R.id.account);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String acc = databaseAccess.getACCOUNT();
        account.setText("ລ້າງຂໍ້ມູນ/ມີຂໍ້ມູນທັງໜົດ:"+acc+" ລາຍການ");


        card_expense_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dowlaodActivity.this, LoadWaterActivity.class);
                startActivity(intent);
            }
        });

        card_graph_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dowlaodActivity.this, LoadBTPaymentActivity.class);
                startActivity(intent);
            }
        });

        card_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dowlaodActivity.this);
                builder.setMessage("ທ່ານຕ້ອງການລ້າງຂໍ້ມູນແທ້ ຫຼື ບໍ ?")
                        .setCancelable(false)
                        .setPositiveButton("ຕົກລົງ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(dowlaodActivity.this);
                                databaseAccess.open();
                                boolean del = databaseAccess.deletetbl_water();
                                if (del) {
                                    Toasty.error(dowlaodActivity.this, "ຜິດຜາດ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toasty.success(dowlaodActivity.this, "ລົບຂໍ້ມູນສຳເລັດ", Toast.LENGTH_SHORT).show();

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
}
