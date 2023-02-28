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
import com.app.apisvtes.editbill.BillTestbillActivity;
import com.app.apisvtes.tapwater.BillTestActivity;
import com.app.apisvtes.tapwater.BillTestsActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.app.apisvtes.conn.ClassLibs.CustID;
import static com.app.apisvtes.conn.ClassLibs.RATRIDS;
import static com.app.apisvtes.conn.ClassLibs.Call;

import es.dmoral.toasty.Toasty;

public class Receipt_list_dapter extends RecyclerView.Adapter<Receipt_list_dapter.MyViewHolder> {


    private List<HashMap<String, String>> waterData;
    private Context context;
    DecimalFormat f;
    private double img;
    public Receipt_list_dapter(Context context, List<HashMap<String, String>> waterData) {
        this.context = context;
        this.waterData = waterData;
        f = new DecimalFormat("##0.00");

    }


    public Receipt_list_dapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_list_water_item, parent, false);
        return new Receipt_list_dapter.MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull final Receipt_list_dapter.MyViewHolder holder, int position) {
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        final String ACCOUNT= waterData.get(position).get("ACCOUNT");
        String NAME= waterData.get(position).get("NAME");
        String PREVREAD= waterData.get(position).get("PREVREAD");
        String PREVDATE= waterData.get(position).get("PREVDATE");
        String Consumption= waterData.get(position).get("Consumption");
        String TOTALDUE= waterData.get(position).get("TOTALDUE");
        String RATRID= waterData.get(position).get("RATRID");
        String call= waterData.get(position).get("call");
        String WALKSEQ= waterData.get(position).get("WALKSEQ");
        String pay= waterData.get(position).get("pay");
        img= Double.parseDouble(Consumption);;
        double total = Double.parseDouble(TOTALDUE);
        String date_before = PREVDATE;
        String date_after = formateDateFromstring("yyyy-MM-dd", "dd-MM-yyyy", date_before);


//       ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE

        databaseAccess.open();
        //   String currency=databaseAccess.getCurrency();

        //  SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

        holder.txtACCOUNT.setText(context.getString(R.string.water_Acc)+ACCOUNT);
        holder.txtNAME.setText(NAME);
        holder.txtPREVREAD.setText(context.getString(R.string.water_prvread)+PREVREAD);
        holder.txtPREVDATE.setText(context.getString(R.string.water_prvdate)+ date_after);
        holder.txtConsumption.setText(context.getString(R.string.water_comsum)+Consumption +" m³");
        holder.txtTOTALDUE.setText(context.getString(R.string.water_total)+ f.format(total));
        holder.txt_route.setText("ເລກສາຍທາງ: "+WALKSEQ);

        if(pay.equals("1")){
            holder.imgDelete.setImageResource(R.drawable.ic_baseline_credit_score_24);
        }else if (pay.equals("0")){
            holder.imgDelete.setImageResource(R.drawable.check);
        }else {
            holder.imgDelete.setImageResource(R.drawable.ic_baseline_edit_off_24);
        }


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 RATRIDS= RATRID.trim();
                 Call = call;

                     if (Call.equals("2")){
                         holder.progressBar2.setVisibility(View.VISIBLE);
                         CustID=ACCOUNT;
                         Intent i = new Intent(context, BillTestbillActivity.class);
                         context.startActivity(i);
                          //  Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show();
                     }else {


                         if (RATRIDS.equals("01")){
                           //  Toast.makeText(context, RATRIDS, Toast.LENGTH_SHORT).show();
                            holder.progressBar2.setVisibility(View.VISIBLE);
                             CustID=ACCOUNT;
                             Intent i = new Intent(context, BillTestActivity.class);
                             context.startActivity(i);
                            // Toast.makeText(context, "Ok 02", Toast.LENGTH_SHORT).show();
                         }else if (RATRIDS.equals("02")){
                             holder.progressBar2.setVisibility(View.VISIBLE);
                             CustID=ACCOUNT;
                             Intent i = new Intent(context, BillTestsActivity.class);
                             context.startActivity(i);
                           //  Toast.makeText(context, "Ok 02", Toast.LENGTH_SHORT).show();
                         }else if (RATRIDS.equals("03")){
                             holder.progressBar2.setVisibility(View.VISIBLE);
                             CustID=ACCOUNT;
                             Intent i = new Intent(context, BillTestActivity.class);
                             context.startActivity(i);
                         }else if (RATRIDS.equals("04")){
                             holder.progressBar2.setVisibility(View.VISIBLE);
                             CustID=ACCOUNT;
                             Intent i = new Intent(context, BillTestActivity.class);
                             context.startActivity(i);
                         }
                     }


            }
        });

    }

    @Override
    public int getItemCount() {
        return waterData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtACCOUNT, txtNAME, txtPREVREAD, txtPREVDATE,txtConsumption,txtTOTALDUE,txt_route;
        ImageView imgDelete,product_image;
        ProgressBar progressBar2;

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
            cardview = itemView.findViewById(R.id.cardview);

            product_image = itemView.findViewById(R.id.product_image);
            txt_route = itemView.findViewById(R.id.txt_route);

            itemView.setOnClickListener(this);
            progressBar2 = itemView.findViewById(R.id.progressBar_household2);

        }
        // ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE
        @Override
        public void onClick(View v) {
         //   progressBar2.setVisibility(View.VISIBLE);
           /*   Intent i = new Intent(context, BillTestActivity.class);
            i.putExtra("ACCOUNT", waterData.get(getAdapterPosition()).get("ACCOUNT"));
            CustID=waterData.get(getAdapterPosition()).get("ACCOUNT");
          i.putExtra("NAME", waterData.get(getAdapterPosition()).get("NAME"));
            i.putExtra("PREVREAD", waterData.get(getAdapterPosition()).get("PREVREAD"));
            i.putExtra("PREVDATE", waterData.get(getAdapterPosition()).get("PREVDATE"));
            i.putExtra("Consumption", waterData.get(getAdapterPosition()).get("Consumption"));
            i.putExtra("TOTALDUE", waterData.get(getAdapterPosition()).get("TOTALDUE"));
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
