package com.internship.mts.internproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseFragment;
import com.internship.mts.internproject.databinding.CommentFragmentBinding;
import com.internship.mts.internproject.network.model.Comment;

import java.util.ArrayList;

public class CommentFragment extends BaseFragment {

    private CommentFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.comment_fragment, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.commentFragmentRecyclerView.setLayoutManager(layoutManager);

        binding.commentFragmentRecyclerView.setAdapter(new CommentAdapter(new ArrayList<Comment>()));
        return binding.getRoot();
    }

    public void addComment(Comment comment) {
        ((CommentAdapter) binding.commentFragmentRecyclerView.getAdapter()).addItemToHead(comment);
    }

    public void sendComment(Comment comment) {
        ((CommentAdapter) binding.commentFragmentRecyclerView.getAdapter()).addItemToHead(comment);
        binding.commentFragmentRecyclerView.smoothScrollToPosition(0);
    }
}
