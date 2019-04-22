package com.example.newsinf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment2 extends Fragment {
    private TextView textView = null;

    /*
     * 選択された項目のインデックスに対する内容をTextViewに表示する
     */
    public void setContentAtIndex(int newIndex) {
        Log.d("fujita","setContentAtIndex_start");
        textView.setText(MainActivity.contentArray[newIndex]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.my_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = (TextView) getActivity().findViewById(R.id.fragment2_textView);
    }
}
