/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import gdgcatania.gabrielebaldoni.devfest.tictactoe.R;
import info.gdgcatania.android.app.tictactoe.api.classes.Result;
import info.gdgcatania.android.app.tictactoe.adapters.HistoryAdapter;
import info.gdgcatania.android.app.tictactoe.classes.Utils;
import info.gdgcatania.android.app.tictactoe.services.ConnectionService;


public class History extends Activity {

    private ServiceReceiver receiver;

    private static final String TAG = "History Activity";
    private ListView mList;
    private ArrayList<Result> mResultList;
    private HistoryAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        mList = (ListView) findViewById(R.id.listViewLeader);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        sendRequest();

    }


    private void sendRequest(){

        Bundle data=new Bundle();
        data.putParcelable(Utils.INTENT_BUNDLE_NAME,Utils.currentUser);

        Intent mRegIntent = new Intent(getApplicationContext(), ConnectionService.class);
        mRegIntent.putExtra(Utils.INTENT_TYPE, Utils.TYPE_HISTORY);
        mRegIntent.putExtra(Utils.INTENT_CALL_CLASS,ServiceReceiver.class.getName());
        mRegIntent.putExtras(data);
        startService(mRegIntent);
    }


    private void updateUI() {
        mAdapter = new HistoryAdapter(mResultList, this);
        mList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

        } catch (Exception e) {
            Log.e(TAG, "unregister error" + e.getMessage());
        }

        super.onPause();
    }

    @Override
    protected void onResume() {

        IntentFilter filter = new IntentFilter(Utils.INTENT_ACTION);
        filter.addCategory(Utils.INTENT_ACTION);
        filter.addAction(Utils.INTENT_ACTION);
        filter.addAction(Utils.INTENT_ACTION);

        receiver = new ServiceReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);



        super.onResume();
    }


    public class ServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG + " " + ServiceReceiver.class.getSimpleName(), "intent ricevuto");
            int type = intent.getIntExtra(Utils.INTENT_TYPE, -1);

            switch (type) {
                case Utils.TYPE_RESPONSE:
                    Bundle data = intent.getExtras();
                    mResultList = data.getParcelableArrayList(Utils.INTENT_BUNDLE_NAME);
                    updateUI();
                    break;


                default:
                    Log.e(TAG + " " + ServiceReceiver.class.getSimpleName(), "intent ricevuto con type " + type);
            }

        }


    }
}
