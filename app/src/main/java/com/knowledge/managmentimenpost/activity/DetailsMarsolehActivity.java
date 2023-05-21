package com.knowledge.managmentimenpost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.knowledge.managmentimenpost.ApiServices;
import com.knowledge.managmentimenpost.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsMarsolehActivity extends AppCompatActivity {
    TextView textViewNameSender,textViewAddressSender,textViewPelackSender,textViewFlorSender,textViewPostCodeSender,textViewPhoneSender;
    TextView textViewNameReceiver,textViewAddressReceiver,textViewPelackReceiver,textViewFlorReceiver,textViewPostCodeReceiver,textViewPhoneReceiver;
    TextView textViewFreight,textViewKhadamat,textViewSokht,textViewBasteBandi,textViewBimeh,textViewArzeshAfzodeh,textViewTotal,textViewPayDate;

    String idTicket;
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_marsoleh);

        setupInitialize();
        setupControl();
    }

    private void setupControl() {
        idTicket=getIntent().getStringExtra("IdTicket");

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("Token","jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
            jsonObject.put("TypeRequest","SenderAndReceiver");
            jsonObject.put("IdTicket",idTicket);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        apiServices.sendDataJsonObject(ApiServices.GetTicketManagment, jsonObject, new ApiServices.OnGetResponseData() {
            @Override
            public void onGetData(JSONObject responseData) {

                try {
                    textViewNameSender.setText(responseData.getString("SenderName"));
                    textViewAddressSender.setText(responseData.getString("SenderAddress"));
                    textViewPelackSender.setText(responseData.getString("SenderPelack"));
                    textViewFlorSender.setText(responseData.getString("SenderFlor"));
                    textViewPostCodeSender.setText(responseData.getString("SenderPost"));
                    textViewPhoneSender.setText(responseData.getString("SenderPhone"));

                    textViewNameReceiver.setText(responseData.getString("ReceiverName"));
                    textViewAddressReceiver.setText(responseData.getString("ReceiverAddress"));
                    textViewPelackReceiver.setText(responseData.getString("ReceiverPelack"));
                    textViewFlorReceiver.setText(responseData.getString("ReceiverFlor"));
                    textViewPostCodeReceiver.setText(responseData.getString("ReceiverPost"));
                    textViewPhoneReceiver.setText(responseData.getString("ReceiverPhone"));

                    textViewFreight.setText(responseData.getString("Freight"));
                    textViewKhadamat.setText(responseData.getString("Khadamat"));
                    textViewSokht.setText(responseData.getString("Sokht"));
                    textViewBasteBandi.setText(responseData.getString("BasteBandi"));
                    textViewBimeh.setText(responseData.getString("Bimeh"));
                    textViewArzeshAfzodeh.setText(responseData.getString("ArzeshAzodeh"));
                    textViewTotal.setText(responseData.getString("Total"));
                    textViewPayDate.setText(responseData.getString("PayDate"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setupInitialize() {
        textViewNameSender=findViewById(R.id.txtView_name_sender_detailsAct);
        textViewAddressSender=findViewById(R.id.txtView_address_sender_detailsAct);
        textViewPelackSender=findViewById(R.id.txtView_pelak_sender_detailsAct);
        textViewFlorSender=findViewById(R.id.txtView_flor_sender_detailsAct);
        textViewPostCodeSender=findViewById(R.id.txtView_post_sender_detailsAct);
        textViewPhoneSender=findViewById(R.id.txtView_phone_sender_detailsAct);

        textViewNameReceiver=findViewById(R.id.txtView_name_receiver_detailsAct);
        textViewAddressReceiver=findViewById(R.id.txtView_address_receiver_detailsAct);
        textViewPelackReceiver=findViewById(R.id.txtView_pelak_receiver_detailsAct);
        textViewFlorReceiver=findViewById(R.id.txtView_flor_receiver_detailsAct);
        textViewPostCodeReceiver=findViewById(R.id.txtView_post_receiver_detailsAct);
        textViewPhoneReceiver=findViewById(R.id.txtView_phone_receiver_detailsAct);

        textViewFreight=findViewById(R.id.txtView_freight_expense_detailsAct);
        textViewKhadamat=findViewById(R.id.txtView_khadamat_expense_detailsAct);
        textViewSokht=findViewById(R.id.txtView_sokht_expense_detailsAct);
        textViewBasteBandi=findViewById(R.id.txtView_basteBandi_expense_detailsAct);
        textViewBimeh=findViewById(R.id.txtView_bimeh_expense_detailsAct);
        textViewArzeshAfzodeh=findViewById(R.id.txtView_arzeshAfzodeh_expense_detailsAct);
        textViewTotal=findViewById(R.id.txtView_total_expense_detailsAct);
        textViewPayDate=findViewById(R.id.txtView_pay_date_expense_detailsAct);

        apiServices=new ApiServices(DetailsMarsolehActivity.this);



    }
}
