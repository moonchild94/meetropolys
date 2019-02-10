package ru.daryasoft.meetropolys

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import ru.daryasoft.meetropolys.model.Account
import ru.daryasoft.meetropolys.sign.SignActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        exit.setOnClickListener {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    val account = realm
                        .where(Account::class.java)
                        .equalTo("isActive", true)
                        .findFirst()
                    account?.let {
                        it.isActive = false
                    }
                }
            }

            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        checkAutorization()
    }

    private fun checkAutorization() {
        var isAnyUserAuthorized = false

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                val account = realm
                    .where(Account::class.java)
                    .equalTo("isActive", true)
                    .findFirst()
                account?.let {
                    isAnyUserAuthorized = true
                }
            }
        }

        if (!isAnyUserAuthorized) {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }
}
