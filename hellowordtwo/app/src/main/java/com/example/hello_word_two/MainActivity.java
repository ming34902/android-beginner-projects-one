package com.example.hello_word_two;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Android one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_edittext);
//        setContentView(R.layout.activity_main_relative);
//        setContentView(R.layout.activity_main_vertical);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d(TAG,"onCreate: 第一个android");

        EditText editText1 = findViewById(R.id.editText1);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("editText1-afterTextChanged", "文本变化之后");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("editText1-beforeTextChanged", "文本变化之前");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentText1 = s.toString().trim();
                Log.d("editText1-onTextChanged", currentText1);
            }
        });
    }
}