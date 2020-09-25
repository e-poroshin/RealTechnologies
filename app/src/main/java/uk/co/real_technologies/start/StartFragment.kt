package uk.co.real_technologies.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uk.co.real_technologies.R

class StartFragment : Fragment(), View.OnClickListener {

    private val buttonIDs = listOf(
        R.id.buttonSignIn,
        R.id.buttonSignUp,
        R.id.button1,
        R.id.button2,
        R.id.button3,
        R.id.button4,
        R.id.button5,
        R.id.button6,
        R.id.button7,
        R.id.button8,
        R.id.button9,
        R.id.button0,
        R.id.buttonFingerprint,
        R.id.buttonFaceId,
        R.id.buttonHome,
        R.id.buttonMenu
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        for (id in buttonIDs) {
            view.findViewById<View>(id).setOnClickListener(this)
        }
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSignIn -> showToast("Sign In")
            R.id.buttonSignUp -> openSignUpFragment()
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
        Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
    }

    private fun openSignUpFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerStart,
                SignUpFragment(),
                SignUpFragment::class.java.name
            )
            .commit()
    }

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }
}