package com.example.galgeleg;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return new ViewsHolder(LayoutInflater.from(context).inflate(R.layout.listofword,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewsHolder holder, final int position) {
        // Retrieve the data for that position
        String word = words.get(position);
        // Add the data to the view
        holder.ord.setText(word);
        holder.ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = words.get(position);
                Toast.makeText(context,word,Toast.LENGTH_SHORT).show();
                Intent spil = new Intent(context,SpilActivity.class);
                spil.putExtra("given_word",word);
                spil.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(spil);

            }
        });

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class ViewsHolder extends RecyclerView.ViewHolder {

        TextView ord;
        public ViewsHolder(@NonNull View itemView) {
            super(itemView);
            ord =itemView.findViewById(R.id.ord_view);
        }

    }
}
