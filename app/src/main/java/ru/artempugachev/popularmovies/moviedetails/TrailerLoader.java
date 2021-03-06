//package ru.artempugachev.popularmovies.loader;
//
//import android.content.Context;
//import android.support.v4.content.Loader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import ru.artempugachev.popularmovies.BuildConfig;
//import ru.artempugachev.popularmovies.moviedetails.api.Video;
//import ru.artempugachev.popularmovies.moviedetails.api.VideoResponse;
//import ru.artempugachev.popularmovies.tmdb.TmdbApiClient;
//import ru.artempugachev.popularmovies.tmdb.TmdbApiInterface;
//
///**
// * Load trailers
// */
//
//public class TrailerLoader extends Loader<List<Video>> {
//    /**
//     * Stores away the application context associated with context.
//     * Since Loaders can be used across multiple activities it's dangerous to
//     * store the context directly; always use {@link #getContext()} to retrieve
//     * the Loader's Context, don't use the constructor argument directly.
//     * The Context returned by {@link #getContext} is safe to use across
//     * Activity instances.
//     *
//     * @param context used to retrieve the application context.
//     */
//
//    private List<Video> trailers;
//    private String movieId;
//
//    public TrailerLoader(Context context, String movieId) {
//        super(context);
//        this.movieId = movieId;
//    }
//
//    @Override
//    protected void onStartLoading() {
//        if (trailers != null) {
//            deliverResult(trailers);
//        } else {
//            forceLoad();
//        }
//    }
//
//    @Override
//    public void deliverResult(List<Video> data) {
//        trailers = data;
//        super.deliverResult(trailers);
//    }
//
//
//    @Override
//    protected void onForceLoad() {
//        TmdbApiClient tmdbApiClient = new TmdbApiClient();
//        TmdbApiInterface tmdbApiInterface = tmdbApiClient.buildApiInterface();
//
//        if (movieId != null) {
//
//            Call<VideoResponse> call = tmdbApiInterface.getVideos(movieId, BuildConfig.TMDB_API_KEY);
//
//            if (call != null) {
//                call.enqueue(new Callback<VideoResponse>() {
//                    @Override
//                    public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
//                        if (response.isSuccessful()) {
//                            List<Video> videos = response.body().getResults();
//
//                            // not every video is trailer, so filter them
//                            List<Video> trailers = new ArrayList<Video>();
//                            for (Video video : videos) {
//                                if (video.isTrailer()) trailers.add(video);
//                            }
//
//                            deliverResult(trailers);
//                        } else {
//                            deliverResult(null);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<VideoResponse> call, Throwable throwable) {
//                        deliverResult(null);
//                    }
//
//                });
//            }
//        } else {
//            deliverResult(null);
//        }
//
//        super.onForceLoad();
//    }
//}
