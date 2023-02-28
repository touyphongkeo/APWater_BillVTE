package com.app.apisvtes.tapwater;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Accountlist_Activity extends BaseActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    ImageView imgNoProduct;
    EditText etxtSearch;
    FloatingActionButton fabAdd;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("ຂໍ້ມູນຜູ້ນຳໃຊ້");

        recyclerView = findViewById(R.id.recycler_view);
        imgNoProduct = findViewById(R.id.image_no_product);
        etxtSearch=findViewById(R.id.etxt_customer_search);
        fabAdd = findViewById(R.id.fab_add);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Accountlist_Activity.this);
        databaseAccess.open();





    }
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