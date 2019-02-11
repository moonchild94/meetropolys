package ru.daryasoft.meetropolys.account

import io.realm.Realm
import java.util.*

class AccountDao {

    fun getActiveAccount(realm: Realm): Account? {
        return getAccount(realm, mapOf(AccountField.IS_ACTIVE to true))
    }

    fun getAccount(realm: Realm, email: String, password: String): Account? {
        return getAccount(realm, mapOf(AccountField.EMAIL to email, AccountField.PASSWORD to password))
    }

    fun getAccount(realm: Realm, email: String): Account? {
        return getAccount(realm, mapOf(AccountField.EMAIL to email))
    }

    fun switchAccount(realm: Realm, account: Account, isActive: Boolean) {
        realm.executeTransaction {
            account.isActive = isActive
        }
    }

    fun addAccount(realm: Realm, email: String, password: String) {
        val account = Account(UUID.randomUUID().toString(), email, password, true)
        realm.executeTransaction {
            realm.copyToRealm(account)
        }
    }

    private fun getAccount(realm: Realm, fieldToValue: Map<AccountField, Any>): Account? {
        var account: Account? = null

        realm.executeTransaction {
            var query = realm.where(Account::class.java)
            for ((field, value) in fieldToValue) {
                query = when (field) {
                    AccountField.IS_ACTIVE -> query.equalTo(field.title, value as Boolean)
                    else -> query.equalTo(field.title, value as String)
                }

            }
            account = query.findFirst()
        }

        return account
    }

    private companion object {
        const val isActiveFieldName = "isActive"
        const val emailFieldName = "email"
        const val passwordFieldName = "password"
    }

    private enum class AccountField(internal val title: String) {
        IS_ACTIVE(isActiveFieldName),
        EMAIL(emailFieldName),
        PASSWORD(passwordFieldName)
    }
}