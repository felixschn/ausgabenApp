package com.example.felix.ausgaben2;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterDataActivity extends AppCompatActivity {

    public static final String RETVAL_KEY = "RETURN STRING";

    private EditText enterDataString, enterDataAmount, enterDataDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);
        enterDataString = (EditText) findViewById(R.id.enterDataString);
        enterDataAmount =  (EditText)findViewById(R.id.enterDataAmount);
        enterDataDate = (EditText)findViewById(R.id.enterDataDate);

        Button buttonZurueck = (Button) findViewById(R.id.buttonSend);
        buttonZurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultData = new Intent();
                resultData.putExtra("amount",enterDataAmount.getText().toString());
                resultData.putExtra("string",enterDataString.getText().toString());
                resultData.putExtra("buyDate",enterDataDate.getText().toString());
                setResult(Activity.RESULT_OK, resultData);
                finish();
            }
        });




    }
}
