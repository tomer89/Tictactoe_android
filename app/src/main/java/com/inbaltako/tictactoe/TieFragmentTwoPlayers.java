package com.inbaltako.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;



public class TieFragmentTwoPlayers extends Fragment {
    private ImageButton newGame;
    private ImageButton exit;

    public TieFragment newInstance() {
        return new TieFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tie_fragment_two_players, null);
        newGame = (ImageButton) view.findViewById(R.id.newGameTieTwoPlayer);
        exit = (ImageButton) view.findViewById(R.id.exitTieTwoPlayer);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoardActivityPlayer.class));
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
