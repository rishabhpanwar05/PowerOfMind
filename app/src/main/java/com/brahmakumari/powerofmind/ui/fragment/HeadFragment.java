package com.brahmakumari.powerofmind.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class HeadFragment extends Fragment {
    TextView title;
    public HeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.i("Lat: ","Create");
        View v=inflater.inflate(R.layout.fragment_head, container, false);
        title = (TextView) v.findViewById(R.id.headTextview);
        //setTitleText("some text");
        return v;
    }


}
