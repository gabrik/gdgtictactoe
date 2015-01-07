/*
 * Copyright (c) 2014. Gabriele Baldoni.
 */

package info.gdgcatania.android.app.tictactoe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.gdgcatania.android.app.tictactoe.api.classes.TTLeaderBoardElement;
import gdgcatania.gabrielebaldoni.devfest.tictactoe.R;


/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 17/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * This class implements the ArrayAdaper for LeaderBoard
 */
public class LeaderboardAdapter extends BaseAdapter {

    private ArrayList<TTLeaderBoardElement> mLeaderboard;
    private Context mContext;

    public LeaderboardAdapter(ArrayList<TTLeaderBoardElement> mLeaderboard, Context mContext) {
        this.mLeaderboard = mLeaderboard;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mLeaderboard!=null)
            return mLeaderboard.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(mLeaderboard != null)
            return mLeaderboard.get(i);

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder mHolder;
        TTLeaderBoardElement mElement = mLeaderboard.get(i);
        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        if (view == null) {
            view = vi.inflate(R.layout.leaderboard_list_element, null);
            mHolder = createViewHolder(view);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();

        }

        mHolder.txtPosition.setText("P. "+i+1);
        mHolder.txtName.setText(mElement.getmUserName());
        mHolder.txtScore.setText(Integer.toString(mElement.getmScore()));






        return view;
    }



    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txtPosition = (TextView) v.findViewById(R.id.textViewPos);
        holder.txtName = (TextView) v.findViewById(R.id.textViewName);
        holder.txtScore= (TextView) v.findViewById(R.id.textViewScore);


        return holder;
    }




    private static class ViewHolder {
        public TextView txtPosition;
        public TextView txtName;
        public TextView txtScore;


    }

}
