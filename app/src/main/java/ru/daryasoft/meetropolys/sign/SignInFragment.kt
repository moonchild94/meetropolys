package ru.daryasoft.meetropolys.sign

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.daryasoft.meetropolys.R
import ru.daryasoft.meetropolys.Utils.Companion.hideKeyboard
import ru.daryasoft.meetropolys.Utils.Companion.showSnack
import ru.daryasoft.meetropolys.main.MainActivity
import ru.daryasoft.meetropolys.sign.presenter.ISignInPresenter
import ru.daryasoft.meetropolys.sign.presenter.SignInPresenter
import ru.daryasoft.meetropolys.sign.view.ISignInView

class SignInFragment : Fragment(), ISignInView {

    private lateinit var presenter: ISignInPresenter

    private lateinit var signInErrorText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SignInPresenter(this)
        signInErrorText = resources.getString(R.string.sign_in_error_text)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sign_in.setOnClickListener {
            hideKeyboard(requireActivity())

            val email = sign_email.text.toString()
            val password = sign_password.text.toString()
            presenter.logIn(email, password)
        }
    }

    override fun onLogIn(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            val activity = requireActivity()
            activity.startActivityFromFragment(this, Intent(activity, MainActivity::class.java), 0)
            activity.finish()
        } else {
            view?.let {
                showSnack(it, layoutInflater.inflate(R.layout.sign_error_snack, null), signInErrorText)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
