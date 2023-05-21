package com.knowledge.managmentimenpost.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knowledge.managmentimenpost.ApiServices;
import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.Roozh;
import com.knowledge.managmentimenpost.adapter.adapterRecyclerViewPaymentModule;
import com.knowledge.managmentimenpost.adapter.adapterRecyclerViewTicket;
import com.knowledge.managmentimenpost.dataModule.DataModuleSituationPaymentAct;
import com.knowledge.managmentimenpost.dataModule.DataModuleTicket;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class SituationPaymentActivity extends AppCompatActivity {
    TextView textViewFromDate, textViewToDate,textViewIsPaid,textViewIsntPaid;
    Button buttonSearch;
    RecyclerView recyclerView;
    ApiServices apiServices;
    String strFromDate = null, strToDate = null;
    String TAG = "tag";

    long todayDate = 0, entryDate = 0, resDate = 0;
    private PersianDatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    private int flagTextView;
    private final int FROM_ALL_DATE = 2;
    private final int TO_ALL_DATE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        setupInitialize();
        setupControl();

    }

    private void setupInitialize() {

        buttonSearch = findViewById(R.id.btn_search_paidAct);
        textViewFromDate = findViewById(R.id.txtV_input_from_paidAct);
        textViewToDate = findViewById(R.id.txtV_input_to_paidAct);
        textViewIsPaid=findViewById(R.id.txtView_sum_isPaid_paidAct);
        textViewIsntPaid=findViewById(R.id.txtView_sum_isntPaid_paidAct);

//        sharedClass = new SharedClass(this);
        datePickerDialog = new PersianDatePickerDialog(this);

        recyclerView = findViewById(R.id.recyClerView_paidAct);
        apiServices = new ApiServices(this);
    }

    private void setupControl() {
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(year, month, day);

        Roozh roozh = new Roozh();
        roozh.GregorianToPersian(year, month, day);

        initDate.setTimeInMillis(calendar.getTimeInMillis());
        todayDate = initDate.getTimeInMillis();
        todayDate = todayDate / (24 * 60 * 60 * 1000);


        datePickerDialog.setNegativeButton("بیخیال")
                .setPositiveButtonString("تایید")
                .setTodayButton("تاریخ امروز")
                .setTodayButtonVisible(true)
                .setInitDate(initDate)
                .setActionTextColor(ContextCompat.getColor(SituationPaymentActivity.this, R.color.Black))
                .setMaxYear(roozh.getYear() + 5)
                .setMinYear(roozh.getYear())
                .setBackgroundColor(ContextCompat.getColor(SituationPaymentActivity.this, R.color.Green))
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        entryDate = persianCalendar.getTimeInMillis();
                        entryDate = entryDate / (24 * 60 * 60 * 1000);
                        resDate = entryDate - todayDate;

                        switch (flagTextView) {
                            case FROM_ALL_DATE:
                                textViewFromDate.setText(persianCalendar.getPersianShortDate());
                                break;
                            case TO_ALL_DATE:
                                textViewToDate.setText(persianCalendar.getPersianShortDate());
                                break;
                        }
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        textViewFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagTextView = FROM_ALL_DATE;
                datePickerDialog.show();
            }
        });
        textViewToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagTextView = TO_ALL_DATE;
                datePickerDialog.show();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strFromDate = textViewFromDate.getText().toString();
                strToDate = textViewToDate.getText().toString();
                final List<DataModuleSituationPaymentAct> dataModules=new ArrayList<>();
                if (strFromDate.isEmpty() && strToDate.isEmpty()) {
                    Toast.makeText(SituationPaymentActivity.this, "لطفا تاریخ های مربوط به گذشته راوارد کنید!", Toast.LENGTH_SHORT).show();
                } else {
                    final JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Token", "jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
                        jsonObject.put("FromDate", strFromDate);
                        jsonObject.put("ToDate", strToDate);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("tagerr", "onClick: " + jsonObject.toString());
                    apiServices.sendDataJsonObject(ApiServices.GetStatePayment, jsonObject, new ApiServices.OnGetResponseData() {
                        @Override
                        public void onGetData(JSONObject responseData) {
                            String indexJson = "";
                            Log.i(TAG, "onGetData: " + responseData.toString());
                            dataModules.clear();
                            for (int i = 0; i < responseData.length(); i++) {
                                indexJson = "Ticket ".concat(String.valueOf(i));
                                try {
                                    JSONObject subJson = responseData.getJSONObject(indexJson);
                                    if (indexJson.equals("Ticket 0")){
                                        textViewIsPaid.setText(subJson.getString("IsPaid"));
                                        textViewIsntPaid.setText(subJson.getString("DontPaid"));
                                    }
                                    DataModuleSituationPaymentAct dataModuleSituationPaymentAct = new DataModuleSituationPaymentAct();

                                    dataModuleSituationPaymentAct.setCodeFactor(subJson.getString("IdTicket"));
                                    dataModuleSituationPaymentAct.setDateFactor(subJson.getString("RequestDate"));
                                    dataModuleSituationPaymentAct.setInfoPost(subJson.getString("InfoPost"));
                                    dataModuleSituationPaymentAct.setSender(subJson.getString("Sender"));
                                    dataModuleSituationPaymentAct.setReceiver(subJson.getString("Receiver"));
                                    dataModuleSituationPaymentAct.setFactorPrice(subJson.getString("Amount"));
                                    dataModuleSituationPaymentAct.setIsPay(subJson.getString("IsPay"));

                                    dataModules.add(dataModuleSituationPaymentAct);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapterRecyclerViewPaymentModule adaptor = new adapterRecyclerViewPaymentModule(SituationPaymentActivity.this,dataModules);
                            recyclerView.setLayoutManager(new GridLayoutManager(SituationPaymentActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adaptor);
                        }
                    });
                }
            }
        });
    }
}
