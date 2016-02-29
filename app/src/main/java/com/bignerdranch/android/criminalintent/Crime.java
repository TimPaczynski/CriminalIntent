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

    public Crime(){
        //generate unique crime identifier
        mId = UUID.randomUUID();
        mDate = new Date();
        sdf = new SimpleDateFormat("EEEE, LLLL d, y", Locale.US);

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




}

