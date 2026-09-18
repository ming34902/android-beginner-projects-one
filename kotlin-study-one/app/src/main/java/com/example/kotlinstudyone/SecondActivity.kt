package com.example.kotlinstudyone

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinstudyone.constants.AppConstants

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        // 1. 接收 MainActivity 传来的文本\\
        val text2 = findViewById<TextView>(R.id.text2)
        intent.getStringExtra(AppConstants.IntentKeys.EXTRA_TEXT)?.let { passedText ->
            text2.text = passedText
        }

        // 2. 点击 button2 返回 MainActivity
        findViewById<Button>(R.id.button2).setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}