package com.geektech.quizapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.geektech.quizapp.R;
import com.geektech.quizapp.history.HistoryFragment;
import com.geektech.quizapp.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerAdapter adapter;
    private MenuItem menuItem;
    private BottomNavigationView navigationView;
    //    private Vector <Fragment> fragments;
    private MainFragment mainFragment;
    private HistoryFragment historyFragment;
    private SettingsFragment settingsFragment;
    private TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initView();
    }

    private void initView() {
        pager = findViewById(R.id.viewPager);
        tvTitle = findViewById(R.id.tv_title)
        ;
        adapter = new PagerAdapter(super.getSupportFragmentManager());
        mainFragment = new MainFragment();
        historyFragment = new HistoryFragment();
        settingsFragment = new SettingsFragment();
        adapter.addFragments(mainFragment);
        adapter.addFragments(historyFragment);
        adapter.addFragments(settingsFragment);

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
                return false;
            }
        });
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }

                navigationView.getMenu().getItem(position).setChecked(true);
                menuItem = navigationView.getMenu().getItem(position);
                if (position == 0) {
                    tvTitle.setText(R.string.quiz);

                } else if (position == 1) {
                    tvTitle.setText(R.string.history);
                } else {
                    tvTitle.setText(R.string.settings);
                }
            }
        });

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);

        context.startActivity(starter);
    }
}

