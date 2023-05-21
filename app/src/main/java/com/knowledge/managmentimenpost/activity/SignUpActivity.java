package com.knowledge.managmentimenpost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.knowledge.managmentimenpost.R;
import com.knowledge.managmentimenpost.SharedClass;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextPass, editTextNewPass, editTextVerifyNewPass;
    TextView textViewChangePass;
    Button buttonEnter;
    SharedClass sharedClass;
    String pass, newPass, verifyNewPass;
    int isChange = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupInitialize();
        setupControl();
    }

    private void setupControl() {

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChange == 0) {
                    pass = editTextPass.getText().toString();

                    if (pass.equals(sharedClass.getPass())) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else {
                        Toast.makeText(SignUpActivity.this, "رمز وارد شده اشتباه است!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    pass = editTextPass.getText().toString();
                    newPass = editTextNewPass.getText().toString();
                    verifyNewPass = editTextVerifyNewPass.getText().toString();

                    if (pass.equals(sharedClass.getPass())) {
                        if (newPass.equals(verifyNewPass)) {
                            sharedClass.savePass(newPass);

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            finish();
                            startActivity(intent);
                            overridePendingTransition(0, 0);

                            Toast.makeText(SignUpActivity.this, "رمز عبور جدید تغییر یافت!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "رمزهای وارد شده جدید با هم مطابقت ندارند!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "رمز وارد شده قدیمی اشتباه است!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        textViewChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNewPass.setVisibility(View.VISIBLE);
                editTextVerifyNewPass.setVisibility(View.VISIBLE);
                editTextPass.setHint("رمز قدیمی");

                isChange=1;
            }
        });
    }

    private void setupInitialize() {
        editTextPass = findViewById(R.id.edtText_pass_signUpAct);
        editTextNewPass = findViewById(R.id.edtText_new_pass_signUpAct);
        editTextVerifyNewPass = findViewById(R.id.edtText_verify_new_pass_signUpAct);
        textViewChangePass = findViewById(R.id.txtView_change_pass_signUpAct);
        buttonEnter = findViewById(R.id.btn_enter_signUpAct);
        sharedClass = new SharedClass(this);
    }
}
