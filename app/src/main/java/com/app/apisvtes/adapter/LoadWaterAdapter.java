package com.app.apisvtes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apisvtes.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LoadWaterAdapter extends RecyclerView.Adapter<LoadWaterAdapter.MyViewHolder> {


    private final List<HashMap<String, String>> waterData;
    private final Context context;
    DecimalFormat f;

    public LoadWaterAdapter(Context context, List<HashMap<String, String>> waterData) {
        this.context = context;
        this.waterData = waterData;
        f = new DecimalFormat("##0.00");

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {


        final String ACCOUNT= waterData.get(position).get("ACCOUNT");
        String NAME= waterData.get(position).get("NAME");
        String PREVREAD= waterData.get(position).get("PREVREAD");
        String PREVDATE= waterData.get(position).get("PREVDATE");
        String Consumption= waterData.get(position).get("Consumption");
        String TOTALDUE= waterData.get(position).get("TOTALDUE");


        double total = Double.parseDouble(TOTALDUE);

        String date_before = PREVDATE;
        String date_after = formateDateFromstring("yyyy-MM-dd", "dd-MM-yyyy", date_before);

        holder.txtACCOUNT.setText(context.getString(R.string.water_Acc)+ACCOUNT);
        holder.txtNAME.setText(NAME);
        holder.txtPREVREAD.setText(context.getString(R.string.water_prvread)+PREVREAD);
        holder.txtPREVDATE.setText(context.getString(R.string.water_prvdate)+ date_after);
        holder.txtConsumption.setText(context.getString(R.string.water_comsum)+Consumption +" m³");
        holder.txtTOTALDUE.setText(context.getString(R.string.water_total)+ f.format(total));

        holder.imgDelete.setImageResource(R.drawable.bangladesh);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(context, AddEnrollmentActivity.class);
                i.putExtra("hh_member_id", hh_member_id);
                i.putExtra("hh_code", hh_code);
                i.putExtra("pp_id", pp_id);
                  HH_CODE = hh_code;
                context.startActivity(i);*/
            //    Toasty.success(context, hh_member_id.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return waterData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtACCOUNT, txtNAME, txtPREVREAD, txtPREVDATE,txtConsumption,txtTOTALDUE;
        ImageView imgDelete,product_image;

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

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


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
