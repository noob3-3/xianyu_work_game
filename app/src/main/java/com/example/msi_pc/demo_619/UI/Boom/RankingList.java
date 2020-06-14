package com.example.msi_pc.demo_619.UI.Boom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.msi_pc.demo_619.R;
import com.example.msi_pc.demo_619.Tools.UIOperation;
import com.example.msi_pc.demo_619.UI.Navigation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


// 主页
public class RankingList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_ranking_list, container, false);
        // 全面屏
        UIOperation.SetFullScreen(Navigation.navigation);
        initView(view);
        initOnClickListener();
        return view;
    }


    private void initView(View view)
    {

    }

    private void initOnClickListener()
    {

    }

}
