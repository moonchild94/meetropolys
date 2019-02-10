package ru.daryasoft.meetropolys.sign

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.daryasoft.meetropolys.MainActivity
import ru.daryasoft.meetropolys.R
import ru.daryasoft.meetropolys.model.Account

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sign_in.setOnClickListener {
            val email = sign_email.text.toString()
            val password = sign_password.text.toString()
            var authorizationIsOk = false

            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    val account = realm
                        .where(Account::class.java)
                        .equalTo("email", email)
                        .equalTo("password", password)
                        .findFirst()
                    account?.let {
                        it.isActive = true
                        authorizationIsOk = true
                    }
                }
            }

            if (authorizationIsOk) {
                val activity = requireActivity()
                activity.startActivityFromFragment(this, Intent(activity, MainActivity::class.java), 0)
                activity.finish()
            }
            else {
                Toast.makeText(requireContext(), "Fail!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
