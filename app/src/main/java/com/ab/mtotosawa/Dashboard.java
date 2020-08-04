package com.ab.mtotosawa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ab.mtotosawa.controller.DevPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Dashboard extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_dashboard);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new DevPagerAdapter (getSupportFragmentManager()));

        TabLayout tabs = findViewById (R.id.tabLayoutId);
        tabs.setupWithViewPager(viewPager);
    }
}