package com.mandiri.mandirinewss.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.mandiri.mandirinewss.adapter.PagerAdapter
import com.mandiri.mandirinewss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Declare
        val mtoolbar = binding.judul
        setSupportActionBar(mtoolbar)
        val viewpager = binding.fragmentContainer
        val tablayout = binding.tabLayout
        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewpager.adapter = pagerAdapter

//        Attach tablayout with viewpager
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            // Sesuaikan dengan judul tab yang sesuai dengan posisi
            when (position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Science"
                2 -> tab.text = "Entertainment"
                3 -> tab.text = "Business"
            }
        }.attach()

//        EventController
        viewpager.registerOnPageChangeCallback( object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when {
                    position in listOf<Int>(0,1,2,3) -> pagerAdapter.notifyDataSetChanged()
                }
            }
        })

//      Search Bar
        val searchActive = binding.searcH
        searchActive.setOnClickListener(){
            val intentSearch: Intent = Intent(this, SearchView::class.java)
            startActivity(intentSearch)
        }


    }

}