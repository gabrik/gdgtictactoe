/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.api.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 11/10/14.
 * Provide Result wrapper
 */
public class Result implements Parcelable {


    private static final String TAG ="Result" ;
    private int score;
    private String Email;


    public Result(String email, int score) {
        Email = email;
        this.score = score;
    }

    public Result(Parcel source) {
        this.score=source.readInt();
        this.Email=source.readString();



    }

    public Result(JSONObject obj) {
        try {
            this.score=obj.getInt("Score");
            this.Email=obj.getString("Email");

        } catch (JSONException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }

    }

    public int getScore() {
        return score;
    }

    public String getEmail() {
        return Email;
    }

    public String toJSON(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.score);
        parcel.writeString(this.Email);


    }


    @SuppressWarnings("rawtypes")
    public final static Creator CREATOR = new Creator() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
