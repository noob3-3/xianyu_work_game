package com.zjs.youxi;

import com.google.gson.Gson;
import com.zjs.youxi.Tools.Riddle_Data;
import com.zjs.youxi.Tools.Server;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void getData() {
        String key = "8789d7324e904d78";
        //笑话
        String url1 = "https://api.jisuapi.com/xiaohua/text?pagenum=1&pagesize=20&sort=addtime&appkey="+key;
        //脑筋急转弯
        String url2 = "https://api.jisuapi.com/jzw/search?appkey="+key+"&keyword=女人&pagenum=1&pagesize=5";
        //
        String url3 = "https://api.jisuapi.com/miyu/search?appkey="+key;
        String data = Server.getServerResult( url3, "GET" );
        Gson gson = new Gson();
        System.out.println( data );
        Riddle_Data z = gson.fromJson(data, Riddle_Data.class );
        System.out.println( z.getMsg() );
    }


}