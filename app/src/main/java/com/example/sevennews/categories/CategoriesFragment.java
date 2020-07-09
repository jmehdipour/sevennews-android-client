package com.example.sevennews.categories;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sevennews.R;
import com.example.sevennews.base.BaseFragment;

public class CategoriesFragment extends BaseFragment {
    @Override
    public void setupViews() {
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CategoryAdapter(getContext()));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_categories;
    }
}
