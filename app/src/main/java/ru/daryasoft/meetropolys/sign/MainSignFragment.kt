package ru.daryasoft.meetropolys.sign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_sign.*
import ru.daryasoft.meetropolys.R

class MainSignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sign_pager.adapter =
            SignFragmentPagerAdapter(fragmentManager, requireContext())
        sign_tabs.setupWithViewPager(sign_pager)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainSignFragment()
    }
}
