package com.example.galgeleg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListordAdapter extends RecyclerView.Adapter<ListordAdapter.ViewsHolder> {

    private ArrayList<String> words;
    private Context context;

    public ListordAdapter(Context context, ArrayList<String> words){
        this.context = context;
        this.words = words;
    }
    @NonNull
    @Override
    public ViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.onlineword_layout,parent,false);
        return new ViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewsHolder holder, final int position) {
        holder.background.setImageResource(R.drawable.word_background);
        holder.position.setText(Integer.toString(holder.getAdapterPosition()));
        String word = words.get(holder.getAdapterPosition());
        holder.ord.setText(word);
        holder.ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = words.get(holder.getAdapterPosition());
                Toast.makeText(context,word,Toast.LENGTH_SHORT).show();
                Intent spil = new Intent(context,SpilActivity.class);
                spil.putExtra("given_word",word);
                spil.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(spil);
                ((ListActivity)context).finish();

            }
        });


    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class ViewsHolder extends RecyclerView.ViewHolder {

        TextView ord,position;
        ImageView imageView,background;
        public ViewsHolder(@NonNull View itemView) {
            super(itemView);
            ord =itemView.findViewById(R.id.onlineOrd_id);
            position = itemView.findViewById(R.id.onlinelist_position_id);
            background = itemView.findViewById(R.id.onlineOrd_imageid);
        }

    }
}
