package com.example.studyanroidlog;



import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainLogActivity extends AppCompatActivity {

    private static final String TAG = "按钮点击";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        System.out.println("MainLogActivity-start");
        Log.d("tag", "MainLogActivity-start2");

        Button button1 = findViewById(R.id.myButton1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onButtonClick-2-setOnClickListener");
                Toast toast1 = Toast.makeText(MainLogActivity.this, "send按钮点击", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 10, 2000);
                toast1.show();
            }
        });
    }

//    public void onButtonClick(View view) {
//        Log.d(TAG, "onButtonClick-1");
//    }
}