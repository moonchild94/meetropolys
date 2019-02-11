package ru.daryasoft.meetropolys.sign.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.daryasoft.meetropolys.account.AccountDao
import ru.daryasoft.meetropolys.account.AccountRepository
import ru.daryasoft.meetropolys.sign.view.ISignUpView

class SignUpPresenter(private val view: ISignUpView) : ISignUpPresenter {

    private var accountService = AccountRepository(AccountDao())

    override fun register(email: String, password: String) {
        GlobalScope.launch {
            val isRegistrationSuccessful = accountService.register(email, password)
            GlobalScope.launch(Dispatchers.Main) {
                view.onRegister(isRegistrationSuccessful)
            }
        }
    }
}