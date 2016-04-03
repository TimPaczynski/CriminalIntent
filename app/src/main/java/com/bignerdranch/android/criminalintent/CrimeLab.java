package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDBSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by MCS on 3/1/2016.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDBSchema.CrimeTable.NAME, null, values);

    }
    public void removeCrime(Crime c) {
        String uuidString = c.getId().toString();

        mDatabase.delete(CrimeDBSchema.CrimeTable.NAME,
                CrimeDBSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] {uuidString}
                );
    }


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
        }
    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();

        }

    public List<Crime> getCrimes(){

        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDBSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0 ){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }


    }

    public File getPhotoFile(Crime crime) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null){
            return null;
        }

        return new File(externalFilesDir, crime.getPhotoFilename());
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDBSchema.CrimeTable.NAME, values,
                CrimeDBSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] { uuidString});

    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDBSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDBSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDBSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDBSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDBSchema.CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }
    private CrimeCursorWrapper queryCrimes ( String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDBSchema.CrimeTable.NAME,
                null, //null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null

        );
        return new CrimeCursorWrapper(cursor);
    }
}


