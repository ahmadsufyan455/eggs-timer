package com.fynzero.eggstimer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fynzero.eggstimer.R
import com.fynzero.eggstimer.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup view pager
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}