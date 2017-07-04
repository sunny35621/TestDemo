package com.example.administrator.testapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testapplication.bean.Test;
import com.example.administrator.testapplication.fragment.Kemu1Fragment;
import com.example.administrator.testapplication.util.Answer;
import com.example.administrator.testapplication.util.GetHttpData;
import com.example.administrator.testapplication.util.Judge;
import com.example.administrator.testapplication.util.SDUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ke1FromToActivity extends AppCompatActivity {
    private View view1, view2;
    private TextView textView1, textView2, questionTV, explainTV,zongjiTV,cuowuTV,zhengqueTV;
    private Button button1, button2;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private List<Test> testList = new ArrayList<>();
    private Set<String> ansSet=new HashSet<>();
    private ImageView imageView,fanhui,shezhi,night;
    private Bitmap bm;
    private LinearLayout layout;
    private List<Test> tests=new ArrayList<>(),tests1=new ArrayList<>(),tests2=new ArrayList<>();
    private static final String NUM1="1",NUM2="2",NUM3="3",NUM4="4";
    private int cuowushu=0,zhengqueshu=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke1_from_to);
        shezhi= (ImageView) findViewById(R.id.shezhi);
        fanhui= (ImageView) findViewById(R.id.fanhui);
        night= (ImageView) findViewById(R.id.night);
        textView1 = (TextView) findViewById(R.id.datimoshi);
        textView2 = (TextView) findViewById(R.id.beitimoshi);
        layout= (LinearLayout) findViewById(R.id.layout);
        button1= (Button) findViewById(R.id.shoucang);
        button2= (Button) findViewById(R.id.quxiaoshoucang);
        view1 = findViewById(R.id.datiLine);
        view2 = findViewById(R.id.beitiLine);
        checkBox1 = (CheckBox) findViewById(R.id.check1);
        checkBox2 = (CheckBox) findViewById(R.id.check2);
        checkBox3 = (CheckBox) findViewById(R.id.check3);
        checkBox4 = (CheckBox) findViewById(R.id.check4);
        imageView = (ImageView) findViewById(R.id.iv);
        zongjiTV= (TextView) findViewById(R.id.zongji);
        cuowuTV= (TextView) findViewById(R.id.cuowushu);
        zhengqueTV= (TextView) findViewById(R.id.zhengqueshu);
        explainTV = (TextView) findViewById(R.id.explain);
        questionTV = (TextView) findViewById(R.id.questionTV);
        explainTV.setVisibility(View.GONE);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                toDati();
                explainTV.setVisibility(View.GONE);

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                toRead();
                explainTV.setVisibility(View.VISIBLE);
            }
        });
        String path = "http://api.avatardata.cn/Jztk/Query?key=f035cd4172b24f9cab3916ca8d99c70c&subject=4&model=c1";

        GetHttpData.getHttpData(path, new GetHttpData.IGetData() {
            @Override
            public void onSuccess(byte[] bytes) {
                String s = new String(bytes);
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject object1 = result.getJSONObject(i);
                        String url = object1.getString("url");
                        String question = object1.getString("question");
                        String a = object1.getString("item1");
                        String b = object1.getString("item2");
                        String c = object1.getString("item3");
                        String d = object1.getString("item4");
                        String daan=object1.getString("answer");
                        String explain = object1.getString("explains");
                        if (url.contains(".jpg")) {
                            testList.add(new Test(explain, question, a, b, c, d, url,daan,0));
                        }
                    }
                    addQuestion();
                    zongjiTV.setText(1+i+"/"+testList.size());
//                    Toast.makeText(Ke1FromToActivity.this, "1"+testList.size(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String err) {
                Toast.makeText(Ke1FromToActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(this, "0"+testList.size(), Toast.LENGTH_SHORT).show();
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ansSet.add(NUM1);
                    if(checkBox3.getVisibility()==View.GONE){
                        checkBox2.setChecked(false);
                    }
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ansSet.add(NUM2);
                    if(checkBox3.getVisibility()==View.GONE){
                        checkBox1.setChecked(false);
                    }
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ansSet.add(NUM3);
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ansSet.add(NUM4);
                }
            }
        });

    }
    //--------------------------------------------------------------------------------------------
    private void toDati() {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        ansSet.removeAll(ansSet);
    }
    private void toRead() {
        Set<String> strings = new Answer().map.get(testList.get(i).daan);
        for (String str:strings) {
            for (int j = 1; j < 4; j++) {
                switch (str){
                    case ""+1:
                        checkBox1.setChecked(true);
                        break;
                    case ""+2:
                        checkBox2.setChecked(true);
                        break;
                    case ""+3:
                        checkBox3.setChecked(true);
                        break;
                    case ""+4:
                        checkBox4.setChecked(true);
                        break;
                }
            }
        }
        ansSet.removeAll(ansSet);
    }
    private int i = 0;

    private void addQuestion() {
        questionTV.setText((i + 1) + "." + testList.get(i).question);

        explainTV.setText("解析：" + testList.get(i).explain);
        if (testList.get(i).c.equals("")) {
            checkBox1.setText("正确");
            checkBox2.setText("错误");
            checkBox3.setVisibility(View.GONE);
            checkBox4.setVisibility(View.GONE);
        } else {
            checkBox1.setText("A." + testList.get(i).a);
            checkBox2.setText("B." + testList.get(i).b);
            checkBox3.setText("C." + testList.get(i).c);
            checkBox4.setText("D." + testList.get(i).d);
            checkBox3.setVisibility(View.VISIBLE);
            checkBox4.setVisibility(View.VISIBLE);
        }
        if (testList.get(i).url == null || testList.get(i).url.equals("")) {
            bm = null;
        } else {
            GetHttpData.getHttpData(testList.get(i).url, new GetHttpData.IGetData() {
                @Override
                public void onSuccess(byte[] bytes) {

                    bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bm);

                }

                @Override
                public void onError(String err) {
                    Toast.makeText(Ke1FromToActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void initia() {
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);

    }


    public void nextOne(View view) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        Test test = testList.get(i);
        test.setFlag(Judge.doJudge(testList.get(i),ansSet));
        if(view1.getVisibility()==View.VISIBLE) {
            tests.add(test);
            switch (test.flag) {
                case 1:
                    zhengqueTV.setText(++zhengqueshu + "");
                    break;
                case -1:
                    cuowuTV.setText(++cuowushu + "");
                    tests2.add(testList.get(i));
                    break;
            }
        }
        i++;
        switch (view1.getVisibility()){
            case View.GONE:
                toRead();
                break;
            case View.VISIBLE:
                toDati();
                break;
        }
        addQuestion();
        zongjiTV.setText(1+i+"/"+testList.size());
        ansSet.removeAll(ansSet);
        initia();
    }

    public void lastOne(View view) {

        if (i > 0) {
            i--;
            addQuestion();

        }
        switch (view1.getVisibility()){
            case View.GONE:
                toRead();
                break;
            case View.VISIBLE:
                toDati();
                break;
        }
        zongjiTV.setText(1+i+"/"+testList.size());
    }

    public void goBack(View view) {
//        finish();
        SDUtil.saveDataToSDcard("record"+System.currentTimeMillis()+"",tests);
        SDUtil.saveDataToSDcard("saved"+System.currentTimeMillis()+"",tests1);
        SDUtil.saveDataToSDcard("erro"+System.currentTimeMillis()+"",tests2);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("mark", 1);
        startActivity(intent);
    }


    public void shoucang(View view) {
        tests1.add(testList.get(i));

        button2.setVisibility(View.VISIBLE);
        button1.setVisibility(View.GONE);
    }

    public void quxiaoshoucang(View view) {
        tests1.remove(testList.get(i));
        button2.setVisibility(View.GONE);
        button1.setVisibility(View.VISIBLE);
    }

    public void shezhi(View view) {
        button1.setBackgroundColor(Color.parseColor("#1b1b1b"));
        button2.setBackgroundColor(Color.parseColor("#1b1b1b"));
        shezhi.setImageResource(R.mipmap.night);
        fanhui.setImageResource(R.mipmap.fanhuin);
        textView1.setTextColor(Color.parseColor("#ffffff"));
        textView2.setTextColor(Color.parseColor("#ffffff"));
        layout.setBackgroundColor(Color.parseColor("#000000"));
        explainTV.setTextColor(Color.parseColor("#ffffff"));
        questionTV.setTextColor(Color.parseColor("#ffffff"));
        checkBox1.setTextColor(Color.parseColor("#ffffff"));
        checkBox2.setTextColor(Color.parseColor("#ffffff"));
        checkBox3.setTextColor(Color.parseColor("#ffffff"));
        checkBox4.setTextColor(Color.parseColor("#ffffff"));
        night.setVisibility(View.VISIBLE);
        shezhi.setVisibility(View.GONE);
           }


    public void night(View view) {
//        button1.setBackgroundColor(Color.parseColor("#cde4df"));
//        button2.setBackgroundColor(Color.parseColor("#cde4df"));
//        shezhi.setImageResource(R.mipmap.night);
//        fanhui.setImageResource(R.mipmap.fanhui);
//        textView1.setTextColor(Color.parseColor("#151818"));
//        textView2.setTextColor(Color.parseColor("#151818"));
//        layout.setBackgroundColor(Color.parseColor("#cde4df"));
//        explainTV.setTextColor(Color.parseColor("#151818"));
//        questionTV.setTextColor(Color.parseColor("#151818"));
//        checkBox1.setTextColor(Color.parseColor("#151818"));
//        checkBox2.setTextColor(Color.parseColor("#151818"));
//        checkBox3.setTextColor(Color.parseColor("#151818"));
//        checkBox4.setTextColor(Color.parseColor("#151818"));
//        night.setVisibility(View.GONE);
//        shezhi.setVisibility(View.VISIBLE);
    }
}
