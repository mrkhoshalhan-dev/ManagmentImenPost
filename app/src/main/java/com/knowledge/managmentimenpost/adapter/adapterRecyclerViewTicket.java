package com.knowledge.managmentimenpost.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.activity.DetailsMarsolehActivity;
import com.knowledge.managmentimenpost.dataModule.DataModuleTicket;

import java.util.List;

public class adapterRecyclerViewTicket extends RecyclerView.Adapter<adapterRecyclerViewTicket.MyViewHolder> {
    final String TAG="tag";
    private Context context;
    private List<DataModuleTicket> dataModuleTickets;

    public adapterRecyclerViewTicket(Context context, List<DataModuleTicket>dataModuleTickets){
        this.context = context;
        this.dataModuleTickets = dataModuleTickets;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_view_tickets_act,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final DataModuleTicket dataModuleTicket=dataModuleTickets.get(position);

        holder.textViewPostType.setText(dataModuleTicket.getPostType());
        holder.textViewServiceType.setText(dataModuleTicket.getServiceType());
        holder.textViewWeight.setText(dataModuleTicket.getWeight());
        holder.textViewValueWare.setText(dataModuleTicket.getValueWare());
        holder.textViewLength.setText(dataModuleTicket.getLength());
        holder.textViewHorizontal.setText(dataModuleTicket.getHorizontal());
        holder.textViewVertical.setText(dataModuleTicket.getVertical());
        holder.textViewContent.setText(dataModuleTicket.getContent());
        holder.textViewRequestDate.setText(dataModuleTicket.getRequestDate());
        holder.textViewNumTicket.setText(dataModuleTicket.getNumTicket());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsMarsolehActivity.class);
                intent.putExtra("IdTicket",dataModuleTicket.getIdTicket());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModuleTickets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewPostType,textViewServiceType,textViewWeight,textViewValueWare,textViewLength,textViewHorizontal,textViewVertical,textViewContent,textViewRequestDate,textViewNumTicket;
        Button buttonDetails;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPostType =itemView.findViewById(R.id.txtView_type_post_recycler_ticket);
            textViewServiceType =itemView.findViewById(R.id.txtView_service_type_recycler_ticket);
            textViewWeight =itemView.findViewById(R.id.txtView_weight_recycler_ticket);
            textViewValueWare =itemView.findViewById(R.id.txtView_value_ware_recycler_ticket);
            textViewLength =itemView.findViewById(R.id.txtView_length_recycler_ticket);
            textViewHorizontal =itemView.findViewById(R.id.txtView_horizontal_recycler_ticket);
            textViewVertical =itemView.findViewById(R.id.txtView_vertical_recycler_ticket);
            textViewContent =itemView.findViewById(R.id.txtView_content_recycler_ticket);
            textViewRequestDate =itemView.findViewById(R.id.txtView_create_date_recycler_ticket);
            textViewNumTicket =itemView.findViewById(R.id.txtView_num_ticket_recycler_ticket);

            buttonDetails =itemView.findViewById(R.id.btn_details_recycler_ticket);
        }
    }
}
