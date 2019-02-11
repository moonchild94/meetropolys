package ru.daryasoft.meetropolys.sign

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.daryasoft.meetropolys.R
import ru.daryasoft.meetropolys.Utils.Companion.hideKeyboard
import ru.daryasoft.meetropolys.Utils.Companion.showSnack
import ru.daryasoft.meetropolys.main.MainActivity
import ru.daryasoft.meetropolys.sign.presenter.SignUpPresenter
import ru.daryasoft.meetropolys.sign.view.ISignUpView

class SignUpFragment : Fragment(), ISignUpView {

    private val emailRegex = Regex(emailPattern)

    private lateinit var incorrectEmailErrorText: String
    private lateinit var incorrectPasswordErrorText: String
    private lateinit var duplicateEmailErrorText: String

    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpPresenter(this)

        incorrectEmailErrorText = resources.getString(R.string.incorrect_email_error_text)
        incorrectPasswordErrorText = resources.getString(R.string.incorrect_password_error_text)
        duplicateEmailErrorText = resources.getString(R.string.duplicate_email_error_text)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sign_up.setOnClickListener {
            hideKeyboard(requireActivity())

            val email = sign_email.text.toString()
            if (!email.matches(emailRegex)) {
                showShackError(view, incorrectEmailErrorText)
                return@setOnClickListener
            }

            val password = sign_password.text.toString()
            if (password.isBlank()) {
                showShackError(view, incorrectPasswordErrorText)
                return@setOnClickListener
            }

            presenter.register(email, password)
        }
    }

    override fun onRegister(isSuccessfulRegister: Boolean) {
        if (!isSuccessfulRegister) {
            view?.let {
                showShackError(it, duplicateEmailErrorText)
            }
            return
        }

        val activity = requireActivity()
        activity.startActivityFromFragment(this, Intent(activity, MainActivity::class.java), 0)
        activity.finish()
    }

    private fun showShackError(view: View, text: String) {
        val snackView = layoutInflater.inflate(R.layout.sign_error_snack, null)
        showSnack(view, snackView, text)
    }

    companion object {
        private const val emailPattern =
            "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\$"

        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}
