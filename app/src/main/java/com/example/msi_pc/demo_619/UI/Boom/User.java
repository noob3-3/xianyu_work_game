package com.example.msi_pc.demo_619.UI.Boom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.msi_pc.demo_619.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


// 用户
public class User extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_user, container, false);
        initView(view);
        initOnClickListener();
        return view;
    }



    private void initView(View view) {
    }

    private void initOnClickListener() {
    }


}
