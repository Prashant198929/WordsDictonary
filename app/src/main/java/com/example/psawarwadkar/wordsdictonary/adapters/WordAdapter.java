package com.example.psawarwadkar.wordsdictonary.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psawarwadkar.wordsdictonary.DefinitionActivity;

import com.example.psawarwadkar.wordsdictonary.R;
import com.example.psawarwadkar.wordsdictonary.utilise.DictionaryModel;

import java.util.List;


public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    public List<DictionaryModel> data;

    public WordAdapter(){

    }

    public void setData(List<DictionaryModel> data){
        this.data= data;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.word_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(wordView,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DictionaryModel dictionaryModel = data.get(position);
        holder.wordText.setText(dictionaryModel.getWord());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Context context;
        public TextView wordText;

        public ViewHolder(View itemView, final Context context){

            super(itemView);
            this.context=context;

            wordText = itemView.findViewById(R.id.wordText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    DictionaryModel dictionaryModel = data.get(position);
                    Intent intent = new Intent(context, DefinitionActivity.class);
                    intent.putExtra("WORD", dictionaryModel.getWord());
                    intent.putExtra("DEFINITION", dictionaryModel.getDefinition());

                    context.startActivity(intent);


                }
            });
        }

    }
}
