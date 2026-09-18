package com.example.kotlinstudyone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinstudyone.constants.AppConstants
import com.example.kotlinstudyone.ui.theme.KotlinStudyOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            KotlinStudyOneTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "KotlinStudyOne",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button1)

        //create a listener to listen to when the button is clicked and return result
        rollButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(AppConstants.IntentKeys.EXTRA_TEXT, getString(R.string.textToTwo))
            }
            startActivity(intent)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinStudyOneTheme {
        Greeting("KotlinStudyOne")
    }
}