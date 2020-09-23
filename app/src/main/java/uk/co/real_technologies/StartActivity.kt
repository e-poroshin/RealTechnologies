package uk.co.real_technologies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        findViewById<View>(R.id.buttonSignIn).setOnClickListener { finish() }
        findViewById<View>(R.id.buttonSignUp).setOnClickListener { finish() }
    }
}