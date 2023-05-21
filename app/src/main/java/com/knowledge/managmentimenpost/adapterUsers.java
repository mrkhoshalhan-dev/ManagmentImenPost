package com.knowledge.managmentimenpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knowledge.managmentimenpost.dataModule.DataModuleUser;

import java.util.List;

public class adapterUsers extends RecyclerView.Adapter<adapterUsers.MyViewHolder> {

    private Context context;
    private List<DataModuleUser> dataModuleUsers;

    public adapterUsers(Context context, List<DataModuleUser> dataModuleUsers) {
        this.context = context;
        this.dataModuleUsers = dataModuleUsers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_user_act, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModuleUser dataModuleUser=dataModuleUsers.get(position);

        holder.textViewIndex.setText(dataModuleUser.getIndexUser());
        holder.textViewName.setText(dataModuleUser.getName());
        holder.textViewCreateDate.setText(dataModuleUser.getDateCreate());
        holder.textViewUpdateDate.setText((dataModuleUser.getDateUpdate()));
        holder.textViewPhone.setText(dataModuleUser.getUserPhone());

    }

    @Override
    public int getItemCount() {
        return dataModuleUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIndex, textViewName, textViewCreateDate, textViewUpdateDate, textViewPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCreateDate = itemView.findViewById(R.id.txtView_date_create_recyclerUserAct);
            textViewUpdateDate = itemView.findViewById(R.id.txtView_last_update_recyclerUserAct);
            textViewIndex = itemView.findViewById(R.id.txtView_index_recyclerUserAct);
            textViewName = itemView.findViewById(R.id.txtView_name_recyclerUserAct);
            textViewPhone = itemView.findViewById(R.id.txtView_phone_recyclerUserAct);
        }
    }
}
