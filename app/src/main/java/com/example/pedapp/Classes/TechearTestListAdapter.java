package com.example.pedapp.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedapp.R;

import java.util.ArrayList;

public class TechearTestListAdapter extends RecyclerView.Adapter<TechearTestListAdapter.ViewHolder> {

    private ArrayList<String> testNames;
    private Context context;

    public TechearTestListAdapter(ArrayList<String> testNames, Context context) {
        this.testNames = testNames;
        this.context = context;
    }

    @NonNull
    @Override
    public TechearTestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_testname, parent, false);
        TechearTestListAdapter.ViewHolder resultViewHolder = new TechearTestListAdapter.ViewHolder(view);
        return resultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TechearTestListAdapter.ViewHolder holder, int position) {
        ((TextView) holder.mTextView.findViewById(R.id.tv_onetestname))
                .setText(testNames.get(position));
    }

    @Override
    public int getItemCount() {
        return testNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTextView =itemView.findViewById(R.id.tv_onetestname);
        }
    }
}
