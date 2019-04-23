package com.gustavoaz7.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gustavoaz7.whatsapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText phone;
    private EditText phoneArea;
    private EditText phoneCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.login_phone);
        SimpleMaskFormatter simpleMaskPhone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher maskPhone = new MaskTextWatcher(phone, simpleMaskPhone);
        phone.addTextChangedListener(maskPhone);

        phoneArea = findViewById(R.id.login_phone_area);
        SimpleMaskFormatter simpleMaskPhoneArea = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskPhoneArea = new MaskTextWatcher(phoneArea, simpleMaskPhoneArea);
        phoneArea.addTextChangedListener(maskPhoneArea);

        phoneCountry = findViewById(R.id.login_phone_country);
        SimpleMaskFormatter simpleMaskPhoneCountry = new SimpleMaskFormatter("+NN");
        MaskTextWatcher maskPhoneCountry = new MaskTextWatcher(phoneCountry, simpleMaskPhoneCountry);
        phoneCountry.addTextChangedListener(maskPhoneCountry);
    }
}
