package com.example.ayase.navigationdrawerdemo.dummy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayase.navigationdrawerdemo.EnglishFragment.OnListFragmentInteractionListener;
import com.example.ayase.navigationdrawerdemo.dummy.Glossary.Vocabulary;
import com.example.ayase.navigationdrawerdemo.R;

import java.util.List;

public class GlossaryRecyclerViewAdapter extends RecyclerView.Adapter<GlossaryRecyclerViewAdapter.ViewHolder> {
    private final List<Vocabulary> mGlossary;
    private final OnListFragmentInteractionListener mListener;

    public GlossaryRecyclerViewAdapter(List<Vocabulary> items, OnListFragmentInteractionListener listener) {
        mGlossary = items;
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //get word and its first meaning and the example which the meaning opposite.
        holder.mVocabulary = mGlossary.get(position);
        String word=holder.mVocabulary.word;
        String meaning1=holder.mVocabulary.meaning.get(0);
        String example1=holder.mVocabulary.example.get(meaning1);

        holder.mWordText.setText(word);
        holder.mMeaningText.setText(meaning1);
        holder.mExampleText.setText(example1);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mListener.onListFragmentInteraction(holder.mVocabulary);
            }
        });
    }

    @Override
    public GlossaryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_english, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mGlossary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mWordText;
        public final TextView mMeaningText;
        public final TextView mExampleText;
        public Vocabulary mVocabulary;

        public ViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
            mWordText = (TextView) itemView.findViewById(R.id.english_word);
            mMeaningText = (TextView) itemView.findViewById(R.id.english_meaning);
            mExampleText = (TextView) itemView.findViewById(R.id.english_example);
        }
    }
}
