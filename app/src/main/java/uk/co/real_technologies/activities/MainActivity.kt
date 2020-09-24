package uk.co.real_technologies.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uk.co.real_technologies.accounts.AccountsFragment
import uk.co.real_technologies.categories.CategoriesFragment
import uk.co.real_technologies.fragments.OnMainFragmentsActionListener
import uk.co.real_technologies.operations.OperationsFragment
import uk.co.real_technologies.statistics.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.co.real_technologies.R

class MainActivity : AppCompatActivity(), OnMainFragmentsActionListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val isLoggedIn: Boolean = sharedPreferences.getBoolean(SAVED_STATE, false)
//        if (!isLoggedIn) {
            startActivity(Intent(this, StartActivity::class.java))
            true.saveLogIn()
//        }
        setContentView(R.layout.activity_main)
        onOpenOperationsFragment()
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_categories -> onOpenCategoriesFragment()
                R.id.action_accounts -> onOpenAccountsFragment()
                R.id.action_operations -> onOpenOperationsFragment()
                R.id.action_statistics -> onOpenStatisticsFragment()
            }
            true
        })
    }

    private fun Boolean.saveLogIn() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(SAVED_STATE, this)
        editor.apply()
    }

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(baseContext, R.string.backPressed,
                    Toast.LENGTH_SHORT).show()
            back_pressed = System.currentTimeMillis()
        }
    }

    override fun onOpenOperationsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMain, OperationsFragment.newInstance(), OperationsFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenAccountsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMain, AccountsFragment.newInstance(), AccountsFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenCategoriesFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMain, CategoriesFragment.newInstance(), CategoriesFragment::class.java.simpleName)
                .commit()
    }

    override fun onOpenStatisticsFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMain, StatisticsFragment.newInstance(), StatisticsFragment::class.java.simpleName)
                .commit()
    }

    companion object {
        private var back_pressed: Long = 0
        const val SAVED_STATE = "SAVED_STATE"
    }
}