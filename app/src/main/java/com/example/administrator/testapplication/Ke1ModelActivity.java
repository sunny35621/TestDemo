package com.example.administrator.testapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testapplication.bean.Test;
import com.example.administrator.testapplication.util.GetHttpData;
import com.example.administrator.testapplication.util.Judge;
import com.example.administrator.testapplication.util.SDUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ke1ModelActivity extends AppCompatActivity {
    private TextView timeTV,questionTV, explainTV,zongjiTV,cuowuTV,zhengqueTV;
    private ImageView imageView;
    private Bitmap bm;
    private List<Test> testList=new ArrayList<>(),tests=new ArrayList<>(),tests1=new ArrayList<>(),tests2=new ArrayList<>();
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private static final String NUM1="1",NUM2="2",NUM3="3",NUM4="4";
    private Set<String> ansSet=new HashSet<>();
    private int cuowushu=0,zhengqueshu=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke1_model);

        timeTV= (TextView) findViewById(R.id.shijian);
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
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ansSet.add(NUM1);
                    if(checkBox3.getVisibility()== View.GONE){
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
        String path = "http://api.avatardata.cn/Jztk/Query?key=f035cd4172b24f9cab3916ca8d99c70c&subject=4&model=c1";

        new AsyncTask<String, Void,String>(){

            @Override
            protected String doInBackground(String... params) {
                try {
                    InputStream inputStream = new URL(params[0]).openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String lint,result = "";
                    while ((lint = reader.readLine()) != null) {
                        result+=lint;
                    }
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                jiexiJson(s);

                Random random=new Random();

                do {
                    int r=random.nextInt(tests2.size());
                    Test test=tests2.get(r);
                    if(!testList.contains(test)){
                        testList.add(test);
                    }

                }while(testList.size()<10);
                addQuestion();
//                Log.d("TAG", s);
            }
        }.execute(path);
    }
//-----------------------------------------------------------------------------------------
private int i = 0;

    private void addQuestion() {


        questionTV.setText((i + 1) + "." + testList.get(i).question);

        explainTV.setText("解析：" + testList.get(i).explain);
        explainTV.setVisibility(View.GONE);
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
                    Toast.makeText(Ke1ModelActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void nextOne(View view) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        Test test = testList.get(i);
        test.setFlag(Judge.doJudge(testList.get(i),ansSet));

            tests.add(test);
            switch (test.flag) {
                case 1:
                    zhengqueTV.setText(++zhengqueshu + "");
                    break;
                case -1:
                    cuowuTV.setText(++cuowushu + "");
                    tests1.add(testList.get(i));
                    break;
            }
        i++;
        addQuestion();
        zongjiTV.setText(1+i+"/"+testList.size());
        ansSet.removeAll(ansSet);
    }

    public void lastOne(View view) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        if (i > 0) {
            i--;
            addQuestion();
        }
        zongjiTV.setText(1+i+"/"+testList.size());
    }

    public void goBack(View view) {
//        finish();
        SDUtil.saveDataToSDcard("record"+System.currentTimeMillis()+"",tests);
        SDUtil.saveDataToSDcard("erro"+System.currentTimeMillis()+"",tests1);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("mark", 1);
        startActivity(intent);
    }

    private void jiexiJson(String s) {
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
                    tests2.add(new Test(explain, question, a, b, c, d, url,daan,0));
                }
            }

            zongjiTV.setText(1+i+"/"+testList.size());
            Toast.makeText(Ke1ModelActivity.this, "1"+testList.size(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
