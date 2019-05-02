package com.example.newsinf;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyFragment1 extends ListFragment {
    private ListSelectionListener listener = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("fujita","MyFragment1_onCreateView_start");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(),"position=" + position, Toast.LENGTH_SHORT).show();

        listener.onListSelection(position);
    }

    // Android 6.0未満の場合、onAttach(Activity activity)しか呼ばれない

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (ListSelectionListener) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("fujita","MyFragment1_onAttach_start");
        listener = (ListSelectionListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fujita","MyFragment1_onActivityCreated_start");
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.my_fragment1_item,MainActivity.titleArray));

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 表示が終わるとここで実行待ちになる

    }
}
