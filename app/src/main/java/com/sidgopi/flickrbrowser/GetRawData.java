package com.sidgopi.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by CouponDunia on 22/06/16.
 */

public class GetRawData {
    private String mRawUrl;
    private String mRawData;

    public void setmRawData(String mRawData) {
        this.mRawData = mRawData;
    }

    public String getmRawData() {
        return mRawData;
    }

    public String getmRawUrl() {
        return mRawUrl;
    }

    public void setmRawUrl(String mRawUrl) {
        this.mRawUrl = mRawUrl;
    }

    public GetRawData(String mRawUrl) {
        this.mRawUrl = mRawUrl;
        this.mRawData = mRawData;
    }

    public void execute() {
        DownloadData downloadRawData = new DownloadData();
        downloadRawData.execute(mRawUrl);
    }

    public class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection httpURLConnection = null;
            BufferedReader reader;

            if (params == null) {
                return null;
            }

            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //            httpURLConnection.setRequestMethod("GET");
                //            httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                String result = builder.toString();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String webData) {
            mRawData = webData;
            Log.d("super-on post exec", webData);

        }
    }
}

