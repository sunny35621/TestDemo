package com.example.administrator.testapplication.util;

import android.util.Log;

import com.example.administrator.testapplication.bean.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/26.
 */

public class Judge {

    public  static  int doJudge(Test test,Set<String> ansSet){
        int flag=0;

        Log.d("set", "doJudge: "+new Answer().map.get(test.daan)+"------"+ansSet);
    if(new Answer().map.get(test.daan).equals(ansSet)){
        flag=1;
    }else{
        flag=-1;
    }



        return flag;
    }
}
