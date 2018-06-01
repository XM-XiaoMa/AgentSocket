package com.ebig.easy_soket;

import android.os.Bundle;

import com.ebig.easy_socket.common.AsyncUI;

import java.lang.ref.WeakReference;

public class MainActivity extends AsyncUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPreExecute(WeakReference<AsyncUI> weakUI) {

    }

    @Override
    protected Object doInBackground(WeakReference<AsyncUI> weakUI, Object... objects) {
        return null;
    }

    @Override
    protected void onPreExecuteTask(WeakReference<AsyncUI> weakUI, Object o) {

    }
}
