/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.api.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import java.util.Arrays;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 11/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * This class rappresent the TicTacToe game
 */
public class Game implements Parcelable {

    private static final String TAG = "TicTacToe Game";

    private String status;
    private int[][] tableau;

    public Game(String status) {
        this.status = status;
        this.makeTableau();
    }

    public Game(String status, int[][] tableau) {
        this.status = status;
        this.tableau = tableau;
    }


    public Game (Parcel var){
        this.status=var.readString();
        this.makeTableau();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int[][] getTableau() {
        return tableau;
    }

    public void setTableau(int[][] tableau) {
        this.tableau = tableau;
    }


    public void makeTableau(){

        char[] buffArray = new char[9];

        int[][]x = new int[3][3];

        for(int i=0;i<this.status.length();i++){
            buffArray[i]=this.status.charAt(i);
        }

        char[] firstRow= Arrays.copyOfRange(buffArray,0,3);
        char[] secondRow= Arrays.copyOfRange(buffArray,3,6);
        char[] thirdRow= Arrays.copyOfRange(buffArray,6,9);

        for(int i=0;i<firstRow.length;i++){
            switch (firstRow[i]){
                case 'O': x[0][i]=-1;
                        break;
                case 'X': x[0][i]=1;
                    break;
                case '-': x[0][i]=0;
                    break;
            }
        }

        for(int i=0;i<secondRow.length;i++){
            switch (secondRow[i]){
                case 'O': x[1][i]=-1;
                    break;
                case 'X': x[1][i]=1;
                    break;
                case '-': x[1][i]=0;
                    break;
            }
        }

        for(int i=0;i<thirdRow.length;i++){
            switch (thirdRow[i]){
                case 'O': x[2][i]=-1;
                    break;
                case 'X': x[2][i]=1;
                    break;
                case '-': x[2][i]=0;
                    break;
            }
        }

        this.tableau=x;


    }

    public int score(){
        if(this.winner(1)) return 10;
        if(this.winner(-1)) return -10;
        return 0;
    }

    public boolean winner(int player){
        if(this.tableau[0][0]+this.tableau[0][1]+this.tableau[0][2]==3*player){
            return true;
        }
        if(this.tableau[1][0]+this.tableau[1][1]+this.tableau[1][2]==3*player){
            return true;
        }
        if(this.tableau[2][0]+this.tableau[2][1]+this.tableau[2][2]==3*player){
            return true;
        }

        if(this.tableau[0][0]+this.tableau[1][1]+this.tableau[2][2]==3*player){
            return true;
        }
        if(this.tableau[0][0]+this.tableau[1][0]+this.tableau[2][0]==3*player){
            return true;
        }
        if(this.tableau[2][2]+this.tableau[1][1]+this.tableau[0][0]==3*player){
            return true;
        }
        if(this.tableau[0][1]+this.tableau[1][1]+this.tableau[2][1]==3*player){
            return true;
        }
        if(this.tableau[0][2]+this.tableau[1][2]+this.tableau[2][2]==3*player){
            return true;
        }
        return false;
    }

    public boolean over(){
        int sum=0;

        for (int j=0;j<3;j++){
            for (int i=0; i<3; i++){
                if (this.tableau[j][i]==0) sum+=1;
            }
        }

        if (sum==0) return true;

        return false;
    }

    public void statusFromTableau(char[] sTableau){
        this.status="";
        for(int i=0;i<sTableau.length;i++){
            this.status+=sTableau[i];
        }
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
        parcel.writeString(this.status);
    }

    @SuppressWarnings("rawtypes")
    public final static Creator CREATOR = new Creator() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
