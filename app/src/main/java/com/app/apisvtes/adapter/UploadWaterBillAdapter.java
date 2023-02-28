package com.app.apisvtes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class UploadWaterBillAdapter extends RecyclerView.Adapter<UploadWaterBillAdapter.MyViewHolder> {


    private List<HashMap<String, String>> waterData;
    private Context context;
    DecimalFormat f;
    private double img;
    public UploadWaterBillAdapter(Context context, List<HashMap<String, String>> waterData) {
        this.context = context;
        this.waterData = waterData;
        f = new DecimalFormat("##0.00");

    }


    public UploadWaterBillAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_item, parent, false);
        return new MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull final UploadWaterBillAdapter.MyViewHolder holder, int position) {

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);

        final String ACCOUNT= waterData.get(position).get("ACCOUNT");
        String NAME= waterData.get(position).get("NAME");
          String Consumption= waterData.get(position).get("Consumption");
        String TOTALDUE= waterData.get(position).get("TOTALDUE");

        img= Double.parseDouble(Consumption);;

        double total = Double.parseDouble(TOTALDUE);

        databaseAccess.open();
     //   String currency=databaseAccess.getCurrency();

      //  SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

        holder.txtACCOUNT.setText(ACCOUNT);
        holder.txtNAME.setText(NAME);
        holder.txtConsumption.setText("ບໍລິມາດ:"+Consumption +" m³");
        holder.txtTOTALDUE.setText("ຈຳນວນເງິນ:"+ f.format(total));
        holder.imgDelete.setImageResource(R.drawable.check);


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.want_to_delete_expense)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                databaseAccess.open();
                                boolean deleteProduct=databaseAccess.deleteExpense(ACCOUNT);

                                if (deleteProduct)
                                {
                                    Toasty.error(context, R.string.expense_deleted, Toast.LENGTH_SHORT).show();

                                    waterData.remove(holder.getAdapterPosition());

                                    // Notify that item at position has been removed
                                    notifyItemRemoved(holder.getAdapterPosition());

                                }

                                else
                                {
                                    Toast.makeText(context, R.string.failed, Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return waterData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtACCOUNT, txtNAME, txtConsumption,txtTOTALDUE;
        ImageView imgDelete,product_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtACCOUNT = itemView.findViewById(R.id.txt_ACCOUNT);
            txtNAME = itemView.findViewById(R.id.txt_NAME);
            txtConsumption = itemView.findViewById(R.id.txt_Consumption);
            txtTOTALDUE= itemView.findViewById(R.id.txt_TOTALDUE);
            imgDelete = itemView.findViewById(R.id.img_delete);

            product_image = itemView.findViewById(R.id.product_image);

            itemView.setOnClickListener(this);

        }
// ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE
        @Override
        public void onClick(View v) {

        }
    }

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
           // LOGE(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }


}
