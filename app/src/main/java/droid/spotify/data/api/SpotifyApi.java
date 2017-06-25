package droid.spotify.data.api;

import droid.spotify.data.model.Envelop;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Models the Spotify  API.
 *
 * docs: https://developer.spotify.com/web-api/
 **/
interface SpotifyApi {
    String OAUTH_TOKEN = "Bearer BQAJ6yIpomnE7AUazxAViR9Bs1X0KcI8vx28e1dsMBIdnH57hGigsUDdO_dwFYA8qp-inCmuWn4ByFEebmqRpik8Jien5V0l6m4IJWxAG0H1Aq23GO4vBKCbbcHPzqMP9PSCugJaCNAku1usbXqZxauhTKClYds";
    String ENDPOINT = "https://api.spotify.com/";

    @GET("v1/search?&type=artist,album")
    @Headers("Authorization: " + OAUTH_TOKEN)
    Call<Envelop> search(@Query("q") String q, @Query("limit") int limit);
}
