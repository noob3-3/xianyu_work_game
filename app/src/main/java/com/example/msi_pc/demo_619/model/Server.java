package com.example.msi_pc.demo_619.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class Server {

    public static final String FAIL               =   "error";                                         // 请求失败
    public static String getServerResult(String urlPath,String RequestMethod)
    {
        try{
            HttpURLConnection conn;
            URL url = new URL(null,urlPath);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(RequestMethod);
            int code = conn.getResponseCode();
            switch (code)
            {
                // 请求成功
                case 200:
                    InputStream inputstream = conn.getInputStream();
                    Scanner sc  = new Scanner(inputstream,"UTF-8");
                    String text = "";
                    while(sc.hasNext()){
                        text += sc.nextLine();
                    }
                    return text;
                default:
                    System.out.println( "嗯嗯？"+code );
                    return FAIL;
            }
        }catch (Exception e)
        {
            System.out.println( "哦豁"+e.getMessage() );
        }
        return FAIL;
    }

    public static String SendBody2Server(String urlPath, Map<String, String> params, String Method){
        try{
            String BOUNDARY = java.util.UUID.randomUUID().toString();
            String PREFIX = "--";
            String LINEND = "\r\n";
            String MULTIPART_FROM_DATA = "multipart/form-data";
            String CHARSET = "UTF-8";
            URL uri = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setReadTimeout(30 * 1000); // 缓存的最长时间
            conn.setDoInput(true);// 允许输入
            conn.setDoOutput(true);// 允许输出
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod(Method);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();
            if (params!=null) {
                // 首先组拼文本类型的参数
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINEND);
                    sb.append("Content-Disposition: form-data; name=\""
                            + entry.getKey() + "\"" + LINEND);
                    sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
                    sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                    sb.append(LINEND);
                    sb.append(entry.getValue());
                    sb.append(LINEND);
                }

            }

            DataOutputStream outStream = new DataOutputStream(
                    conn.getOutputStream());
            outStream.write(sb.toString().getBytes());

            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();

            // 得到响应码
            int res = conn.getResponseCode();
            InputStream in = conn.getInputStream();
            if (res == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    buffer.append(line);
                }
                return buffer.toString();
            }
            outStream.close();
            conn.disconnect();
            return in.toString();
        }catch (Exception e)
        {
            System.out.println("发送Body出现异常:"+e.getMessage());
            e.printStackTrace();
        }
        return FAIL;
    }

    public static Bitmap getServerPicture(String actionUrl, Map<String, String> params, String Method) throws IOException {

        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--";
        String LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(30 * 1000); // 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false); // 不允许使用缓存
        conn.setRequestMethod(Method);
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
        StringBuilder sb = new StringBuilder();
        if (params!=null) {
            // 首先组拼文本类型的参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\""
                        + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

        }

        DataOutputStream outStream = new DataOutputStream(
                conn.getOutputStream());
        outStream.write(sb.toString().getBytes());

        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();

        // 得到响应码
        int res = conn.getResponseCode();
        InputStream in = conn.getInputStream();
        if (res == 200) {
            return BitmapFactory.decodeStream(in);
        }
        outStream.close();
        conn.disconnect();
        return null;
    }
}
