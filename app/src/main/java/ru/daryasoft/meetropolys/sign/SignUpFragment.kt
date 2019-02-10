package ru.daryasoft.meetropolys.sign

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.daryasoft.meetropolys.MainActivity
import ru.daryasoft.meetropolys.R
import ru.daryasoft.meetropolys.model.Account
import java.util.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sign_up.setOnClickListener {
            val email = sign_email.text.toString()
            val password = sign_password.text.toString()
            val account = Account(UUID.randomUUID().toString(), email, password, true)

           Realm.getDefaultInstance().use { realm ->
               realm.executeTransaction {
                   realm.copyToRealm(account)
               }
           }

            val activity = requireActivity()
            activity.startActivityFromFragment(this, Intent(activity, MainActivity::class.java), 0)
            activity.finish()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}
