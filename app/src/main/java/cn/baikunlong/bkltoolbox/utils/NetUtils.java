package cn.baikunlong.bkltoolbox.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtils {

    private static OkHttpClient client = new OkHttpClient();

    /**
     * 使用post方式请求
     *
     * @param body 传递RequestBody对象，或者传递json格式参数
     * @return 返回json对象
     */
    public static JSONObject post(Object body) {
        if (body == null) {
            body = RequestBody.create(MediaType.parse("application/json"), "");
        } else if (body instanceof String) {
            body = RequestBody.create(MediaType.parse("application/json"), (String) body);
        }
        Request request = new Request.Builder()
                .post((RequestBody) body)
                .url("https://cloud.tencent.com/act/common/ajax/domain")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = Objects.requireNonNull(response.body()).string();
            System.out.println("json = " + json);
//            String s = json + "\n";
            response.close();
            return new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    /**
//     * 使用post方式请求
//     * @param body 传递RequestBody对象，或者传递json格式参数
//     * @return 返回json对象
//     */
//    static JSONObject post(Object body,BufferedOutputStream outputStream) {
//        if (body == null) {
//            body = RequestBody.create(MediaType.parse("application/json"), "");
//        } else if (body instanceof String) {
//            body = RequestBody.create(MediaType.parse("application/json"), (String)body);
//        }
//        Request request = new Request.Builder()
//                .post((RequestBody) body)
//                .url("https://cloud.tencent.com/act/common/ajax/domain")
//                .build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            String json = Objects.requireNonNull(response.body()).string();
////            System.out.println("json = " + json);
//            String s = json + "\n";
//            outputStream.write(s.getBytes());
//
//            return new JSONObject(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(response!=null){
//                response.close();
//            }
//        }
//        return null;
//    }

}
