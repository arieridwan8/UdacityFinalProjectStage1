package id.arieridwan.jagatcinema.network;

import id.arieridwan.jagatcinema.models.MovieDao;
import id.arieridwan.jagatcinema.models.ReviewDao;
import id.arieridwan.jagatcinema.models.TrailerDao;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by arieridwan on 16/06/2017.
 */

public interface ApiServices {
    @GET("movie/{filter}")
    Observable<MovieDao> getPopularMovie(@Path("filter") String filter,@Query("api_key") String apiKey);
}
