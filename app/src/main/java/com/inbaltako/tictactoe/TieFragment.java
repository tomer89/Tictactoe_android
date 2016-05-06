package com.inbaltako.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class TieFragment extends Fragment {

    ImageButton newGame;
    ImageButton exit;

    public TieFragment newInstance() {
        return new TieFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tie_fragment, null);
        newGame = (ImageButton) view.findViewById(R.id.newGameTie);
        exit = (ImageButton) view.findViewById(R.id.exitTie);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoardActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
