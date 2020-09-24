package uk.co.real_technologies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity(), View.OnClickListener {

    private val buttonIDs = listOf(R.id.buttonSignIn, R.id.buttonSignUp, R.id.button1, R.id.button2,
        R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
        R.id.button9, R.id.button0, R.id.buttonFingerprint, R.id.buttonFaceId, R.id.buttonHome, R.id.buttonMenu)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        for (id in buttonIDs) {
            findViewById<View>(id).setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.buttonSignIn -> finish()
            R.id.buttonSignUp -> finish()
            R.id.button1 -> showToast("One")
            R.id.button2 -> showToast("Two")
            R.id.button3 -> showToast("Three")
            R.id.button4 -> showToast("Four")
            R.id.button5 -> showToast("Five")
            R.id.button6 -> showToast("Six")
            R.id.button7 -> showToast("Seven")
            R.id.button8 -> showToast("Eight")
            R.id.button9 -> showToast("Nine")
            R.id.button0 -> showToast("Zero")
            R.id.buttonFingerprint -> showToast("Fingerprint")
            R.id.buttonFaceId -> showToast("FaceId")
            R.id.buttonHome -> showToast("Home")
            R.id.buttonMenu -> showToast("Menu")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}