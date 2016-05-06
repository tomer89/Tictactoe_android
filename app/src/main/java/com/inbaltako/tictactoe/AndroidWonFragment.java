package com.inbaltako.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AndroidWonFragment extends Fragment {

    private ImageButton newGame;
    private ImageButton exit;

    public AndroidWonFragment newInstance() {
        return new AndroidWonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android_win_fragment, container, false);
        newGame = (ImageButton) view.findViewById(R.id.newGameAndroidWon);
        exit = (ImageButton) view.findViewById(R.id.exitAndroidWon);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoardActivity.class));
                getActivity().finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}
