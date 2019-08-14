package cn.baikunlong.bkltoolbox.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.FormBody;

import static org.junit.Assert.*;

public class NetUtilsTest {

    private int max = 500;
    private int initValue = 0;
    private final AtomicInteger startCount = new AtomicInteger(initValue);
    private final AtomicInteger stopCount = new AtomicInteger(0);
    private final AtomicInteger currentCount = new AtomicInteger(0);

    private boolean flag = true;
//    private String data = "{\"domainName\":\"imbkl\",\"tldList\":[\".cn\"]}";

    private BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("F:\\MyAndroidSrc\\MyProject\\BklToolBox\\app\\src\\test\\java\\cn\\baikunlong\\bkltoolbox\\utils\\log.txt"));

    public NetUtilsTest() throws FileNotFoundException {
    }


//    @Test
//    public void post() throws Exception {
//        currentCount.set(0);
//        for (int i = 0; i < max; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    startCount.addAndGet(1);
////                    System.out.println(startCount + "开始");
//                    String data = "{\"domainName\":\"" + startCount + "\",\"tldList\":[\".cn\"]}";
//                    FormBody body = new FormBody.Builder().add("action", "searchDomain").add("data", data).build();
//                    NetUtils.post(body, outputStream);
//                    //总数加1
//                    stopCount.addAndGet(1);
//                    //如果循环结束
//                    if (currentCount.addAndGet(1) >= max) {
//                        //如果总数达到目的
//                        if(stopCount.get()>=10000 ){
//                            flag = false;
//                            System.out.println("全部结束");
//                        }else {
//                            //继续递归
//                            System.out.println("结束"+stopCount.get()+"个");
//                            startCount.addAndGet(max);
//                            try {
//                                post();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                }
//            }).start();
//        }
//        outputStream.flush();
//        while (flag) {
//            Thread.sleep(1000);
//        }
//        outputStream.close();
//    }
}