package com.app.apisvtes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;
import com.app.apisvtes.database.DatabaseAccess;
import com.app.apisvtes.editbill.EditWaterbbBillActivity;
import com.app.apisvtes.tapwater.BillTestActivity;
import com.app.apisvtes.tapwater.EditWaterBillActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static  com.app.apisvtes.conn.ClassLibs.CustID;

public class ListWaterBillAdapter extends RecyclerView.Adapter<ListWaterBillAdapter.MyViewHolder> {


    private List<HashMap<String, String>> waterData;
    private Context context;
    DecimalFormat f;
    private double img;
    public ListWaterBillAdapter(Context context, List<HashMap<String, String>> waterData) {
        this.context = context;
        this.waterData = waterData;
        f = new DecimalFormat("##0.00");

    }


    public ListWaterBillAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_item, parent, false);
        return new MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull final ListWaterBillAdapter.MyViewHolder holder, int position) {

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);

        final String ACCOUNT= waterData.get(position).get("ACCOUNT");
        String NAME= waterData.get(position).get("NAME");
        String PREVREAD= waterData.get(position).get("PREVREAD");
        String PREVDATE= waterData.get(position).get("PREVDATE");
        String Consumption= waterData.get(position).get("Consumption");
        String TOTALDUE= waterData.get(position).get("TOTALDUE");
        String walkseq= waterData.get(position).get("WALKSEQ");
        String call= waterData.get(position).get("call");

        img= Double.parseDouble(Consumption);

        double total = Double.parseDouble(TOTALDUE);

        String date_before = PREVDATE;
        String date_after = formateDateFromstring("yyyy-MM-dd", "dd-MM-yyyy", date_before);


//       ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE

        databaseAccess.open();
       // String zonea=databaseAccess.getCurrency(zone);

        databaseAccess.open();
        String zonea = databaseAccess.getCallss(call);


        //  SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

        holder.txtACCOUNT.setText(context.getString(R.string.water_Acc)+ACCOUNT);
        holder.txtNAME.setText(NAME);
        holder.txtPREVREAD.setText(context.getString(R.string.water_prvread)+PREVREAD);
        holder.txtPREVDATE.setText(context.getString(R.string.water_prvdate)+ date_after);
        holder.txtConsumption.setText(context.getString(R.string.water_comsum)+Consumption +" m³");
        holder.txtTOTALDUE.setText(context.getString(R.string.water_total)+ f.format(total));
        holder.WALKSEQ.setText("ເລກສາຍທາງ:"+walkseq);

        if(img>0){
          //  holder.imgDelete.setImageResource(R.drawable.ic_edit);
            holder.imgDelete.setImageResource(R.drawable.ic_printbill);
        }else{
            holder.imgDelete.setImageResource(R.drawable.ic_edit);
        }


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, call, Toast.LENGTH_SHORT).show();
                CustID=ACCOUNT;
                Intent i = new Intent(context, BillTestActivity.class);
                context.startActivity(i);

            }
        });



        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(context, call, Toast.LENGTH_SHORT).show();
                if (call.equals("2")){
                  //  Toast.makeText(context, "ກຳລັງປັບປຸງ", Toast.LENGTH_SHORT).show();
                    CustID=ACCOUNT;
                    Intent i = new Intent(context, EditWaterbbBillActivity.class);
                    context.startActivity(i);
                }else {
                    CustID=ACCOUNT;
                    Intent i = new Intent(context, EditWaterBillActivity.class);
                    context.startActivity(i);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return waterData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtACCOUNT, txtNAME, txtPREVREAD, txtPREVDATE,txtConsumption,txtTOTALDUE,WALKSEQ;
        ImageView imgDelete,product_image;
        ProgressBar progressBar;
        CardView cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtACCOUNT = itemView.findViewById(R.id.txt_ACCOUNT);
            txtNAME = itemView.findViewById(R.id.txt_NAME);
            txtPREVREAD= itemView.findViewById(R.id.txt_PREVREAD);
            txtPREVDATE = itemView.findViewById(R.id.txt_PREVDATE);
            txtConsumption = itemView.findViewById(R.id.txt_Consumption);
            txtTOTALDUE = itemView.findViewById(R.id.txt_TOTALDUE);
            imgDelete = itemView.findViewById(R.id.img_delete);
            product_image = itemView.findViewById(R.id.product_image);
            progressBar = itemView.findViewById(R.id.progressBar);
            WALKSEQ = itemView.findViewById(R.id.WALKSEQ);
            cardview = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {

          /*  Intent i = new Intent(context, EditWaterBillActivity.class);
            i.putExtra("ACCOUNT", waterData.get(getAdapterPosition()).get("ACCOUNT"));
            CustID=waterData.get(getAdapterPosition()).get("ACCOUNT");

            context.startActivity(i);*/
        }
    }

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
           // LOGE(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }


}
