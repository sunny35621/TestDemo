package com.example.administrator.testapplication.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/26.
 */

public class Answer {
    public Map<String,Set<String>> map=new HashMap<>();


    public Answer() {
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        Set<String> set3=new HashSet<>();
        Set<String> set4=new HashSet<>();
        Set<String> set7=new HashSet<>();
        Set<String> set8=new HashSet<>();
        Set<String> set9=new HashSet<>();
        Set<String> set10=new HashSet<>();
        Set<String> set11=new HashSet<>();
        Set<String> set12=new HashSet<>();
        Set<String> set13=new HashSet<>();
        Set<String> set14=new HashSet<>();
        Set<String> set15=new HashSet<>();
        Set<String> set16=new HashSet<>();
        Set<String> set17=new HashSet<>();

        set1.add("1");
        map.put(1+"",set1);


        set2.add(2+"");
        map.put(2+"",set2);


        set3.add(3+"");
        map.put(3+"",set3);


        set4.add(4+"");
        map.put(4+"",set4);


        set7.add(1+"");
        set7.add(2+"");
        map.put(7+"",set7);


        set8.add(1+"");
        set8.add(3+"");
        map.put(8+"",set8);


        set9.add(1+"");
        set9.add(4+"");
        map.put(9+"",set9);


        set10.add(2+"");
        set10.add(3+"");
        map.put(10+"",set10);


        set11.add(2+"");
        set11.add(4+"");
        map.put(11+"",set11);

        set12.add(3+"");
        set12.add(4+"");
        map.put(12+"",set12);

        set13.add(1+"");
        set13.add(2+"");
        set13.add(3+"");
        map.put(13+"",set13);

        set14.add(1+"");
        set14.add(2+"");
        set14.add(4+"");
        map.put(14+"",set14);

        set14.add(1+"");
        set14.add(3+"");
        set15.add(4+"");
        map.put(15+"",set15);

        set16.add(2+"");
        set16.add(3+"");
        set16.add(4+"");
        map.put(16+"",set16);

        set17.add(1+"");
        set17.add(2+"");
        set17.add(3+"");
        set17.add(4+"");
        map.put(17+"",set17);
    }
}
