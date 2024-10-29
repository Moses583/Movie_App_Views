package com.ravemaster.movie_app_views.listeners;

import com.ravemaster.movie_app_views.models.PopularMoviesApiResponse;

public interface PopularMoviesListener {
    void onLoading(boolean isLoading);
    void didFetch(PopularMoviesApiResponse response, String message);
    void didError(String message);
}
