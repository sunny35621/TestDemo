package com.example.administrator.testapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.testapplication.Ke1FromToActivity;
import com.example.administrator.testapplication.Ke1ModelActivity;
import com.example.administrator.testapplication.MyErroActivity;
import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.RecordActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Kemu1Fragment extends Fragment {
    private ViewPager lunboViewPager;
    private List<ImageView> imageViewList=new ArrayList<>();
    private ImageView imageView;
    private boolean isLooper;
    private Handler handler=new Handler(Looper.getMainLooper());;
    private Timer mTimer;
    private WebView myWebView;
    private RadioGroup mGroup;
    public Kemu1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kemu1, container, false);
        lunboViewPager= (ViewPager) view.findViewById(R.id.lunboViewPager);
        mGroup= (RadioGroup) view.findViewById(R.id.group);
        myWebView= (WebView) view.findViewById(R.id.myWebView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://mnks.jxedt.com/");
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button1:
                        Intent intent1=new Intent(getContext(), Ke1FromToActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.button2:
//                        Toast.makeText(getContext(), ""+1, Toast.LENGTH_SHORT).show();

                        Intent intent2=new Intent(getContext(), Ke1ModelActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.button3:
                        Intent intent3=new Intent(getContext(), MyErroActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.button4:
                        Intent intent4=new Intent(getContext(), RecordActivity.class);
                        startActivity(intent4);
                        break;

                }
            }
        });
        addData();
        lunboViewPager.setOffscreenPageLimit(1);
        lunboViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                container.removeView(imageViewList.get(position));
                if(container!=null){
                    container.removeAllViews();
                }
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewList.get(position));
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                while (isLooper) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int position = lunboViewPager.getCurrentItem();
                            if (position == imageViewList.size()-1) {
                                lunboViewPager.setCurrentItem(0);
                            } else {
                                lunboViewPager.setCurrentItem(++position);
                            }
                        }
                    });
                }
//                mTimer = new Timer();
//                mTimer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                int currentItem = lunboViewPager.getCurrentItem();
//                                if (currentItem == lunboViewPager.getAdapter().getCount() - 1) {
//                                    currentItem = 0;
//                                } else {
//                                    currentItem++;
//                                }
//                                lunboViewPager.setCurrentItem(currentItem);
//                            }
//                        });
//                    }
//                }, 3000, 3000);
            }
        }).start();//开启线程
        return view;
    }

    private void addData() {
        ImageView imageView=new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.jiaxiao);
        imageViewList.add(imageView);


    }


}
