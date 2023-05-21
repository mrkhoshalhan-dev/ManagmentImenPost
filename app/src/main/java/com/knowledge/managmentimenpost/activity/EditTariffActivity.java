package com.knowledge.managmentimenpost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.knowledge.managmentimenpost.ApiServices;
import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.SharedClass;

import org.json.JSONException;
import org.json.JSONObject;

public class EditTariffActivity extends AppCompatActivity {
    private static final String TAG = "logeee";
    EditText editTextFreight, editTextKhadamat, editTextSokht, editTextBasteBandi, editTextBimeh, editTextArzeshAfzodeh;
    Button buttonSave;

    String Freight, Khadamat, Sokht, BasteBandi, Bimeh, ArzeshAfzodeh;


    SharedClass sharedClass;
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tariff);

        setupInitialize();
        setupControl();
    }

    private void setupControl() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Token", "jhslfjkhdsjilhie8u9435huihe87f438yr35907965");
            jsonObject.put("TypeRequest", "GetTariff");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "setupControl: " + jsonObject.toString());
        apiServices.sendDataJsonObject(ApiServices.GetTicketManagment, jsonObject, new ApiServices.OnGetResponseData() {
            @Override
            public void onGetData(JSONObject responseData) {
                Log.i(TAG, "onGetData: " + responseData.toString());

                try {
                    editTextFreight.setText(responseData.getString("Freight"));
                    editTextArzeshAfzodeh.setText(responseData.getString("ArzeshAfzodeh"));
                    editTextBasteBandi.setText(responseData.getString("BasteBandi"));
                    editTextBimeh.setText(responseData.getString("Bimeh"));
                    editTextKhadamat.setText(responseData.getString("Khadamat"));
                    editTextSokht.setText(responseData.getString("Sokht"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Freight = editTextFreight.getText().toString();
                ArzeshAfzodeh = editTextArzeshAfzodeh.getText().toString();
                BasteBandi = editTextBasteBandi.getText().toString();
                Bimeh = editTextBimeh.getText().toString();
                Khadamat = editTextKhadamat.getText().toString();
                Sokht = editTextSokht.getText().toString();


                if (Freight.length() >0 && Khadamat.length() >0 && Sokht.length() >0 && BasteBandi.length() >0 && Bimeh.length() >0 && ArzeshAfzodeh.length() >0){

                    try {
                        jsonObject.put("TypeRequest", "SaveTariff");

                        jsonObject.put("Freight",Freight);
                        jsonObject.put("ArzeshAfzodeh",ArzeshAfzodeh);
                        jsonObject.put("BasteBandi",BasteBandi);
                        jsonObject.put("Bimeh",Bimeh);
                        jsonObject.put("Khadamat",Khadamat);
                        jsonObject.put("Sokht",Sokht);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "onClick: "+jsonObject.toString());

                    apiServices.sendDataJsonObject(ApiServices.GetTicketManagment, jsonObject, new ApiServices.OnGetResponseData() {
                        @Override
                        public void onGetData(JSONObject responseData) {
                            try {
                                if (responseData.getBoolean("Response")){
                                    Toast.makeText(EditTariffActivity.this, "تغییرات با موفقیت ذخیره گردید!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }else{
                    Toast.makeText(EditTariffActivity.this, "لطفا مقادیر بالا را پر کنید!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupInitialize() {
        editTextFreight = findViewById(R.id.edtText_freight_editTariffAct);
        editTextKhadamat = findViewById(R.id.edtText_khadamat_editTariffAct);
        editTextSokht = findViewById(R.id.edtText_sokht_editTariffAct);
        editTextBasteBandi = findViewById(R.id.edtText_bastebandi_editTariffAct);
        editTextBimeh = findViewById(R.id.edtText_bimeh_editTariffAct);
        editTextArzeshAfzodeh = findViewById(R.id.edtText_arzeshafzodeh_editTariffAct);

        buttonSave = findViewById(R.id.btn_save_editTariffAct);
        sharedClass = new SharedClass(EditTariffActivity.this);
        apiServices = new ApiServices(EditTariffActivity.this);
    }
}
