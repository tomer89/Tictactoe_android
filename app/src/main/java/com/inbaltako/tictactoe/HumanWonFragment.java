package com.inbaltako.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HumanWonFragment extends Fragment {

    private ImageButton newGame;
    private ImageButton exit;

    public HumanWonFragment newInstance() {
        return new HumanWonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_human_win_fragment, container, false);
        newGame = (ImageButton) view.findViewById(R.id.newGameHumanWon);
        exit = (ImageButton) view.findViewById(R.id.exitHumanWon);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoardActivityComp.class));
                getActivity().finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
