package com.zjs.youxi.Tools;


import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
                    return FAIL;
            }
        }catch (Exception e)
        {
        }
        return FAIL;
    }
}
