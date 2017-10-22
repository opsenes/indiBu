package com.internship.mts.internproject.view;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseAdapter;
import com.internship.mts.internproject.databinding.CommentItemBinding;
import com.internship.mts.internproject.network.model.Comment;
import com.internship.mts.internproject.viewmodel.CommentViewModel;

import java.util.List;

public class CommentAdapter extends BaseAdapter<Comment, CommentItemBinding> {

    public CommentAdapter(List<Comment> commentList) {
        super(commentList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comment_item;
    }

    @Override
    protected void onBindViewHolder(CommentItemBinding binding, Comment item, int position) {
        binding.setCommentVM(new CommentViewModel(item));
    }
}
