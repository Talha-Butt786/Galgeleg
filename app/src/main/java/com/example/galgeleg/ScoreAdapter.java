package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.galgeleg.State_logic.HighScoreList;

// Custom Adapter class to match the listview of my layout.
//https://www.devglan.com/android/create-custom-adapter-in-list-view
public class ScoreAdapter extends BaseAdapter {
    Context context;
    HighScoreList scoreList;

    public ScoreAdapter(Context context, HighScoreList scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }


    @Override
    public int getCount() {
        return scoreList.getScoreslist().size();
    }

    @Override
    public Object getItem(int position) {
        return scoreList.getScoreslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layoutlist,parent,false);
        }
        TextView score = convertView.findViewById(R.id.scoreid);
        TextView dato = convertView.findViewById(R.id.datoid);

        score.setText(String.valueOf(scoreList.getScoreslist().get(position).getPoints()));
        dato.setText(String.valueOf(scoreList.getScoreslist().get(position).getDate()));
        return convertView;
    }
}
