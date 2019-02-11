package ru.daryasoft.meetropolys.account

import io.realm.Realm

class AccountRepository(private val accountDao: AccountDao) {
    fun isLoggedIn(): Boolean {
        Realm.getDefaultInstance().use { realm ->
            return accountDao.getActiveAccount(realm) != null
        }
    }

    fun logOut() {
        Realm.getDefaultInstance().use { realm ->
            accountDao.getActiveAccount(realm)?.let { accountDao.switchAccount(realm, it, false) }
        }
    }

    fun logIn(email: String, password: String): Boolean {
        Realm.getDefaultInstance().use { realm ->
            val account = accountDao.getAccount(realm, email, password) ?: return false
            accountDao.switchAccount(realm, account, true)
        }
        return true
    }

    fun register(email: String, password: String): Boolean {
        Realm.getDefaultInstance().use { realm ->
            if (accountDao.getAccount(realm, email) != null) {
                return false
            }
            accountDao.addAccount(realm, email, password)
        }
        return true
    }
}