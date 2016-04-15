package com.example.customviewexample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.imageAndText) ImageAndTextView imageAndText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*imageAndText.setImageResource(R.drawable.change_profile);
        imageAndText.setText("ssss");*/
    }
}
