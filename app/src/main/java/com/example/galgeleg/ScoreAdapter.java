package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galgeleg.logic.ScoreList;

// Custom Adapter class to match the listview of my layout.
//https://www.devglan.com/android/create-custom-adapter-in-list-view
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewsHolder> {
    Context context;
    ScoreList scoreList;

    public ScoreAdapter(Context context, ScoreList scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }

    @NonNull
    @Override
    public ViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.highscore_layout,parent,false);
        return new ViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewsHolder holder, int position) {
        holder.name.setText(scoreList.getScoreslist().get(holder.getAdapterPosition()).getName());
        holder.position.setText(Integer.toString(holder.getAdapterPosition()+1));
        //holder.date.setText(scoreList.getScoreslist().get(holder.getAdapterPosition()).getDate());
        holder.score.setText(Integer.toString(scoreList.getScoreslist().get(holder.getAdapterPosition()).getPoints()));
        //holder.word.setText(scoreList.getScoreslist().get(holder.getAdapterPosition()).getWord());
        holder.imageView.setImageResource(R.drawable.forkert7);
    }

    @Override
    public int getItemCount() {
        return scoreList.getScoreslist().size();
    }

    public class ViewsHolder extends RecyclerView.ViewHolder {
        TextView name,score,position;
        ImageView imageView;
        public ViewsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_id);
            score = itemView.findViewById(R.id.score_iddd);
            position = itemView.findViewById(R.id.list_position_id);
            //date = itemView.findViewById(R.id.date_id);
            //word = itemView.findViewById(R.id.word_id);
            imageView = itemView.findViewById(R.id.scorelist_imageid);
        }
    }
}
