package uk.co.real_technologies.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uk.co.real_technologies.R
import uk.co.real_technologies.activities.MainActivity

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        view.findViewById<View>(R.id.button_next).setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish() }
        return view
    }
}