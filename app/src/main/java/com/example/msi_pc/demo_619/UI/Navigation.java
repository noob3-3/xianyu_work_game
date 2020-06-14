package com.example.msi_pc.demo_619.UI;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.msi_pc.demo_619.R;
import com.example.msi_pc.demo_619.Tools.UIOperation;
import com.example.msi_pc.demo_619.UI.Boom.Game;
import com.example.msi_pc.demo_619.UI.Boom.RankingList;
import com.example.msi_pc.demo_619.UI.Boom.User;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// 底部导航栏
public class Navigation extends FragmentActivity implements View.OnClickListener {

    // 用于Fragment之间的页面跳转
    public static Navigation navigation;


    /*********************************界面部分*********************************/
    public Fragment RankingListFragment;      // 排行榜
    public Fragment GameFragment;             // 游戏
    public Fragment UserFragment;             // 我的
    /*********************************界面部分*********************************/


    /*********************************点击部分*********************************/
    public RelativeLayout RankingListClick;
    public RelativeLayout GameClick;
    public RelativeLayout UserClick;
    /*********************************点击部分*********************************/

    public ImageView RankingListImg;
    public ImageView GameImg;
    public ImageView UserImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);
        navigation = this;
        initView();
        // 变暗
        ChangeToUnlight();
        // 设置首页为主界面
        if (GameFragment == null) {
            GameFragment = new Game();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            // 将所有UI设为暗色
            ChangeToUnlight();
            // 将首页变色
            beginTransaction.replace(R.id.id_context, GameFragment);
            beginTransaction.commit();
        }
    }

    // 变暗
    public void ChangeToUnlight()
    {
        RankingListImg.setImageResource(R.drawable.rankinglist_unlight);
        GameImg.setImageResource(R.drawable.game_unlight);
        UserImg.setImageResource(R.drawable.user_unlight);
    }


    // 初始化UI部分
    private void initView() {
        // 沉浸式
        UIOperation.SetFullScreen(this);

        UserImg        = findViewById(R.id.user_img);
        GameImg        = findViewById(R.id.game_img);
        RankingListImg = findViewById(R.id.ranking_list_img);

        RankingListClick = findViewById(R.id.ranking_list_click);
        GameClick        = findViewById(R.id.game_click);
        UserClick        = findViewById(R.id.user_click);


        RankingListClick.setOnClickListener(this);
        GameClick.setOnClickListener(this);
        UserClick.setOnClickListener(this);
    }


    // 点击事件
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        // 将所有UI设为暗色
        /*TODO*/
        ChangeToUnlight();
        /*TODO*/
        switch (v.getId())
        {
            // 首页
            case R.id.game_click:
                if(GameClick == null)
                {
                    GameFragment = new Game();
                }
                // 切换界面
                beginTransaction.replace(R.id.id_context,GameFragment);
                GameImg.setImageResource(R.drawable.game_light);
                break;
            // 课程列表
            case R.id.ranking_list_click:
                if(RankingListFragment == null)
                {
                    RankingListFragment = new RankingList();
                }
                // 切换界面
                beginTransaction.replace(R.id.id_context,RankingListFragment);
                RankingListImg.setImageResource(R.drawable.rankinglist_light);
                break;
            // 用户界面
            case R.id.user_click:
                if(UserFragment == null)
                {
                    UserFragment = new User();
                }
                // 切换界面
                beginTransaction.replace(R.id.id_context,UserFragment);
                UserImg.setImageResource(R.drawable.user_light);
                break;
        }
        // 提交
        beginTransaction.commit();
    }
}
