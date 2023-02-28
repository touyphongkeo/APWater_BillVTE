package com.app.apisvtes.adapter;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.PWD1;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;
import static com.app.apisvtes.conn.ClassLibs.CALL;

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

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.MyViewHolder> {
    private List<HashMap<String, String>> categoryData;
    private Context context;
    public ZoneAdapter(Context context, List<HashMap<String, String>> categoryData) {
        this.context = context;
        this.categoryData = categoryData;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmembers_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final String Usr_id = categoryData.get(position).get("ZONECODE");
        final String Usr_nm = categoryData.get(position).get("ZONENAME");
        String PWD = categoryData.get(position).get("ZONENAMEENG");
        String Dist = categoryData.get(position).get("KHETCODE");
        String Lst_updt = categoryData.get(position).get("Lst_updt");
        String Lst_usr = categoryData.get(position).get("Lst_usr");
        String pc_nm = categoryData.get(position).get("pc_nm");
        String Call = categoryData.get(position).get("Call");
        holder.txtCategoryName.setText(PWD);


    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtCategoryName;
        ImageView imgDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategoryName = itemView.findViewById(R.id.txt_category_name);

            imgDelete = itemView.findViewById(R.id.img_delete);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
          //  Intent i = new Intent(context, EditCategoryActivity.class);
           // Intent i = new Intent(context, EditProductActivity.class);
            CALL =  categoryData.get(getAdapterPosition()).get("Call");

         //   context.startActivity(i);

          //  context.startActivity(i);
        }
    }


}
