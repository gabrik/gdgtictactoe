/*
 * Copyright (c) 2014. Nunzio Giulio Caggegi.
 */

package info.gdgcatania.android.app.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import gdgcatania.gabrielebaldoni.devfest.tictactoe.R;
import info.gdgcatania.android.app.tictactoe.api.classes.Game;
import info.gdgcatania.android.app.tictactoe.api.classes.Result;
import info.gdgcatania.android.app.tictactoe.classes.Utils;
import info.gdgcatania.android.app.tictactoe.services.ConnectionService;


public class MainActivity extends Activity {


    private static final String TAG = "TicTacToe PlayActivity";

    private Game theGame;

    private ImageView i0;
    private ImageView i1;
    private ImageView i2;
    private ImageView i3;
    private ImageView i4;
    private ImageView i5;
    private ImageView i6;
    private ImageView i7;
    private ImageView i8;
    private ServiceReceiver receiver;
    private AlertDialog diag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i0=(ImageView) findViewById(R.id.imageView);
        i1=(ImageView) findViewById(R.id.imageView2);
        i2=(ImageView) findViewById(R.id.imageView3);
        i3=(ImageView) findViewById(R.id.imageView4);
        i4=(ImageView) findViewById(R.id.imageView5);
        i5=(ImageView) findViewById(R.id.imageView6);
        i6=(ImageView) findViewById(R.id.imageView7);
        i7=(ImageView) findViewById(R.id.imageView8);
        i8=(ImageView) findViewById(R.id.imageView9);

        clearTableau();


        i0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i0.getDrawable()==null){
                    i0.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[0]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i1.getDrawable()==null){
                    i1.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[1]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });


        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i2.getDrawable()==null){
                    i2.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[2]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i3.getDrawable()==null){
                    i3.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[3]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i4.getDrawable()==null){
                    i4.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[4]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i5.getDrawable()==null){
                    i5.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[5]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i6.getDrawable()==null){
                    i6.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[6]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i7.getDrawable()==null){
                    i7.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[7]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i8.getDrawable()==null){
                    i8.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    char[] tableau=theGame.getStatus().toCharArray();
                    tableau[8]='X';
                    theGame.statusFromTableau(tableau);
                    sendGame(theGame);
                }
            }
        });





    }

    private void sendGame(Game currentGame){
        Bundle data=new Bundle();
        data.putParcelable(Utils.INTENT_BUNDLE_NAME,currentGame);
        Intent mRegIntent = new Intent(getApplicationContext(), ConnectionService.class);
        mRegIntent.putExtra(Utils.INTENT_TYPE, Utils.TYPE_PLAY);
        mRegIntent.putExtra(Utils.INTENT_CALL_CLASS,ServiceReceiver.class.getName());
        mRegIntent.putExtras(data);
        startService(mRegIntent);
    }

    private int updateUI(Game currentGame){

        this.theGame=currentGame;


        if(currentGame.winner(1))
            return 1;
        if(currentGame.winner(-1))
            return -1;
        if(currentGame.over())
            return -2;


        char[] p = currentGame.getStatus().toCharArray();
        switch(p[0]){
            case 'X':{
                i0.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i0.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[1]){
            case 'X':{
                i1.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i1.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[2]){
            case 'X':{
                i2.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i2.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[3]){
            case 'X':{
                i3.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i3.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[4]){
            case 'X':{
                i4.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i4.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[5]){
            case 'X':{
                i5.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i5.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[6]){
            case 'X':{
                i6.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i6.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[7]){
            case 'X':{
                i7.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i7.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        switch(p[8]){
            case 'X':{
                i8.setImageDrawable(getResources().getDrawable(R.drawable.x));
                break;
            }
            case 'O':{
                i8.setImageDrawable(getResources().getDrawable(R.drawable.o));
                break;
            }

        }

        return 0;
    }



    private void clearTableau(){
        theGame=new Game("---------");

          i0.setImageDrawable(null);
          i1.setImageDrawable(null);
          i2.setImageDrawable(null);
          i3.setImageDrawable(null);
          i4.setImageDrawable(null);
          i5.setImageDrawable(null);
          i6.setImageDrawable(null);
          i7.setImageDrawable(null);
          i8.setImageDrawable(null);




    }

    private void alert(String text){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearTableau();
                        diag.dismiss();
                        updateUI(theGame);
                    }
                });
        diag=builder.create();
        diag.show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        

        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                    Intent openLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(openLogin);
                    return true;
            case R.id.action_leader:
                Intent openLeader = new Intent(getApplicationContext(), Leaderboard.class);
                startActivity(openLeader);
                return true;
            case R.id.action_history:
                Intent openHistory = new Intent(getApplicationContext(),History.class);
                startActivity(openHistory);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    public class ServiceReceiver extends BroadcastReceiver {
        // <receiver android:name="com.cryptochat.app.activity.ActivityRegister$ServiceReceiver" android:enabled="true"/>
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG + " " + ServiceReceiver.class.getSimpleName(), "intent ricevuto");
            int type = intent.getIntExtra(Utils.INTENT_TYPE, -1);

            Intent mRegIntent = new Intent(getApplicationContext(), ConnectionService.class);
            Bundle resData;

            switch (type) {
                case Utils.TYPE_RESPONSE:
                    Bundle data = intent.getExtras();
                    Game curGame=data.getParcelable(Utils.INTENT_BUNDLE_NAME);
                    int res=updateUI(curGame);
                    switch (res){
                        case 1:
                            resData=new Bundle();
                            resData.putParcelable(Utils.INTENT_BUNDLE_NAME,new Result(Utils.currentUser.getEmail(),10));

                            mRegIntent.putExtra(Utils.INTENT_TYPE, Utils.TYPE_SAVE);
                            mRegIntent.putExtra(Utils.INTENT_CALL_CLASS,MainActivity.ServiceReceiver.class.getName());
                            mRegIntent.putExtras(resData);
                            startService(mRegIntent);
                            alert("Hai vinto!!!");
                            break;
                        case -1:alert("Hai perso!!!");
                            resData=new Bundle();
                            resData.putParcelable(Utils.INTENT_BUNDLE_NAME,new Result(Utils.currentUser.getEmail(),-5));

                            mRegIntent.putExtra(Utils.INTENT_TYPE, Utils.TYPE_SAVE);
                            mRegIntent.putExtra(Utils.INTENT_CALL_CLASS,MainActivity.ServiceReceiver.class.getName());
                            mRegIntent.putExtras(resData);
                            startService(mRegIntent);
                            break;
                        case -2: alert("Finee!!!");
                            resData=new Bundle();
                            resData.putParcelable(Utils.INTENT_BUNDLE_NAME,new Result(Utils.currentUser.getEmail(),0));

                            mRegIntent.putExtra(Utils.INTENT_TYPE, Utils.TYPE_SAVE);
                            mRegIntent.putExtra(Utils.INTENT_CALL_CLASS,MainActivity.ServiceReceiver.class.getName());
                            mRegIntent.putExtras(resData);
                            startService(mRegIntent);
                            break;
                        default:
                            break;
                    }
                    break;



                default:
                    Log.e(TAG + " " + ServiceReceiver.class.getSimpleName(), "intent ricevuto con type " + type);
            }

        }
    

}

}
