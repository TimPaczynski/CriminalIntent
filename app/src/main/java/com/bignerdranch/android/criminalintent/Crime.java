package com.bignerdranch.android.criminalintent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by MCS on 2/23/2016.
 */
public class Crime {
    private UUID mId;
    private  String mTitle;
    private Date mDate;
    private boolean mSolved;
    private SimpleDateFormat sdf;
    private String mSuspect;

    public Crime(){
        //generate unique crime identifier
        this(UUID.randomUUID());
        sdf = new SimpleDateFormat("EEEE, LLLL d, y", Locale.US);

    }
    public Crime(UUID id){
        mId = id;
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved() {
        return mSolved;
    }

    public String crimeDate() {
        return sdf.format(mDate);
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }




}

