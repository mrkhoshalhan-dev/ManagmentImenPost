package com.knowledge.managmentimenpost;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedClass {

    private static final String SHARED_DATA_SIGN_UP = "001100";

    private static final String KEY_SIGN_UP_PASS = "0011001";

    private Context context;

    public SharedClass(Context context) {
        this.context = context;
    }

    private SharedPreferences sharedPreferences;

    public void savePass(String pass) {
        sharedPreferences = context.getSharedPreferences(SHARED_DATA_SIGN_UP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_SIGN_UP_PASS, pass);
        editor.commit();
    }

    public String getPass() {
        sharedPreferences = context.getSharedPreferences(SHARED_DATA_SIGN_UP, Context.MODE_PRIVATE);
        String pass;

        pass = sharedPreferences.getString(KEY_SIGN_UP_PASS, "imenpostmanag");

        return pass;
    }
}
