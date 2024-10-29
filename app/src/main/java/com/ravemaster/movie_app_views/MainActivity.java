package com.ravemaster.movie_app_views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ravemaster.movie_app_views.listeners.PopularMoviesListener;
import com.ravemaster.movie_app_views.models.PopularMoviesApiResponse;

public class MainActivity extends AppCompatActivity {

    private RequestManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        manager = new RequestManager(this);
    }

    private final PopularMoviesListener popularMoviesListener = new PopularMoviesListener() {
        @Override
        public void onLoading(boolean isLoading) {

        }

        @Override
        public void didFetch(PopularMoviesApiResponse response, String message) {

        }

        @Override
        public void didError(String message) {

        }
    };

}