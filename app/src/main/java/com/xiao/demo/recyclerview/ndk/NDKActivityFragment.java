package com.xiao.demo.recyclerview.ndk;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiao.demo.recyclerview.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NDKActivityFragment extends Fragment {

    public NDKActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ndk, container, false);
    }
}
