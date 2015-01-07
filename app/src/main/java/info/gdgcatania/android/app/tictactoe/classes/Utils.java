/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.classes;

import info.gdgcatania.android.app.tictactoe.api.classes.TTTUser;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 11/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * This containts some utils
 */
public class Utils {

    //Costanti per intent
    public static final String INTENT_TYPE = "INT_TYPE";
    public static final String INTENT_ACTION = "info.gdgcatania.app.INTENT";
    public static final String INTENT_CALL_CLASS = "CALLER";

    public static final String INTENT_BUNDLE_NAME = "GAME";

    public static final int TYPE_DEFAULT = -1;

    public static final int TYPE_PLAY = 1;
    public static final int TYPE_RESPONSE = 2;
    public static final int TYPE_LEADER = 3;
    public static final int TYPE_REGISTER = 4;
    public static final int TYPE_HISTORY = 5;
    public static final int TYPE_SAVE = 6;

    public static final int TYPE_LOSS = 6;



    public static TTTUser currentUser;


}
