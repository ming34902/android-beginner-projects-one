package com.example.studyimagebotton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityImageButton extends AppCompatActivity {
    private Button btnOne;
    private ImageButton btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_image_button);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (ImageButton) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ImageButton 没有 getText()/setText()（它继承自 ImageView）
                // 改用 btnOne 的可用状态作为当前状态的依据
                boolean enable = !btnOne.isEnabled();
                if (enable) {
                    btnOne.setText("按钮可用");
                } else {
                    btnOne.setText("按钮不可用");
                }
                btnOne.setEnabled(enable);
                // ImageButton 用透明度做视觉反馈：可用=不透明，不可用=半透明
                btnTwo.setAlpha(enable ? 1f : 0.4f);
            }
        });

    }
}
