package uk.co.real_technologies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uk.co.real_technologies.R
import uk.co.real_technologies.fragments.OnStartFragmentsActionListener
import uk.co.real_technologies.start.StartFragment

class StartActivity : AppCompatActivity(), OnStartFragmentsActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        onOpenStartFragment()
    }

    override fun onOpenStartFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerStart,
                StartFragment.newInstance(),
                StartFragment::class.java.name
            )
            .commit()
    }
}