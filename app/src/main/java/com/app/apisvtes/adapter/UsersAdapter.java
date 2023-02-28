package com.app.apisvtes.adapter;

import static com.app.apisvtes.conn.ClassLibs.Dist1;
import static com.app.apisvtes.conn.ClassLibs.PWD1;
import static com.app.apisvtes.conn.ClassLibs.Usr_id1;
import static com.app.apisvtes.conn.ClassLibs.Usr_nm1;

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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private List<HashMap<String, String>> categoryData;
    private Context context;
    public UsersAdapter(Context context, List<HashMap<String, String>> categoryData) {
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
        final String Usr_id = categoryData.get(position).get("Usr_id");
        final String Usr_nm = categoryData.get(position).get("Usr_nm");
        String PWD = categoryData.get(position).get("PWD");
        String Dist = categoryData.get(position).get("Dist");
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
            Usr_id1 =  categoryData.get(getAdapterPosition()).get("Usr_id");
            Usr_nm1 =  categoryData.get(getAdapterPosition()).get("Usr_nm");
            PWD1 =  categoryData.get(getAdapterPosition()).get("PWD");
            Dist1 =  categoryData.get(getAdapterPosition()).get("Dist");
         //   context.startActivity(i);

          //  context.startActivity(i);
        }
    }


}
