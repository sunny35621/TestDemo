package com.example.administrator.testapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.testapplication.fragment.Kemu1Fragment;
import com.example.administrator.testapplication.fragment.Kemu4Fragment;
import com.example.administrator.testapplication.fragment.NewsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager tViewPager;
    private NewsFragment newsFragment;
    private Kemu1Fragment kemu1Fragment=new Kemu1Fragment();
    private Kemu4Fragment kemu4Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        switch (intent.getIntExtra("mark",0)){
            case 1:
                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.tViewPager,kemu1Fragment);
                ft.commit();
                break;
        }
        tViewPager= (ViewPager) findViewById(R.id.tViewPager);
        FragmentPagerItemAdapter adapter=new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)

                .add("科目一",Kemu1Fragment.class)
                .add("科目四",Kemu4Fragment.class)
                .create()
                );
        tViewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab= (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(tViewPager);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
