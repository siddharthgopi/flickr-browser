package com.sidgopi.flickrbrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Photo> photoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        ProcessPhotos processPhotos = new ProcessPhotos();
        processPhotos.execute();


    }

    public class ProcessPhotos extends FlickrJsonData{

        public List<Photo> getPhotoArrayList(){
            return super.getPhotoArrayList();
        }

        public ProcessPhotos(){
            super();
        }

        public void execute(){
            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData{

            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);

                mAdapter = new PhotoAdapter(MainActivity.this, getPhotoArrayList());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }
        }

    }



}


