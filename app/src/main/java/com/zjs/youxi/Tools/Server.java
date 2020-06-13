package com.zjs.youxi.Tools;

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

/*****************************************************************************
 *                                                                            *
 *  @file  Server.java                                                       *
 *  @brief 此类为返回服务器结果                                              *
 *                                                                            *
 *  @author 张嘉皓                                                           *
 *  @email 877049745@qq.com                                                  *
 *  @version 1.0.0.1(版本号)                                                 *
 *                                                                            *
 *----------------------------------------------------------------------------*
 *  Remark         : 无                                                       *
 *----------------------------------------------------------------------------*
 *  Change History :                                                          *
 *  <Date>     | <Version> | <Author>       | <Description>                   *
 *----------------------------------------------------------------------------*
 *  2019/12/19 | 1.0.0.1   | 张嘉皓         | Create file                     *
 *----------------------------------------------------------------------------*
 *                                                                            *
 *****************************************************************************/
public class Server {
    public static final String IDENTIFY_TONGUE    =   "http://47.104.97.44:8080/file_simple";          // 识别舌像
    public static final String ADVICE             =   "http://47.104.97.44:8080/symptom?";             // 给出建议
    public static final String ADVICE_NEW         =   "http://47.104.97.44:8080/shexiang_diagnosis?";  // 给出建议新
    public static final String FORUM_HISTORY      =   "http://47.104.97.44:8080/post";                 // 历史帖子
    public static final String FORUM_HISTORY_NEW  =   "http://47.104.97.44:8080/findpostbyplate";      // 论坛新
    public static final String FLOOR              =   "http://47.104.97.44:8080/opinion?post_id=";     // 楼层信息
    public static final String FLOOR_NEW          =   "http://47.104.97.44:8080/findop";               // 楼层信息新
    public static final String FLOOR_REPLY        =   "http://47.104.97.44:8080/opinion";              // 回复
    public static final String USER_INFO          =   "http://47.104.97.44:8080/user_info";            // 用户信息
    public static final String ISLOOK             =   "http://47.104.97.44:8080/findpost";             // 增加浏览
    public static final String POST_PICTURE       =   "http://47.104.97.44:8080/post_pic";             // 帖子插图
    public static final String USER_PICTURE       =   "http://47.104.97.44:8080/download";             // 帖子插图
    public static final String FAIL               =   "error";                                         // 请求失败
    public static final String ALL_QUIZ           =   "http://47.104.97.44:8080/quiz";                 // 所有问答卷
    public static final String AQUIZ              =   "http://47.104.97.44:8080/aquiz";                // 请求一个问卷的所有内容
    public static final String QUIZ_IMAGE         =   "http://47.104.97.44:8080/quiz_pic";             // 问卷图片
    public static final String QUIZ_RESULT        =   "http://47.104.97.44:8080/quiz_result";          // 问卷结果



    /**
     * ***********************************************************************
     *
     * @brief       得到服务器结果
     * @parameter
     *
     *               urlPath         url地址
     *               RequestMethod   请求类型
     *
     * @version     v1.0.0
     * @author      张嘉皓
     * @date        2019/12/19
     * ----------------------------------------------------------------------
     * @修改时间:
     * @修改人+联系方式:
     * @说明(你为什么修改):
     * ***********************************************************************
     */
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

    /**
     * ***********************************************************************
     *
     * @brief       发送body到服务器
     * @parameter
     *
     *               urlPath         url地址
     *               params          body
     *               Method          请求类型
     *
     * @version     v1.0.0
     * @author      张嘉皓
     * @date        2019/12/30
     * ----------------------------------------------------------------------
     * @修改时间:
     * @修改人+联系方式:
     * @说明(你为什么修改):
     * ***********************************************************************
     */
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
