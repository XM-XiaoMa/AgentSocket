package com.ebig.easy_socket.common;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author Is-Poson
 * @time 2018/6/1  16:35
 * @desc ${TODO}
 */

public abstract class AsyncUI extends AppCompatActivity {

    protected abstract void onPreExecute(WeakReference<AsyncUI> weakUI);

    protected abstract Object doInBackground(WeakReference<AsyncUI> weakUI, Object... objects);

    protected abstract void onPreExecuteTask(WeakReference<AsyncUI> weakUI, Object o);

    protected void onPreExecuteTaskError(WeakReference<AsyncUI> weakUI, Exception e) {
        Toast.makeText(weakUI.get().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    }

    private class ActionTask extends AsyncTask<Object, Void, Object> {

        private int asyncId = -1;

        public void setAcyncId(int asyncId) {
            this.asyncId = asyncId;
        }


        private WeakReference<AsyncUI> weakUI;

        public ActionTask(AsyncUI ui) {
            if (weakUI == null)
                weakUI = new WeakReference<AsyncUI>(ui);
        }

        @Override
        protected void onPreExecute() {
            if (weakUI != null)
                weakUI.get().onPreExecute(weakUI);
        }

        @Override
        protected Object doInBackground(Object... objects) {
            if (weakUI == null)
                return new RuntimeException("try to invoke doInBackground on a null reference");
            if (objects == null)
                return new RuntimeException("try to input null params");
            return weakUI.get().doInBackground(weakUI, objects);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (weakUI == null)
                return;
            if (o instanceof Exception) {
                weakUI.get().onPreExecuteTaskError(weakUI, ((Exception) o));
            } else {
                try {
                    weakUI.get().onPreExecuteTask(weakUI, o);
                } catch (Exception e) {
                    weakUI.get().onPreExecuteTaskError(weakUI, e);
                }
            }

        }
    }

}
