package ru.daryasoft.meetropolys.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.daryasoft.meetropolys.R
import ru.daryasoft.meetropolys.main.presenter.IMainPresenter
import ru.daryasoft.meetropolys.main.presenter.MainPresenter
import ru.daryasoft.meetropolys.main.view.IMainView
import ru.daryasoft.meetropolys.sign.SignActivity

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var presenter: IMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.checkIfLoggedIn()

        exit.setOnClickListener {
            presenter.logOut()
        }
    }

    override fun onLogOut() {
        startActivity(Intent(this, SignActivity::class.java))
        finish()
    }

    override fun onCheckingLogIn(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            showMainView()
        } else {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }

    private fun showMainView() {
        root_container.visibility = View.VISIBLE
    }
}
