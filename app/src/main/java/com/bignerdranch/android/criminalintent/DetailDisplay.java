package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.DialogFragment;

import java.io.File;

/**
 * Created by MCS on 4/3/2016.
 */

public class DetailDisplay extends DialogFragment {

    public static final String PHOTO_FILE = "photofile";

    private ImageView mPhotoView;



    public static DetailDisplay newInstance(File photo){
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_FILE, photo);
        DetailDisplay fragment = new DetailDisplay();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        File photoFile = (File) getArguments().getSerializable(PHOTO_FILE);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.display_dialog, null);

        mPhotoView = (ImageView) v.findViewById(R.id.enlarge_photo_view);
        Bitmap bitmap = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
        mPhotoView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        }).create();
    }
}
