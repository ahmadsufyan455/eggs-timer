package com.fynzero.eggstimer.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fynzero.eggstimer.R
import com.fynzero.eggstimer.ui.HardFragment
import com.fynzero.eggstimer.ui.MediumFragment
import com.fynzero.eggstimer.ui.SoftFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = SoftFragment()
            1 -> fragment = MediumFragment()
            2 -> fragment = HardFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.soft)
            1 -> context.resources.getString(R.string.medium)
            else -> context.resources.getString(R.string.hard)
        }
    }

    override fun getCount(): Int = 3
}