package com.example.sevennews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevennews.MainActivity;
import com.example.sevennews.R;

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null){
            rootView = inflater.inflate(getLayoutRes(),container,false);
        }

        setupViews();

        return rootView;
    }

    public abstract void setupViews();

    public abstract int getLayoutRes();

    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }
}
