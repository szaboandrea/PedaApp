package com.example.pedapp.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedapp.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    Context context;
    List<Test> tests;

    public TestAdapter(Context context, List<Test> tests) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_question, parent, false);
        TestAdapter.ViewHolder resultViewHolder = new TestAdapter.ViewHolder(view);
        return resultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        ((TextView) holder.cardView.findViewById(R.id.tv_question))
                .setText(tests.get(position).getQuestion());
        ((RadioButton) holder.cardView.findViewById(R.id.radioButtonAnswear1))
                .setText(tests.get(position).getAnswear1());
        ((RadioButton) holder.cardView.findViewById(R.id.radioButtonAnswear2))
                .setText(tests.get(position).getAnswear2());
        ((RadioButton) holder.cardView.findViewById(R.id.radioButtonAnswear3))
                .setText(tests.get(position).getAnswear3());
        ((RadioButton) holder.cardView.findViewById(R.id.radioButtonAnswear4))
                .setText(tests.get(position).getAnswear4());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardViewMovie);
        }
    }
}
