package com.nxnd.travelnote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nxnd.travelnote.R;

/**
 * Created by linSir on 17/3/11.攻略
 */
public class EditFragment extends Fragment {
    private ImageView imageView;
    private EditText edit_title;
    private EditText edit_longtime;
    private EditText edit_location;
    private Button button;
    private Button button_location;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        imageView = view.findViewById(R.id.imageView);
        edit_title = view.findViewById(R.id.edit_title);
        edit_longtime = view.findViewById(R.id.edit_longtime);
        edit_location = view.findViewById(R.id.edit_location);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }



}
