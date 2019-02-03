package com.example.overflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    public ArrayList<String> questionList = new ArrayList<>();
    public ArrayList<String> upvotes = new ArrayList<>();
    public ArrayList<String> downvotes = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> questions, ArrayList<String> upvotes, ArrayList<String> downvotes, Context context) {
        this.questionList = questions;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = null;
        View view = layoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.username.setText(questionList.get(i));
        viewHolder.votes.setText("Votes: " + upvotes.get(i));
        viewHolder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, questionList.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView username,votes;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tvTitle);
            votes = itemView.findViewById(R.id.tvVotes);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
