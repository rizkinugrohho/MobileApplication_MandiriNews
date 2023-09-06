package com.mandiri.mandirinewss.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mandiri.mandirinewss.fragments.BusinessFragment
import com.mandiri.mandirinewss.fragments.EntertainmentFragment
import com.mandiri.mandirinewss.fragments.FinanceFragment
import com.mandiri.mandirinewss.fragments.HomeFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        HomeFragment(),
        FinanceFragment(),
        EntertainmentFragment(),
        BusinessFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}