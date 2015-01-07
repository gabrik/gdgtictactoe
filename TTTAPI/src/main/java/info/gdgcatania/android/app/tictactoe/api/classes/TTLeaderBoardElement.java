/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */
package info.gdgcatania.android.app.tictactoe.api.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 11/10/14.
 * Provide Result wrapper
 */
public class TTLeaderBoardElement implements Parcelable,Comparable<TTLeaderBoardElement>{


    private static final String TAG = "TTLeaderBoardElement";
    private String mUserName;
    private int mScore;


    public TTLeaderBoardElement(String mUserName, int mScore) {
        this.mUserName = mUserName;
        this.mScore = mScore;
    }

    public TTLeaderBoardElement(Parcel source) {
        this.mUserName=source.readString();
        this.mScore=source.readInt();

    }

    public TTLeaderBoardElement(JSONObject obj) {
        try {
            this.mScore=obj.getInt("Score");
            this.mUserName=obj.getString("Name");
        } catch (JSONException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }
    }

    public String getmUserName() {
        return mUserName;
    }

    public int getmScore() {
        return mScore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.mUserName);
        parcel.writeInt(this.mScore);

    }

    @SuppressWarnings("rawtypes")
    public final static Creator CREATOR = new Creator() {
        @Override
        public TTLeaderBoardElement createFromParcel(Parcel source) {
            return new TTLeaderBoardElement(source);
        }

        @Override
        public TTLeaderBoardElement[] newArray(int size) {
            return new TTLeaderBoardElement[size];
        }
    };

    @Override
    public int compareTo(TTLeaderBoardElement o) {
        return this.mScore-o.mScore;
    }
}
