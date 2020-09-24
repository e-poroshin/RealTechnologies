package uk.co.real_technologies.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uk.co.real_technologies.R
import uk.co.real_technologies.fragments.OnStartFragmentsActionListener
import uk.co.real_technologies.start.StartFragment

class StartActivity : AppCompatActivity(), OnStartFragmentsActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_start)
        onOpenStartFragment()
    }

    override fun onOpenStartFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerStart, StartFragment.newInstance(), StartFragment::class.java.name)
    }

    override fun onOpenSignInFragment() {
        TODO("Not yet implemented")
    }

    override fun onOpenSignUpFragment() {
        TODO("Not yet implemented")
    }


}