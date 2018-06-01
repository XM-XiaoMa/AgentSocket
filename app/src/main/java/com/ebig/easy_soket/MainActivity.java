package com.ebig.easy_soket;

import android.os.Bundle;

import com.ebig.easy_socket.common.AsyncTaskUI;

import java.lang.ref.WeakReference;

public class MainActivity extends AsyncTaskUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPreExecute(WeakReference<AsyncTaskUI> weakUI) {

    }

    @Override
    protected Object doInBackground(WeakReference<AsyncTaskUI> weakUI, Object... objects) {
        return null;
    }

    @Override
    protected void onPreExecuteTask(WeakReference<AsyncTaskUI> weakUI, Object o) {

    }
}
