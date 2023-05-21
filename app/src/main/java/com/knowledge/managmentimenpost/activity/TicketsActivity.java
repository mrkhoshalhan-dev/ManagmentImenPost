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

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.knowledge.managmentimenpost.ApiServices;
import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.Roozh;
import com.knowledge.managmentimenpost.adapter.adapterRecyclerViewTicket;
import com.knowledge.managmentimenpost.adapterUsers;
import com.knowledge.managmentimenpost.dataModule.DataModuleTicket;
import com.knowledge.managmentimenpost.dataModule.DataModuleUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class TicketsActivity extends AppCompatActivity {
    TextView textViewFromDate, textViewToDate;
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
        setContentView(R.layout.activity_tickets);

        setupInitialize();
        setupControl();
    }

    private void setupInitialize() {

        buttonSearch = findViewById(R.id.btn_search_ticketsAct);
        textViewFromDate = findViewById(R.id.txtV_input_from_ticketsAct);
        textViewToDate = findViewById(R.id.txtV_input_to_ticketsAct);

//        sharedClass = new SharedClass(this);
        datePickerDialog = new PersianDatePickerDialog(this);

        recyclerView = findViewById(R.id.recyClerView_ticketAct);
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
                .setActionTextColor(ContextCompat.getColor(TicketsActivity.this, R.color.Black))
                .setMaxYear(roozh.getYear() + 5)
                .setMinYear(roozh.getYear())
                .setBackgroundColor(ContextCompat.getColor(TicketsActivity.this, R.color.Green))
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
                if (strFromDate.isEmpty() && strToDate.isEmpty()) {
                    Toast.makeText(TicketsActivity.this, "لطفا تاریخ های مربوط به گذشته راوارد کنید!", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Token", "jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
                        jsonObject.put("TypeRequest", "Ticket");
                        jsonObject.put("FromDate", strFromDate);
                        jsonObject.put("ToDate", strToDate);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("tagerr", "onClick: " + jsonObject.toString());
                    apiServices.sendDataJsonObject(ApiServices.GetTicketManagment, jsonObject, new ApiServices.OnGetResponseData() {
                        @Override
                        public void onGetData(JSONObject responseData) {
                            String valueCol = null;

                            List<DataModuleTicket> dataModuleTickets = new ArrayList<>();

                            Log.i("tager", "onGetData: " + responseData.toString());

                            for (int i = 0; i < responseData.length(); i++) {
                                valueCol = "Index ".concat(String.valueOf(i));
                                DataModuleTicket dataModuleTicket = new DataModuleTicket();

                                JSONObject OBJInJsonObject = null;
                                try {
                                    OBJInJsonObject = responseData.getJSONObject(valueCol);

                                    dataModuleTicket.setIdTicket(OBJInJsonObject.getString("IdTicket"));
                                    dataModuleTicket.setIdTblUser(OBJInJsonObject.getString("IdUser"));
                                    dataModuleTicket.setServiceType(OBJInJsonObject.getString("ServiceType"));
                                    dataModuleTicket.setWeight(OBJInJsonObject.getString("Weight"));
                                    dataModuleTicket.setValueWare(OBJInJsonObject.getString("ValueWare"));
                                    dataModuleTicket.setLength(OBJInJsonObject.getString("Length"));
                                    dataModuleTicket.setHorizontal(OBJInJsonObject.getString("Horizontal"));
                                    dataModuleTicket.setVertical(OBJInJsonObject.getString("Vertical"));
                                    dataModuleTicket.setContent(OBJInJsonObject.getString("Content"));
                                    dataModuleTicket.setPostType(OBJInJsonObject.getString("PostType"));
                                    dataModuleTicket.setRequestDate(OBJInJsonObject.getString("RequestDate"));
                                    dataModuleTicket.setNumTicket(OBJInJsonObject.getString("Index"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                dataModuleTickets.add(dataModuleTicket);
                            }

                            adapterRecyclerViewTicket adapterRecyclerViewTicket = new adapterRecyclerViewTicket(TicketsActivity.this, dataModuleTickets);
                            recyclerView.setLayoutManager(new GridLayoutManager(TicketsActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapterRecyclerViewTicket);
                        }
                    });
                }
            }
        });
    }
}
