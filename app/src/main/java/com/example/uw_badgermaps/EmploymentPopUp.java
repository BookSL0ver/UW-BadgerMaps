package com.example.uw_badgermaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class EmploymentPopUp extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Employment")
                .setMessage("UW-Madison posts open student jobs to https://studentjobs.wisc.edu/\nThrough the university you also have access to Handshake to look for outside or post-graduation employment: https://careers.wisc.edu/handshake/")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
