/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.api.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 13/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * User class
 */
public class TTTUser implements Parcelable{

    private String Name;
    private String Email;

    public TTTUser(String email, String name) {
        this.Email = email;
        this.Name = name;
    }


    public TTTUser (Parcel var){
        this.Name =var.readString();
        this.Email =var.readString();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
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
        parcel.writeString(this.Name);
        parcel.writeString(this.Email);
    }

    @SuppressWarnings("rawtypes")
    public final static Creator CREATOR = new Creator() {
        @Override
        public TTTUser createFromParcel(Parcel source) {
            return new TTTUser(source);
        }

        @Override
        public TTTUser[] newArray(int size) {
            return new TTTUser[size];
        }
    };
}
