package com.geektech.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private ViewPager pager;
    private PagerAdapter adapter;
    private MenuItem menuItem;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders
                .of(this)
                .get(MainViewModel.class);
        initView();
}

    private void initView() {
        pager = findViewById(R.id.viewPager);
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bn_1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.bn_2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.bn_3:
                        pager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }
}

