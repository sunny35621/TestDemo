package com.example.administrator.testapplication.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class Test {
    public String explain;
    public String question;
    public String a,b,c,d;
    public String url;
    public String daan;
    public int flag;

    public Test(String explain,String question, String a, String b, String c, String d,String url,String daan,int flag) {
        this.explain=explain;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.url=url;
        this.daan=daan;
        this.flag=flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
