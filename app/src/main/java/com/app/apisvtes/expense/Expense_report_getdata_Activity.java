package com.app.apisvtes.expense;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.app.apisvtes.R;
import com.app.apisvtes.adapter.Expensereport_listAdapter;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.database.DatabaseOpenHelper;
import com.app.apisvtes.utils.BaseActivity;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Expense_report_getdata_Activity extends BaseActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    private Expensereport_listAdapter expensereportlist_adapter;

    ImageView imgNoProduct;
    TextView txtNoProducts;
    List<HashMap<String, String>> expenseList;
    double total_price;
    DecimalFormat f;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expensereport_list);

        recyclerView = findViewById(R.id.recycler);
        imgNoProduct = findViewById(R.id.image_no_product);

        txtNoProducts = findViewById(R.id.txt_no_products);


        f = new DecimalFormat("#0.00");
        imgNoProduct.setVisibility(View.GONE);
        txtNoProducts.setVisibility(View.GONE);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle(R.string.all_waterbill);


        // set a GridLayoutManager with default vertical orientation and 3 number of columns
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Expense_report_getdata_Activity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        recyclerView.setHasFixedSize(true);


        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Expense_report_getdata_Activity.this);
        databaseAccess.open();

        //get data from local database

        expenseList = databaseAccess.getAllExpense();

        if (expenseList.size() <= 0) {
            //if no data in local db, then load data from server
            Toasty.info(Expense_report_getdata_Activity.this, R.string.no_data_found, Toast.LENGTH_SHORT).show();

            recyclerView.setVisibility(View.GONE);
            imgNoProduct.setVisibility(View.VISIBLE);
            imgNoProduct.setImageResource(R.drawable.not_found);
            txtNoProducts.setVisibility(View.VISIBLE);


        } else {
            expensereportlist_adapter = new Expensereport_listAdapter(Expense_report_getdata_Activity.this, expenseList);

            recyclerView.setAdapter(expensereportlist_adapter);


        }

        databaseAccess.open();
        String currency=databaseAccess.getCurrency();

        databaseAccess.open();
        total_price = databaseAccess.getTotalExpense("all");



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_expent_report, menu);
        return true;
    }


    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            case R.id.menu_all_sales:
              getReport("all");

                return true;

            case R.id.menu_daily:
               getReport("daily");

                return true;


            case R.id.menu_monthly:
              getReport("monthly");


                return true;

            case R.id.menu_yearly:
              getReport("yearly");


                return true;

            case R.id.menu_export_data:

               folderChooser();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void getReport(String type) {

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Expense_report_getdata_Activity.this);
        databaseAccess.open();


        //get data from local database

        expenseList = databaseAccess.getExpenseReport(type);
        if (expenseList.size() <= 0) {
            //if no data in local db, then load data from server
            Toasty.info(Expense_report_getdata_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();


            recyclerView.setVisibility(View.GONE);
            imgNoProduct.setVisibility(View.VISIBLE);
            imgNoProduct.setImageResource(R.drawable.not_found);
            txtNoProducts.setVisibility(View.VISIBLE);

        } else {
            expensereportlist_adapter = new Expensereport_listAdapter(Expense_report_getdata_Activity.this, expenseList);

            recyclerView.setAdapter(expensereportlist_adapter);

            recyclerView.setVisibility(View.VISIBLE);
            imgNoProduct.setVisibility(View.GONE);
            txtNoProducts.setVisibility(View.GONE);



        }

        databaseAccess.open();
        String currency=databaseAccess.getCurrency();

        databaseAccess.open();
        total_price = databaseAccess.getTotalExpense(type);

    }



    public void folderChooser() {
        new ChooserDialog(Expense_report_getdata_Activity.this)

                .displayPath(true)
                .withFilter(true, false)

                // to handle the result(s)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        onExport(path);
                        Log.d("path",path);

                    }
                })
                .build()
                .show();
    }






    public void onExport(String path) {

        String directory_path = path;
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // Export SQLite DB as EXCEL FILE
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DatabaseOpenHelper.DATABASE_NAME, directory_path);
        sqliteToExcel.exportSingleTable("expense", "expense.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

                loading = new ProgressDialog(Expense_report_getdata_Activity.this);
                loading.setMessage(getString(R.string.data_exporting_please_wait));
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            public void onCompleted(String filePath) {

                Handler mHand = new Handler();
                mHand.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        loading.dismiss();
                        Toasty.success(Expense_report_getdata_Activity.this, R.string.data_successfully_exported, Toast.LENGTH_SHORT).show();



                    }
                }, 5000);

            }

            @Override
            public void onError(Exception e) {

                loading.dismiss();
                Toasty.error(Expense_report_getdata_Activity.this, R.string.data_export_fail, Toast.LENGTH_SHORT).show();
            }
        });



}
}