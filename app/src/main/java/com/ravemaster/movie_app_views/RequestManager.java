package com.ravemaster.movie_app_views;

import android.content.Context;

import com.ravemaster.movie_app_views.listeners.PopularMoviesListener;
import com.ravemaster.movie_app_views.models.PopularMoviesApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    public RequestManager(Context context) {
        this.context = context;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getPopularMovies(PopularMoviesListener listener, String category, int page){

        listener.onLoading(true);

        GetPopularMovies getPopularMovies = retrofit.create(GetPopularMovies.class);
        Call<PopularMoviesApiResponse> call = getPopularMovies.getPopularMovies(category,"36ae90fab2c996a1d10808688601db5a",page);
        call.enqueue(new Callback<PopularMoviesApiResponse>() {
            @Override
            public void onResponse(Call<PopularMoviesApiResponse> call, Response<PopularMoviesApiResponse> response) {

                listener.onLoading(false);

                if (!response.isSuccessful()){
                    handleApiError(response,listener);
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<PopularMoviesApiResponse> call, Throwable throwable) {

                listener.onLoading(false);

                listener.didError(throwable.getMessage()+" from onError");
            }
        });
    }

    private void handleApiError(Response<PopularMoviesApiResponse> response, PopularMoviesListener listener) {
        String errorMessage;
        switch (response.code()){
            case 404:
                errorMessage = "Movies not found";
                break;
            case 500:
                errorMessage = "Server error, please try again later";
                break;
            default:
                errorMessage = response.message();
                break;
        }
        listener.didError(errorMessage+" from onResponse");
    }


    private interface GetPopularMovies{
        @GET("movie/{popular}")
        Call<PopularMoviesApiResponse> getPopularMovies(
                @Path("category") String category,
                @Query("api_key") String apiKey,
                @Query("page") int page
        );
    }
}
