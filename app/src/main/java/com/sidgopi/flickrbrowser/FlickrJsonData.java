package com.sidgopi.flickrbrowser;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CouponDunia on 22/06/16.
 */
public class FlickrJsonData extends GetRawData {

    private List<Photo> photoArrayList;
    private String searchCriteria;
    private String matchCriteria;
    private Context context;
    private String finalURL;


    public List<Photo> getPhotoArrayList() {
        return photoArrayList;
    }

    public FlickrJsonData(){
        super(null);
        createURL();
        photoArrayList = new ArrayList<Photo>();
        Log.d("final url", finalURL);
    }


    public void jsonProcess(String in) throws JSONException {

        String title;
        String link;
        String author;
        String tags;
        String imageLocation;
        String description;
        JSONObject media;
        Photo photo;


        JSONObject reader = new JSONObject(in);
        JSONArray items  = reader.optJSONArray("items");


        for(int i=0; i < items.length(); i++){
            JSONObject jsonObject = items.getJSONObject(i);

            title = jsonObject.optString("title");
            link = jsonObject.optString("link");
            author = jsonObject.optString("author");
            tags = jsonObject.optString("tags");
            media = jsonObject.getJSONObject("media");
            imageLocation = media.optString("m");
            description = jsonObject.optString("description");

            photo = new Photo(title, link, author, tags, imageLocation, description);

            Log.d("lensgth", photo.toString());
            this.photoArrayList.add(photo);
            Log.d("length", photoArrayList.toString());
            Log.d("length", String.valueOf(photoArrayList.size()));



        }
    }


    public void createURL(){
        final String BASE_URL =
                "https://api.flickr.com/services/feeds/photos_public.gne?";
        final String FORMAT = "format";
        final String NO_JSON_CALLBACK = "nojsoncallback";
        final String TAGS = "tags";

        Uri urlBuild = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(NO_JSON_CALLBACK, "1")
                .appendQueryParameter(TAGS, "apple")
                .build();

        finalURL = urlBuild.toString();
        return;
    }


    public class DownloadJsonData extends DownloadData{

        protected void onPostExecute(String webData) {
            try {
                jsonProcess(webData);
                Log.d("SON PROCESS ON POST EXE", "done");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(webData);
        }
        @Override
        protected String doInBackground(String... params) {
            String[] par = {finalURL};
            return super.doInBackground(par);
        }
    }

}
