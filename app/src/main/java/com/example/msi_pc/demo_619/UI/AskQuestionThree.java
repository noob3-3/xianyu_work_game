package com.example.msi_pc.demo_619.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.msi_pc.demo_619.R;
import com.example.msi_pc.demo_619.Tools.UIOperation;
import com.example.msi_pc.demo_619.model.Joke_Data;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AskQuestionThree extends AppCompatActivity {
    public static final int TOTAL_QUESTION = 10;  // 题目数量
    public TextView Content;   // 问题
    public Button   Next;      // 下一个
    public List<Joke_Data.ResultBean.ListBean> data = new ArrayList<>();

    public int CurrentNumber = 0;

    public Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            Content.setText( data.get( CurrentNumber ).getContent()+"" );
        }
    };

    public Handler ShowUI = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            Content.setText( msg.obj+"" );
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        UIOperation.SetFullScreen(this);

        // 请求网络资源
        new Thread( new Runnable() {
            @Override
            public void run() {
                getData(1);
                handler.sendMessage( new Message() );
                getData(9);
            }
        } ).start();



        initView();
        initOnClickListener();

    }

    private void initOnClickListener() {

        Next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentNumber == TOTAL_QUESTION )
                {
                    finish();
                }else {
                    if(CurrentNumber > data.size())
                    {
                        Content.setText( "加载中..." );
                        WaitLoading(CurrentNumber);
                    }else {
                        Content.setText( data.get( CurrentNumber ).getContent()+"" );
                        CurrentNumber++;
                    }
                }
            }

        } );

    }

    private void initView() {
        Content = findViewById(R.id.content);
        Next    = findViewById(R.id.next);

    }


    public void WaitLoading(final int number)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (data.size() < number) {}
                Message msg = new Message();
                msg.obj = data.get(number).getContent();
                ShowUI.sendMessage(msg);
                CurrentNumber++;
            }
        }).start();
    }

    public List<Joke_Data.ResultBean.ListBean> getData(int number) {

        List<Joke_Data.ResultBean.ListBean> b = new ArrayList<>();
        for(int i=0;i<number;i++)
        {
            data.add(Joke_Data.Get_Joke_Data().getResult().getList().get( 0 ));
        }
        return b;
    }


}
