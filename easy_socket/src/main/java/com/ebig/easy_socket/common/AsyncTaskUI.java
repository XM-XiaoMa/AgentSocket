package com.ebig.easy_socket.common;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author Is-Poson
 * @time 2018/5/31  21:58
 * @desc ${TODO}
 */

public abstract class AsyncTaskUI extends AppCompatActivity {

    protected abstract void onPreExecute(WeakReference<AsyncTaskUI> weakUI);

    protected abstract Object doInBackground(WeakReference<AsyncTaskUI> weakUI, Object... objects);

    protected abstract void onPreExecuteTask(WeakReference<AsyncTaskUI> weakUI, Object o);

    protected void onPreExecuteTaskError(WeakReference<AsyncTaskUI> weakUI, Exception e) {
        Toast.makeText(weakUI.get().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    }

    private class ActionTask extends AsyncTask<Object, Void, Object> {

        private int asyncId = -1;

        public void setAcyncId(int asyncId) {
            this.asyncId = asyncId;
        }


        private WeakReference<AsyncTaskUI> weakUI;

        public ActionTask(AsyncTaskUI ui) {
            if (weakUI == null)
                weakUI = new WeakReference<AsyncTaskUI>(ui);
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
