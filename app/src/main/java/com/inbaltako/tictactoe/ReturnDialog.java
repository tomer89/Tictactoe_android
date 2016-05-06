package com.inbaltako.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ReturnDialog extends DialogFragment {

    private Button stay;
    private Button leave;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.return_dialog, null);
        stay = (Button) view.findViewById(R.id.dialogButtonStay);
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        leave = (Button) view.findViewById(R.id.dialogButtonLeave);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MenuActivity.class));
                getActivity().finish();
            }
        });

        builder.setView(view);
        builder.setCancelable(false);

        return builder.create();
    }
}
