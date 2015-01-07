/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.api;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import info.gdgcatania.android.app.tictactoe.api.classes.Game;
import info.gdgcatania.android.app.tictactoe.api.classes.Result;
import info.gdgcatania.android.app.tictactoe.api.classes.TTLeaderBoardElement;
import info.gdgcatania.android.app.tictactoe.api.classes.TTTUser;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 11/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * Allow Android App to comunicate with WebService
 */
public class TTTConnectionAPI {

    private static final String TAG = "TicTacToe Connection API";

    private static final String CUSTOM_USER_AGENT = "TicTacToe Client";

    private static final String BACKEND_ADDRESS = "";

    private static final String API_PATH = "/appConcurrent";

    private static final String OLD_GAMES_PATH = "/appHistory";

    private static final String STORE_GAME_PATH = "/appSave";

    private static final String GET_LEADERBOARD_PATH = "/appLeaderboardConc";

    private static final String REGISTER_PATH = "/appNewUser";

    private static final String GETPARAMETER = "status";

    private static final String STAT_OBJ = "Status";
    private static final String TAB_OBJ = "Tableau";


    private static final int timeoutSocket = 20000;
    private static final int timeoutConnection = 20000;


    /** Send current game to backend and get the response from IA
     *
     * @param currentGame the current game state
     * @return the game after IA action
     */
    public static Game play(Game currentGame){

        Game newGame=null;


        HttpParams mParams = new BasicHttpParams();


        HttpConnectionParams.setConnectionTimeout(mParams, timeoutConnection);

        HttpConnectionParams.setSoTimeout(mParams, timeoutSocket);

        HttpClient mHttpClient = new DefaultHttpClient(mParams);

        String apiPage=BACKEND_ADDRESS+API_PATH;

        StringBuilder requestUrl = new StringBuilder(apiPage);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(GETPARAMETER, currentGame.toJSON()));

        String querystring = URLEncodedUtils.format(nameValuePairs, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);



        HttpGet mApiGetRequest = new HttpGet(requestUrl.toString());

        Log.i(TAG,"URL is: " + apiPage+"?"+currentGame.toJSON());

        try {

            mApiGetRequest.setHeader("User-Agent", CUSTOM_USER_AGENT);
            HttpResponse mHttpResponse = mHttpClient.execute(mApiGetRequest);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            String mHttpStringResult= EntityUtils.toString(mHttpEntity);

            Log.i(TAG,"Backend replied: " + mHttpStringResult);

            JSONObject mJsonObject = new JSONObject(mHttpStringResult);


            String status=(String)mJsonObject.get(STAT_OBJ);
            newGame= new Game(status);

        } catch (JSONException e){
            Log.e(TAG, "Error:  " + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }



        return newGame;
    }


    /**
     * Read old games stored in Datastore
     * @param mUser current user
     * @return an array of old games
     */
    public static ArrayList<Result> getPreviousGames (TTTUser mUser){
        ArrayList<Result> mOldGames = new ArrayList<Result>();

        HttpParams mParams = new BasicHttpParams();


        HttpConnectionParams.setConnectionTimeout(mParams, timeoutConnection);

        HttpConnectionParams.setSoTimeout(mParams, timeoutSocket);

        HttpClient mHttpClient = new DefaultHttpClient(mParams);

        String apiPage=BACKEND_ADDRESS+OLD_GAMES_PATH;

        StringBuilder requestUrl = new StringBuilder(apiPage);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(GETPARAMETER, mUser.toJSON()));

        String querystring = URLEncodedUtils.format(nameValuePairs, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);



        HttpGet mApiGetRequest = new HttpGet(requestUrl.toString());

        Log.i(TAG,"URL is: " + apiPage+"?"+mUser.toJSON());

        try {

            mApiGetRequest.setHeader("User-Agent", CUSTOM_USER_AGENT);
            HttpResponse mHttpResponse = mHttpClient.execute(mApiGetRequest);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            String mHttpStringResult= EntityUtils.toString(mHttpEntity);

            Log.i(TAG,"Backend replied: " + mHttpStringResult);


            JSONArray mJsonArray = new JSONArray(mHttpStringResult);

            for(int i=0;i<mJsonArray.length();i++){
                JSONObject obj=mJsonArray.getJSONObject(i);
                mOldGames.add(new Result(obj));
            }


        } catch (JSONException e){
            Log.e(TAG, "Error:  " + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }


        return mOldGames;
    }

    /**
     * Send a game result to server
     * @param mResult result of the game
     * @return true if everything went fine, otherwise false
     */
    public static boolean sendResult(Result mResult){
        boolean res=false;



        HttpParams mParams = new BasicHttpParams();


        HttpConnectionParams.setConnectionTimeout(mParams, timeoutConnection);

        HttpConnectionParams.setSoTimeout(mParams, timeoutSocket);

        HttpClient mHttpClient = new DefaultHttpClient(mParams);

        String apiPage=BACKEND_ADDRESS+STORE_GAME_PATH;

        StringBuilder requestUrl = new StringBuilder(apiPage);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(GETPARAMETER, mResult.toJSON()));

        String querystring = URLEncodedUtils.format(nameValuePairs, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);



        HttpGet mApiGetRequest = new HttpGet(requestUrl.toString());

        Log.i(TAG,"URL is: " + apiPage+"?"+mResult.toJSON());

        try {

            mApiGetRequest.setHeader("User-Agent", CUSTOM_USER_AGENT);
            HttpResponse mHttpResponse = mHttpClient.execute(mApiGetRequest);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            String mHttpStringResult= EntityUtils.toString(mHttpEntity);

            Log.i(TAG,"Backend replied: " + mHttpStringResult);

            JSONObject mJsonObject = new JSONObject(mHttpStringResult);
            if(mJsonObject.getBoolean("res")==true) res=true;


        } catch (JSONException e){
            Log.e(TAG, "Error:  " + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }


        return res;
    }

    /**
     *  Get current leaderboard
     * @return ArrayList rappresenting the current leaderboard
     */
    public static ArrayList<TTLeaderBoardElement> getLeaderboard (){
        ArrayList<TTLeaderBoardElement> mLeaders = new ArrayList<TTLeaderBoardElement>();

        HttpParams mParams = new BasicHttpParams();


        HttpConnectionParams.setConnectionTimeout(mParams, timeoutConnection);

        HttpConnectionParams.setSoTimeout(mParams, timeoutSocket);

        HttpClient mHttpClient = new DefaultHttpClient(mParams);

        String apiPage=BACKEND_ADDRESS+GET_LEADERBOARD_PATH;

        StringBuilder requestUrl = new StringBuilder(apiPage);


        HttpGet mApiGetRequest = new HttpGet(requestUrl.toString());

        Log.i(TAG,"URL is: " + apiPage);

        try {

            mApiGetRequest.setHeader("User-Agent", CUSTOM_USER_AGENT);
            HttpResponse mHttpResponse = mHttpClient.execute(mApiGetRequest);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            String mHttpStringResult= EntityUtils.toString(mHttpEntity);

            Log.i(TAG,"Backend replied: " + mHttpStringResult);


            JSONArray mJsonArray = new JSONArray(mHttpStringResult);

            for(int i=0;i<mJsonArray.length();i++){
                JSONObject obj=mJsonArray.getJSONObject(i);
                mLeaders.add(new TTLeaderBoardElement(obj));
            }



        } catch (JSONException e){
            Log.e(TAG, "Error:  " + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }

        Collections.sort(mLeaders);

        return mLeaders;
    }

    /**
     * Register use to App
     * @param mUser current user
     * @return true if everything went fine, otherwise false
     */
    public static boolean register (TTTUser mUser){
        boolean res=false;

        HttpParams mParams = new BasicHttpParams();


        HttpConnectionParams.setConnectionTimeout(mParams, timeoutConnection);

        HttpConnectionParams.setSoTimeout(mParams, timeoutSocket);

        HttpClient mHttpClient = new DefaultHttpClient(mParams);

        String apiPage=BACKEND_ADDRESS+REGISTER_PATH;

        StringBuilder requestUrl = new StringBuilder(apiPage);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(GETPARAMETER, mUser.toJSON()));

        String querystring = URLEncodedUtils.format(nameValuePairs, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);



        HttpGet mApiGetRequest = new HttpGet(requestUrl.toString());

        Log.i(TAG,"URL is: " + apiPage+"?"+mUser.toJSON());

        try {

            mApiGetRequest.setHeader("User-Agent", CUSTOM_USER_AGENT);
            HttpResponse mHttpResponse = mHttpClient.execute(mApiGetRequest);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            String mHttpStringResult= EntityUtils.toString(mHttpEntity);

            Log.i(TAG,"Backend replied: " + mHttpStringResult);

            JSONObject mJsonObject = new JSONObject(mHttpStringResult);



        } catch (JSONException e){
            Log.e(TAG, "Error:  " + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error:  " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error:  " + e.toString());
        }


        return res;
    }





}
