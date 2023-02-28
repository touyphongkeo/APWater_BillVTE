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

import java.util.HashMap;
import java.util.List;

import static com.app.apisvtes.conn.ClassLibs.lng;

public class LoadMemberAdapter extends RecyclerView.Adapter<LoadMemberAdapter.MyViewHolder> {


    private final List<HashMap<String, String>> enrollmemberData;
    private final Context context;
    private  String cct;


    public LoadMemberAdapter(Context context, List<HashMap<String, String>> enrollmemberData) {
        this.context = context;
        this.enrollmemberData = enrollmemberData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmembers_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final String hh_member_id=enrollmemberData.get(position).get("hh_member_id");
        String hh_code=enrollmemberData.get(position).get("hh_code");
        String pp_id=enrollmemberData.get(position).get("pp_id");
        String hh_first_last_name=enrollmemberData.get(position).get("hh_first_last_name");
        String hh_gender_hh=enrollmemberData.get(position).get("hh_gender_hh");
        String cct=enrollmemberData.get(position).get("Status_Join_CTT");
        String hh_pregnant_woman=enrollmemberData.get(position).get("hh_pregnant_woman");
        String hh_total_age_cal=enrollmemberData.get(position).get("hh_total_age_cal");

        holder.txtHH_member_id.setText(" ID: "+ hh_member_id);
        holder.txtHH_code.setText(hh_code);
        holder.txtPP_id.setText(pp_id);
        holder.txtHH_first_last_name.setText(hh_first_last_name);
        holder.txtHH_gender_hh.setText(hh_gender_hh);

        holder.txtHH_code.setText(hh_code);
        holder.txtPP_id.setText( pp_id);
        holder.txtHH_first_last_name.setText( hh_first_last_name);

        String fsex="Male";
try {


        assert hh_gender_hh != null;
        if(hh_gender_hh.equals("1")){
              if(lng=="la") {
                  fsex="ຊາຍ";
              }else{
                  fsex="Male";
              }
         }else{
            if(lng=="la") {
                fsex="ຍິງ";
            }else{
                fsex="Female";
            }
        }

        holder.txtHH_gender_hh.setText(fsex);

        if(hh_pregnant_woman.equals("1")){
            holder.imgDelete.setImageResource(R.drawable.back);

        }else{
            if( Integer.parseInt(hh_total_age_cal)<2){
                holder.imgDelete.setImageResource(R.drawable.bangladesh);
            }else{
                holder.imgDelete.setImageResource(R.drawable.btn_press);
            }

        }
}catch (Exception e){
    //
}
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
        return enrollmemberData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtHH_member_id, txtHH_code, txtPP_id, txtHH_first_last_name, txtHH_gender_hh;
        ImageView imgDelete,imgCall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHH_member_id = itemView.findViewById(R.id.txt_hh_member_id);
            txtHH_code = itemView.findViewById(R.id.hh_code);
            txtPP_id = itemView.findViewById(R.id.txt_pp_id);
            txtHH_first_last_name = itemView.findViewById(R.id.txt_hh_first_last_name);
            txtHH_gender_hh = itemView.findViewById(R.id.total_hh_members);
            imgDelete = itemView.findViewById(R.id.img_delete);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


        }
    }



}
