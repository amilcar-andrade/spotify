package droid.spotify.data.api;

import droid.spotify.data.model.Envelop;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Models the Spotify  API.
 *
 * docs: https://developer.spotify.com/web-api/
 **/
public interface SpotifyApi {
    String OAUTH_TOKEN = "Bearer BQAryibqAv0xeBcVn64EJSdNgn5JCBRdwtn2CL9QsTCNj5pVpZ74HsD1GYA-HwH_AfFnhEpt2ySkLwkprvO7ut0G-yEkOfcDJ8_pZgz5vgqFsA8t6BzlW0TzDsy23EqVqVNolciYQ3vc3myVn2QCpqkNZkhuHdE";
    String ENDPOINT = "https://api.spotify.com/";

    @GET("v1/search?&type=artist,album")
    @Headers("Authorization: " + OAUTH_TOKEN)
    Call<Envelop> search(@Query("q") String q, @Query("limit") int limit);

    class Factory {
        public static SpotifyApi create(Converter.Factory factory) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(factory)
                    .build();
            return retrofit.create(SpotifyApi.class);
        }
    }
}
