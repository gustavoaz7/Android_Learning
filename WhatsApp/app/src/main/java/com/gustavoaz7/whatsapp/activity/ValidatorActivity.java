package com.gustavoaz7.whatsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.gustavoaz7.whatsapp.R;
import com.gustavoaz7.whatsapp.helper.Preferences;

import java.util.HashMap;

public class ValidatorActivity extends AppCompatActivity {

    private EditText validatorCode;
    private Button validatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);

        validatorCode = findViewById(R.id.validator_code);
        validatorButton = findViewById(R.id.validator_btn);

        SimpleMaskFormatter simpleMaskCode = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskCode = new MaskTextWatcher(validatorCode, simpleMaskCode);
        validatorCode.addTextChangedListener(maskCode);

        validatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences preferences = new Preferences(ValidatorActivity.this);
                HashMap<String, String> userPreferences = preferences.getUserPreferences();
                String generatedToken = userPreferences.get("token");
                String inputedToken = validatorCode.getText().toString();

                if (generatedToken.equals(inputedToken)) {
                    Toast.makeText(ValidatorActivity.this, "Valid Token.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ValidatorActivity.this, "Invalid Token.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
