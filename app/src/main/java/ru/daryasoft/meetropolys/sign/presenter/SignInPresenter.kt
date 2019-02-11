package ru.daryasoft.meetropolys.sign.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.daryasoft.meetropolys.account.AccountDao
import ru.daryasoft.meetropolys.account.AccountRepository
import ru.daryasoft.meetropolys.sign.view.ISignInView

class SignInPresenter(private val view: ISignInView) : ISignInPresenter {

    private var accountService = AccountRepository(AccountDao())

    override fun logIn(email: String, password: String) {
        GlobalScope.launch {
            val isLoggedIn = accountService.logIn(email, password)
            GlobalScope.launch(Dispatchers.Main) {
                view.onLogIn(isLoggedIn)
            }
        }
    }
}