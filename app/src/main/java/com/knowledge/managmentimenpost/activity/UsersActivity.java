package com.knowledge.managmentimenpost.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.knowledge.managmentimenpost.ApiServices;
import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.Roozh;
import com.knowledge.managmentimenpost.SharedClass;
import com.knowledge.managmentimenpost.adapterUsers;
import com.knowledge.managmentimenpost.dataModule.DataModuleUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class UsersActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone;
    TextView textViewFromDate, textViewToDate;

    Button buttonSearchUniqUser, buttonSearchGraph;
    RecyclerView recyclerView;
    ApiServices apiServices;
    String strName, strPhone, strFromDate = null, strToDate = null;
    String TAG = "tag";

    ArrayList arrayListUsers = new ArrayList();
    ArrayList arrayListDateUsers = new ArrayList();

    BarChart barChartUser;

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
        setContentView(R.layout.activity_users_acitivty);

        setupInitialize();
        setupControl();
    }

    private void setupInitialize() {
        buttonSearchUniqUser = findViewById(R.id.btn_search_userAct);
        editTextName = findViewById(R.id.editText_name_usersAct);
        editTextPhone = findViewById(R.id.edtText_phone_signUpAct);

        barChartUser = findViewById(R.id.barChart_mySense_userAct);
        buttonSearchGraph = findViewById(R.id.btn_searchGraph_userAct);
        textViewFromDate = findViewById(R.id.txtV_input_from_userAct);
        textViewToDate = findViewById(R.id.txtV_input_to_userAct);

//        sharedClass = new SharedClass(this);
        datePickerDialog = new PersianDatePickerDialog(this);

        recyclerView = findViewById(R.id.recyclerView_userAct);
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
                .setActionTextColor(ContextCompat.getColor(UsersActivity.this, R.color.Black))
                .setMaxYear(roozh.getYear() + 5)
                .setMinYear(roozh.getYear())
                .setBackgroundColor(ContextCompat.getColor(UsersActivity.this, R.color.Green))
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
        buttonSearchGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strFromDate = textViewFromDate.getText().toString();
                strToDate = textViewToDate.getText().toString();
                if (strFromDate.isEmpty() && strToDate.isEmpty()) {
                    Toast.makeText(UsersActivity.this, "لطفا تاریخ های مربوط به گذشته راوارد کنید!", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setVisibility(View.GONE);
                    barChartUser.setVisibility(View.VISIBLE);

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Token", "jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
                        jsonObject.put("TypeSearch", "All");
                        jsonObject.put("FromDate", strFromDate);
                        jsonObject.put("ToDate", strToDate);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("tagerr", "onClick: " + jsonObject.toString());
                    apiServices.sendDataJsonObject(ApiServices.GetUsers, jsonObject, new ApiServices.OnGetResponseData() {
                        @Override
                        public void onGetData(JSONObject responseData) {
                            String valueCol = null, createDate = null, strInfoUser = "Empty";

                            arrayListUsers.clear();
                            arrayListDateUsers.clear();

                            Log.i("tager", "onGetData: " + responseData.toString());

                            int countUser = 0, indexRate = 0;
                            for (int i = 0; i < responseData.length(); i++) {
                                valueCol = "Index ".concat(String.valueOf(i));

                                JSONObject OBJInJsonObject = null;
                                try {
                                    OBJInJsonObject = responseData.getJSONObject(valueCol);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                countUser = 0;
                                indexRate = 0;
                                createDate = null;
                                try {

                                    countUser = OBJInJsonObject.getInt("UserCount");
                                    indexRate = OBJInJsonObject.getInt("Index");
                                    createDate = OBJInJsonObject.getString("CreateDate");

                                    arrayListUsers.add(new BarEntry(countUser, indexRate));
                                    arrayListDateUsers.add(createDate);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            BarDataSet bardataset = new BarDataSet(arrayListUsers, "آمار کاربران موجود");
                            barChartUser.animateY(5000);
                            BarData data = new BarData(arrayListDateUsers, bardataset);
                            bardataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
                            barChartUser.setData(data);
                        }
                    });

                }
            }
        });
        buttonSearchUniqUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strName = editTextName.getText().toString();
                strPhone = editTextPhone.getText().toString();
                if (!strPhone.isEmpty() && strPhone.length() > 0 && !strName.isEmpty() && strName.length() > 0) {

                    recyclerView.setVisibility(View.VISIBLE);
                    barChartUser.setVisibility(View.GONE);

                    final List<DataModuleUser> dataModuleUsers = new ArrayList<>();

                    final JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Token", "jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
                        jsonObject.put("TypeSearch", "Uniq");
                        jsonObject.put("UserName", strName);
                        jsonObject.put("Phone", strPhone);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "onClick: " + jsonObject.toString());

                    apiServices.sendDataJsonObject(ApiServices.GetUsers, jsonObject, new ApiServices.OnGetResponseData() {
                        @Override
                        public void onGetData(JSONObject responseData) {
                            String indexJson = "", IsExist = "Exit";
                            try {
                                IsExist = responseData.getString("Exist");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (!IsExist.equals("NotExist")) {
                                Log.i(TAG, "onGetData: " + responseData.toString());
                                dataModuleUsers.clear();
                                for (int i = 1; i < responseData.length() + 1; i++) {
                                    indexJson = "Index ".concat(String.valueOf(i));
                                    Log.i(TAG, "onGetData: " + indexJson);
                                    Log.i(TAG, "onGetData3: " + indexJson);
                                    try {
                                        JSONObject subJson = responseData.getJSONObject(indexJson);
                                        DataModuleUser dataModuleUser = new DataModuleUser();


                                        dataModuleUser.setIndexUser(subJson.getString("Id"));
                                        dataModuleUser.setName(subJson.getString("UserName"));
                                        dataModuleUser.setUserPhone(subJson.getString("UserPhone"));
                                        dataModuleUser.setDateCreate(subJson.getString("CreateDate"));
                                        dataModuleUser.setDateUpdate(subJson.getString("UpdateDate"));
                                        Log.i(TAG, "onGetData1: " + dataModuleUser.getIndexUser() + dataModuleUser.getUserPhone());
                                        dataModuleUsers.add(dataModuleUser);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.i(TAG, "onGetData: " + e.getMessage());
                                    }
                                }
                                adapterUsers adapter = new adapterUsers(UsersActivity.this, dataModuleUsers);
                                recyclerView.setLayoutManager(new GridLayoutManager(UsersActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                                recyclerView.setAdapter(adapter);
                            }
                            else {
                                recyclerView.setVisibility(View.GONE);
                                Toast.makeText(UsersActivity.this, "کاربری با مشخصات بالا وجود ندارد!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(UsersActivity.this, "لطفا کادرهای بالا را پر کنید!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}