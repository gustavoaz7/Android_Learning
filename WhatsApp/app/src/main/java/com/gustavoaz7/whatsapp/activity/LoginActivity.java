package com.gustavoaz7.whatsapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gustavoaz7.whatsapp.R;
import com.gustavoaz7.whatsapp.helper.Permissions;
import com.gustavoaz7.whatsapp.helper.Preferences;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private String[] permissions = new String[]{
            Manifest.permission.SEND_SMS
    };
    private EditText phone;
    private EditText phoneArea;
    private EditText phoneCountry;
    private EditText name;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissions.validatePermissions(1, this, permissions);

        phone = findViewById(R.id.login_phone);
        phoneArea = findViewById(R.id.login_phone_area);
        phoneCountry = findViewById(R.id.login_phone_country);
        name = findViewById(R.id.login_name);
        register = findViewById(R.id.login_register_btn);

        // Creating masks for text inputs
        SimpleMaskFormatter simpleMaskPhone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskPhoneArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskPhoneCountry = new SimpleMaskFormatter("+NN");

        MaskTextWatcher maskPhone = new MaskTextWatcher(phone, simpleMaskPhone);
        MaskTextWatcher maskPhoneArea = new MaskTextWatcher(phoneArea, simpleMaskPhoneArea);
        MaskTextWatcher maskPhoneCountry = new MaskTextWatcher(phoneCountry, simpleMaskPhoneCountry);

        phone.addTextChangedListener(maskPhone);
        phoneArea.addTextChangedListener(maskPhoneArea);
        phoneCountry.addTextChangedListener(maskPhoneCountry);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String userPhone = (
                        phoneCountry.getText().toString() +
                        phoneArea.getText().toString() +
                        phone.getText().toString()
                ).replace("+", "").replace("-", "");

                // Generate Token
                Random random = new Random();
                int randomNum = random.nextInt(9999 - 1000) + 1000;
                String token = String.valueOf(randomNum);

                // Save user info for validation
                Preferences preferences = new Preferences(LoginActivity.this);
                preferences.saveUserPreferences(username, userPhone, token);

                // Send SMS
                String message = "WhatsApp Validation Code: " + token;
                boolean isSMSSuccess = sendSMS("+" + userPhone, message);

                if (isSMSSuccess) {
                    Intent intent = new Intent(LoginActivity.this, ValidatorActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Failed sending SMS. Please try again.", Toast.LENGTH_LONG).show();
                }
//                HashMap<String, String> userPreferences = preferences.getUserPreferences();
//                Log.d(">>>>USER", userPreferences.toString());
            }
        });
    }

    private boolean sendSMS(String phone, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                alertValidationPermission();
            }

        }
    }

    private void alertValidationPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission denied");
        builder.setMessage("In order to register a new usr]er, you must accept the permission request");

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
