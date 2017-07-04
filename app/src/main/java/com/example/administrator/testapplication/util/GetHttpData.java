package com.example.administrator.testapplication.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GetHttpData{
    static Handler handler =new Handler(Looper.getMainLooper());
    public static void getHttpData(final String        path, final IGetData iGetData){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
//                    int responseCode=connection.getResponseCode();



//                    if(responseCode==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        int len=0;
                        byte[] bytes=new byte[1024];
                        while((len=inputStream.read(bytes))!=-1){
                            baos.write(bytes,0,len);
                        }
                        final byte[] data=baos.toByteArray();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iGetData.onSuccess(data);
                            }
                        });
//                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iGetData.onError("对不起，网络错误！");
                        }
                    });
                }
            }
        }).start();
    }

    public interface IGetData{
        void onSuccess(byte[] bytes);
        void onError(String err);
    }
}
