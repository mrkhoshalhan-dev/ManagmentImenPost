package com.knowledge.managmentimenpost;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiServices {
    private String server="imenpost.com";
//    private String server="192.168.43.249";

    public static final String GetUsers = "/imenPost/getUsersManagment.php";
    public static final String GetTicketManagment = "/imenPost/getTicketManagment.php";
    public static final String GetStatePayment = "/imenPost/getStatePay.php";

    private Context context;

    public ApiServices(Context context) {
        this.context = context;
    }

    public void sendDataJsonObject(String myUrl, @Nullable JSONObject jsonObject, @Nullable final OnGetResponseData onGetResponseData) {
        myUrl=server+myUrl;
//        myUrl = "http://" + myUrl;

        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                // if version code less lollipop use the http protocol
                //else use https or SSL protocol
                myUrl = "http://" + myUrl;
            } else {
                myUrl = "https://" + myUrl;
            }
        } catch (Exception e) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onGetResponseData.onGetData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_disconnected), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    error.printStackTrace();
                    message = "Parsing error! Please try again after some time!!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json; charset=utf-8");
                return header;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public void sendDataUrlOnly(String myUrl) {

//        myUrl = "http://" + myUrl;

        /*try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                // if version code less lollipop use the http protocol
                //else use https or SSL protocol
                myUrl = "http://" + myUrl;
            } else {
                myUrl = "https://" + myUrl;
            }
        } catch (Exception e) {
        }
*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_disconnected), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    error.printStackTrace();
                    message = "Parsing error! Please try again after some time!!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
//                    Toast.makeText(context, context.getString(R.string.connectivity_internet_lower), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json; charset=utf-8");
                return header;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public interface OnGetResponseData {
        void onGetData(JSONObject responseData);
    }
}
