package ru.daryasoft.meetropolys.sign

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.daryasoft.meetropolys.R
import java.lang.IllegalArgumentException

class SignFragmentPagerAdapter(fm: FragmentManager?, private val context: Context) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf(
        R.string.sign_in_title,
        R.string.sign_up_title
    )

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SignInFragment.newInstance()
            1 -> SignUpFragment.newInstance()
            else -> throw IllegalArgumentException("Unexpected tab number!")
        }
    }

    override fun getCount() = tabTitles.size

    override fun getPageTitle(position: Int): CharSequence? = context.getString(tabTitles[position])
}