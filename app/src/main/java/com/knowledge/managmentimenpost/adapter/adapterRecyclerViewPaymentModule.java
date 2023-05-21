package com.knowledge.managmentimenpost.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.dataModule.DataModuleSituationPaymentAct;

import java.util.List;

public class adapterRecyclerViewPaymentModule extends RecyclerView.Adapter<adapterRecyclerViewPaymentModule.MyViewHolder> {
    final String TAG="tag";
    private Context context;
    private List<DataModuleSituationPaymentAct> dataModuleSituationPaymentActs;

    public adapterRecyclerViewPaymentModule(Context context, List<DataModuleSituationPaymentAct> dataModuleSituationPaymentActs){
        this.context = context;
        this.dataModuleSituationPaymentActs = dataModuleSituationPaymentActs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_view_situation_payment,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final DataModuleSituationPaymentAct dataModule = dataModuleSituationPaymentActs.get(position);
        holder.textViewFactorCode.setText(dataModule.getCodeFactor());
        holder.textViewFactorPrice.setText(dataModule.getFactorPrice());
        holder.textViewSender.setText(dataModule.getSender());
        holder.textViewReceiver.setText(dataModule.getReceiver());
        holder.textViewContent.setText(dataModule.getInfoPost());
        holder.textViewFactorDate.setText(dataModule.getDateFactor());

        if (dataModule.getIsPay().equals("1")){
            holder.textViewIsPay.setTextColor(ContextCompat.getColor(context,R.color.Green));
            holder.textViewIsPay.setText("پرداخت شده است");
        }else if (dataModule.getIsPay().equals("0")){
            holder.textViewIsPay.setTextColor(ContextCompat.getColor(context,R.color.Red));
            holder.textViewIsPay.setText("پرداخت نشده است");
        }
    }

    @Override
    public int getItemCount() {
        return dataModuleSituationPaymentActs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewFactorCode,textViewFactorPrice, textViewSender,textViewReceiver,textViewContent,textViewFactorDate,textViewIsPay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIsPay=itemView.findViewById(R.id.txtView_isPay_recycler_situationPayAct);
            textViewSender =itemView.findViewById(R.id.txtView_sender_recycler_situationPayAct);
            textViewReceiver=itemView.findViewById(R.id.txtView_receiver_recycler_situationPayAct);
            textViewContent=itemView.findViewById(R.id.txtView_content_recycler_situationPayAct);
            textViewFactorCode=itemView.findViewById(R.id.txtView_code_recycler_situationPayAct);
            textViewFactorDate=itemView.findViewById(R.id.txtView_date_recycler_situationPayAct);
            textViewFactorPrice=itemView.findViewById(R.id.txtView_price_recycler_situationPayAct);
        }
    }
}
