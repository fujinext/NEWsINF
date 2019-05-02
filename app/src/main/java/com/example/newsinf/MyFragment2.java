package com.example.newsinf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFragment2 extends Fragment {
    private TextView textView = null;

//    private EditText editText = null;
    /*
     * 選択された項目のインデックスに対する内容をTextViewに表示する
     */
    public void setContentAtIndex(int newIndex) {
        Log.d("fujita", "setContentAtIndex_start");
        // ダミーデータ使用
//        textView.setText(MainActivity.contentArray[newIndex]);

        // http通信開始
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // ネットワークが使えるかを確認する
        if (networkInfo != null && networkInfo.isConnected()) {
            // ネットワークが使える場合
//            editText.setText("https//www.google.co.jp");
//            String host = editText.getText().toString();
//            String host = "https://www.google.co.jp";
//            String host = "https://weather.yahoo.co.jp/weather/";
            String host = "https://www.nikkansports.com/rss/baseball/professional/atom/dragons.xml";
            // 別スレッドで通信開始
            new HttpResponsAsync().execute(host);
            Log.d("fujita", "after_execute");
        } else {
            // ネットワークが使えない場合
            textView.setText("ネットワークが使えません");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        Log.d("fujita", "MyFragment2_onCreateView_start");
        return inflater.inflate(R.layout.my_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fujita", "MyFragment2_onActivityCreated_start");
        textView = (TextView) getActivity().findViewById(R.id.fragment2_textView);
    }

    private class HttpResponsAsync extends AsyncTask<String, Void, String> {
        // AsyncTask<Params, Progress, Result> 総称型で引数の型を指定する
        // Params:doInBackground Progress:onProgressUpdate Result:onPostExecute
        @Override
        protected void onPreExecute() {
            Log.d("fujita", "onPreExecute_start");
            super.onPreExecute();
            // doInBackground前処理
            textView.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("fujita", "doInBackground_start");
            BufferedReader br = null;
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            StringBuffer stringBuffer = new StringBuffer();

//            URL url2 = null;

            Log.d("fujita", "url=" + url);
            try {
                // urlからHttpUrlConnection,BufferdReaderを作成
                Log.d("fujita", "urlConnection1");
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                // URLの作成
//                url2 = new URL(url);
//                Log.d("fujita", "url2=" + url2);
//                // 接続用HttpURLConnectionオブジェクト作成
//                urlConnection = (HttpURLConnection)url2.openConnection();
//                Log.d("fujita", "urlConnection2");
//                // リクエストメソッドの設定
//                urlConnection.setRequestMethod("POST");
//                // リダイレクトを自動で許可しない設定
//                urlConnection.setInstanceFollowRedirects(false);
//                // URL接続からデータを読み取る場合はtrue
//                urlConnection.setDoInput(true);
//                // URL接続にデータを書き込む場合はtrue
//                urlConnection.setDoOutput(true);
//
//                // 接続
//                urlConnection.connect(); // ①

                // HTTPレスポンスを受信
                String line;
                while ((line = br.readLine()) != null) {
                    Log.d("fujita", "readLine_start");
//                    Log.d("fujita", "line=" + line);
                    stringBuffer.append(line);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                return "接続に失敗しました";
            } finally {
                try {
                    if (br != null) br.close();
                    if (urlConnection != null) urlConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            Log.d("fujita", "stringBuffer.toString()=" + stringBuffer.toString());
            return stringBuffer.toString();
        }

        @Override
        protected void onProgressUpdate(Void... strings) {
            Log.d("fujita", "onProgressUpdate_start");
            super.onProgressUpdate(strings);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("fujita", "onPostExecute_start");
            super.onPostExecute(result);
            // doInBackground後処理
            // 取得した文字列をTextViewに表示する
            textView.setText(result);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
}

