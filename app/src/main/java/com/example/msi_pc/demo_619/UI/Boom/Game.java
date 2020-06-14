package com.example.msi_pc.demo_619.UI.Boom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.msi_pc.demo_619.R;
import com.example.msi_pc.demo_619.Tools.UIOperation;
import com.example.msi_pc.demo_619.UI.AskQuestionOne;
import com.example.msi_pc.demo_619.UI.AskQuestionThree;
import com.example.msi_pc.demo_619.UI.AskQuestionTwo;
import com.example.msi_pc.demo_619.UI.Navigation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Game extends Fragment {

    public Button one;
    public Button two;
    public Button  three;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_game, container, false);
        initView(view);
        initOnClickListener();
        return view;
    }



    private void initView(View view) {
        // 全面屏
        UIOperation.SetFullScreen(Navigation.navigation);
        one = view.findViewById( R.id.one );
        two = view.findViewById( R.id.two);
        three = view.findViewById( R.id.three );
    }

    private void initOnClickListener() {

        one.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Navigation.navigation, AskQuestionOne.class );
                startActivity( intent );

            }
        } );

        two.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Navigation.navigation, AskQuestionTwo.class );
                startActivity( intent );

            }
        } );

        three.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.navigation, AskQuestionThree.class );
                startActivity( intent );
            }
        } );

    }

}

