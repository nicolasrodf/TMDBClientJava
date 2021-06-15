package com.nicolasrodf.tmdbclientjava.ui;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.nicolasrodf.tmdbclientjava.MainActivity;

public class ParentFragment extends Fragment {

    AppCompatActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.activity = (AppCompatActivity) context;
        }
    }

    public AppCompatActivity getCurrentActivity(){
        return this.activity;
    }

    protected MainActivity getMainActivity(){
        if(activity instanceof MainActivity){
            return (MainActivity) activity;
        }
        return null;
    }
}
