/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import info.gdgcatania.android.app.tictactoe.api.TTTConnectionAPI;
import info.gdgcatania.android.app.tictactoe.api.classes.Game;
import info.gdgcatania.android.app.tictactoe.api.classes.Result;
import info.gdgcatania.android.app.tictactoe.api.classes.TTTUser;
import info.gdgcatania.android.app.tictactoe.classes.Utils;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * TicTacToe Example GDG Devfest Mediterranean
 *
 */
public class ConnectionService extends IntentService {

    private static final String TAG = "TicTacToe Connection Service";



    public ConnectionService() {
        super("ConnectionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle data = intent.getExtras();
        int type=intent.getIntExtra(Utils.INTENT_TYPE,Utils.TYPE_DEFAULT);
        String caller = intent.getStringExtra(Utils.INTENT_CALL_CLASS);

        switch (type){
            case Utils.TYPE_PLAY:

                sendPlay((Game)data.getParcelable(Utils.INTENT_BUNDLE_NAME),caller);
                break;

            case Utils.TYPE_LEADER:

                getLeader(caller);
                break;


            case Utils.TYPE_REGISTER:
                register((TTTUser)data.getParcelable(Utils.INTENT_BUNDLE_NAME),caller);

                break;

            case Utils.TYPE_HISTORY:
                getHistory((TTTUser)data.getParcelable(Utils.INTENT_BUNDLE_NAME),caller);

                break;

            case Utils.TYPE_SAVE:
                saveGame((Result)data.getParcelable(Utils.INTENT_BUNDLE_NAME),caller);
                break;

            default:
                break;
        }
    }

    /** Send current game to backend throug ConnectionAPI
     *
     * @param currentGame current game state
     * @param caller caller View
     */
    private synchronized void sendPlay(Game currentGame,String caller){
        Game newGame= TTTConnectionAPI.play(currentGame);

        

        Intent sendResult=new Intent();

        Bundle extra=new Bundle();
        extra.putParcelable(Utils.INTENT_BUNDLE_NAME,newGame);

        sendResult.setAction(Utils.INTENT_ACTION);
        sendResult.addCategory(Utils.INTENT_ACTION);
        sendResult.putExtra(Utils.INTENT_TYPE,Utils.TYPE_RESPONSE);
        sendResult.putExtras(extra);
        sendResult.setClassName(getApplication(),caller);

        if(LocalBroadcastManager.getInstance(this).sendBroadcast(sendResult))
            Log.i(TAG,"Broadcast inviato a " + caller);
        else
            Log.e(TAG,"Broadcast non inviato a " + caller);



    }

    /** Send request to backend for download leaderboard
     *
     * @param caller caller View
     */
    private synchronized void getLeader(String caller){

        Intent sendResult=new Intent();

        Bundle extra=new Bundle();
        extra.putParcelableArrayList(Utils.INTENT_BUNDLE_NAME,TTTConnectionAPI.getLeaderboard());

        sendResult.setAction(Utils.INTENT_ACTION);
        sendResult.addCategory(Utils.INTENT_ACTION);
        sendResult.putExtra(Utils.INTENT_TYPE,Utils.TYPE_RESPONSE);
        sendResult.putExtras(extra);
        sendResult.setClassName(getApplication(),caller);

        if(LocalBroadcastManager.getInstance(this).sendBroadcast(sendResult))
            Log.i(TAG,"Broadcast inviato a " + caller);
        else
            Log.e(TAG,"Broadcast non inviato a " + caller);



    }

    /** send request to backend for download games history
     *
     * @param mUser current user
     * @param caller caller View
     */
    private synchronized void getHistory(TTTUser mUser,String caller){

        Intent sendResult=new Intent();

        Bundle extra=new Bundle();
        extra.putParcelableArrayList(Utils.INTENT_BUNDLE_NAME,TTTConnectionAPI.getPreviousGames(mUser));

        sendResult.setAction(Utils.INTENT_ACTION);
        sendResult.addCategory(Utils.INTENT_ACTION);
        sendResult.putExtra(Utils.INTENT_TYPE,Utils.TYPE_RESPONSE);
        sendResult.putExtras(extra);
        sendResult.setClassName(getApplication(),caller);

        if(LocalBroadcastManager.getInstance(this).sendBroadcast(sendResult))
            Log.i(TAG,"Broadcast inviato a " + caller);
        else
            Log.e(TAG,"Broadcast non inviato a " + caller);



    }

    /** send game result to backend
     *
     * @param mResult current game result
     * @param caller caller View
     */
    private synchronized void saveGame(Result mResult,String caller){

        Intent sendResult=new Intent();

        Bundle extra=new Bundle();
        extra.putBoolean(Utils.INTENT_BUNDLE_NAME,TTTConnectionAPI.sendResult(mResult));

        sendResult.setAction(Utils.INTENT_ACTION);
        sendResult.addCategory(Utils.INTENT_ACTION);
        sendResult.putExtra(Utils.INTENT_TYPE,Utils.TYPE_LOSS);
        sendResult.putExtras(extra);
        sendResult.setClassName(getApplication(),caller);

        if(LocalBroadcastManager.getInstance(this).sendBroadcast(sendResult))
            Log.i(TAG,"Broadcast inviato a " + caller);
        else
            Log.e(TAG,"Broadcast non inviato a " + caller);



    }

    /** Send registration to backend
     *
     * @param mUser current user
     * @param caller caller View
     */
    private synchronized void register(TTTUser mUser,String caller){

        Intent sendResult=new Intent();

        Bundle extra=new Bundle();
        extra.putBoolean(Utils.INTENT_BUNDLE_NAME,TTTConnectionAPI.register(mUser));

        sendResult.setAction(Utils.INTENT_ACTION);
        sendResult.addCategory(Utils.INTENT_ACTION);
        sendResult.putExtra(Utils.INTENT_TYPE,Utils.TYPE_LOSS);
        sendResult.putExtras(extra);
        sendResult.setClassName(getApplication(),caller);

        if(LocalBroadcastManager.getInstance(this).sendBroadcast(sendResult))
            Log.i(TAG,"Broadcast inviato a " + caller);
        else
            Log.e(TAG,"Broadcast non inviato a " + caller);



    }





}
