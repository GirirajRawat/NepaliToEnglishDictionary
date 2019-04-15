package com.example.nepalitoenglishdictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MeaningActivity extends AppCompatActivity {
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);



        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String meaning = bundle.getString("meaning");
            tvResult = findViewById(R.id.tvResult);
            tvResult.setText(meaning);
        }
        else{
            Toast.makeText(this, "No Meaning", Toast.LENGTH_SHORT).show();
        }
    }
}
