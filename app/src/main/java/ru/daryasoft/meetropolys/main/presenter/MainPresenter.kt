package ru.daryasoft.meetropolys.main.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.daryasoft.meetropolys.account.AccountDao
import ru.daryasoft.meetropolys.account.AccountRepository
import ru.daryasoft.meetropolys.main.view.IMainView

class MainPresenter(private val view: IMainView) : IMainPresenter {

    private var accountService = AccountRepository(AccountDao())

    override fun logOut() {
        GlobalScope.launch {
            accountService.logOut()
            GlobalScope.launch(Dispatchers.Main) {
                view.onLogOut()
            }
        }
    }

    override fun checkIfLoggedIn() {
        GlobalScope.launch {
            val isLoggedIn = accountService.isLoggedIn()
            GlobalScope.launch(Dispatchers.Main) {
                view.onCheckingLogIn(isLoggedIn)
            }
        }
    }
}