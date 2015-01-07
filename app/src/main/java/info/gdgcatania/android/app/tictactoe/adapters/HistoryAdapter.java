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

import info.gdgcatania.android.app.tictactoe.api.classes.Result;
import gdgcatania.gabrielebaldoni.devfest.tictactoe.R;

/**
 * @author Gabriele Baldoni (gabriele.baldoni@gmail.com)
 * Created by Gabriele Baldoni on 17/10/14.
 * TicTacToe Example GDG Devfest Mediterranean
 * This class implements the ArrayAdaper for History
 */
public class HistoryAdapter extends BaseAdapter {

    private ArrayList<Result> mResults;
    private Context mContext;

    public HistoryAdapter(ArrayList<Result> mResults, Context mContext) {
        this.mResults = mResults;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mResults !=null)
            return mResults.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(mResults != null)
            return mResults.get(i);

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder mHolder;
        Result mElement = mResults.get(i);
        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        if (view == null) {
            view = vi.inflate(R.layout.history_list_element, null);
            mHolder = createViewHolder(view);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();

        }

        mHolder.txtName.setText(mElement.getEmail());
        mHolder.txtScore.setText(Integer.toString(mElement.getScore()));






        return view;
    }



    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();

        holder.txtName = (TextView) v.findViewById(R.id.textViewNameH);
        holder.txtScore= (TextView) v.findViewById(R.id.textViewScoreH);


        return holder;
    }




    private static class ViewHolder {

        public TextView txtName;
        public TextView txtScore;


    }

}
