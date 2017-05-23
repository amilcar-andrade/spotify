package droid.spotify.data.api;

import droid.spotify.data.model.Envelop;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Models the Spotify  API.
 *
 * docs: https://developer.spotify.com/web-api/
 **/
interface SpotifyApi {
    String ENDPOINT = "https://api.spotify.com/";

    @GET("v1/search?&type=artist,album")
    Call<Envelop> search(@Query("q") String q, @Query("limit") int limit);
}
