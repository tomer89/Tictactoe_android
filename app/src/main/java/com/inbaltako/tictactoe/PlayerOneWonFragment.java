package com.inbaltako.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerOneWonFragment extends Fragment {

    private ImageButton newGame;
    private ImageButton exit;

    public TieFragment newInstance() {
        return new TieFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_one_won, null);



        newGame = (ImageButton) view.findViewById(R.id.newGamePlayerOneWon);
        exit = (ImageButton) view.findViewById(R.id.exitPlayerOneWon);


        //mPublisherInterstitialAd.show();

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
