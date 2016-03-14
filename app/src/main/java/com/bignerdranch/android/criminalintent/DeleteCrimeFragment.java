package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by MCS on 3/14/2016.
 */
public class DeleteCrimeFragment extends DialogFragment {

    public static final String CRIMES_CRIME = "crimes";

    public static DeleteCrimeFragment newInstance(Crime crime) {
        Bundle args = new Bundle();
        DeleteCrimeFragment fragment = new DeleteCrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceVariable){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_the_crime)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendResults(Activity.RESULT_OK, false);
                    }
                })
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendResults(Activity.RESULT_OK, true);
                    }
                });
        return builder.create();

    }
    public void sendResults(int result_code, boolean truth_value){
        if (getTargetFragment() ==null)
            return;

        Intent intent =new Intent();
        intent.putExtra(CRIMES_CRIME, truth_value);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), result_code, intent);
    }
}
