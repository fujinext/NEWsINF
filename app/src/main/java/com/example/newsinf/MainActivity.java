package com.example.newsinf;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ListSelectionListener {

    // 表示用文字列格納用の配列
    public static String[] titleArray;
    public static String[] contentArray;

    // Fragmentを作成
    private final MyFragment1 myFragment1 = new MyFragment1();
    private final MyFragment2 myFragment2 = new MyFragment2();

//    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FramentManagerを取得する
//        fragmentManager = getFragmentManager();

        // 表示用文字列を取得
        titleArray = getResources().getStringArray(R.array.Titles);
        contentArray = getResources().getStringArray(R.array.Contents);

        // トランザクションを開始
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // AppCompatActivityのときはこっちだと思う
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // フラグメントを追加
        Log.d("fujita","fragmentTransaction1.add_start");
        fragmentTransaction.add(R.id.fragment, myFragment1);

        // トランザクションを終了（非同期に実行されるため注意）
        Log.d("fujita","fragmentTransaction1.commit_start");
        fragmentTransaction.commit();
    }

    /*
     * 一覧から項が選択された場合の処理
     */
    @Override
    public void onListSelection(int index) {
        // トランザクションを開始
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // AppCompatActivityのときはこっちだと思う
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // MyFragment1をMyFragment2に置き換える
        Log.d("fujita","fragmentTransaction2.replace_start");
        fragmentTransaction.replace(R.id.fragment,myFragment2);

        // 「戻る」ボタンを押したときの処理用にFragmentTransactionをbackstackに追加する
        // 保存するフラグメントが現在のものだけの場合は、引数はnullにする
        Log.d("fujita","fragmentTransaction2.addToBackStack_start");
        fragmentTransaction.addToBackStack(null);

        // トランザクションを終了（非同期に実行されるため注意）
        Log.d("fujita","fragmentTransaction2.commit_start");
        fragmentTransaction.commit();

        // 念のためトランザクションを強制的に実行する
//        fragmentManager.executePendingTransactions();
        getSupportFragmentManager().executePendingTransactions();

        // ここまででonCreateViewとonActivityCreatedが実行される
        // MyFragment2の表示を書き換える
        myFragment2.setContentAtIndex(index);
    }
}
