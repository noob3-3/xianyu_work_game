package com.example.msi_pc.demo_619.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi_pc.demo_619.R;
import com.example.msi_pc.demo_619.Tools.UIOperation;
import com.example.msi_pc.demo_619.model.Brain_Teasers_Data;
import com.example.msi_pc.demo_619.model.Riddle_Data;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AskQuestionTwo extends AppCompatActivity {
    public static final int TOTAL_QUESTION = 10;  // 题目数量
    public TextView Question;   // 问题
    public TextView ShowCurrentQuestion;  //   当前题目
    public TextView ShowTotalQuestion;    //   总题目
    public EditText Answer;     // 答案
    public Button   MakeSure;   // 确定
    public List<Riddle_Data.ResultBean.ListBean> data;

    public int CurrentNumber = 0;   // 代表当前题目的位置
    public int CurrentScore  = 0;   // 当前分数

    public Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            if(data.size()>TOTAL_QUESTION)
            {
                ShowTotalQuestion.setText( 10+"" );
            }else{
                ShowTotalQuestion.setText( data.size()+"" );
            }
            Question.setText( data.get( CurrentNumber ).getContent()+"" );

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choose);
        UIOperation.SetFullScreen(this);
        // 请求网络资源
        new Thread( new Runnable() {
            @Override
            public void run() {
                data = getData();
                handler.sendMessage( new Message() );
            }
        } ).start();


        initView();
        initOnClickListener();

    }

    private void initOnClickListener() {

        MakeSure.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CurrentNumber == TOTAL_QUESTION || CurrentNumber == data.size())
                {
                    // 提示分数信息 返回上一层
                    Toast.makeText( AskQuestionTwo.this,"您的分数是"+CurrentScore+"分",Toast.LENGTH_SHORT ).show();
                    finish();
                }else {
                    if("".equals(Answer.getText().toString()))
                    {
                        Toast.makeText( AskQuestionTwo.this,"您还没有答题哦^_^",Toast.LENGTH_SHORT ).show();
                    }else{
                        Question.setText( data.get( CurrentNumber ).getContent()+"" );
                        CurrentNumber++;
                        ShowCurrentQuestion.setText( CurrentNumber+"" );
                        if(data.get( CurrentNumber-1 ).getAnswer().equals( Answer.getText().toString() ))
                        {
                            CurrentScore+=1;
                        }
                        Answer.setText( "" );
                    }
                }
            }
        } );

    }

    private void initView() {
        Question = findViewById(R.id.question);
        Answer   = findViewById(R.id.answer);
        MakeSure = findViewById(R.id.make_sure);
        ShowCurrentQuestion = findViewById( R.id.current_question );
        ShowTotalQuestion   = findViewById(R.id.total_question);
    }



    public List<Riddle_Data.ResultBean.ListBean> getData() {
        List<Riddle_Data.ResultBean.ListBean> b = new ArrayList<Riddle_Data.ResultBean.ListBean>(  );
        for(int i=0;i<10;i++)
        {
            b.add( Riddle_Data.Get_Riddle_Data().getResult().getList().get( 0 ) );
        }
        return b;
    }


}
