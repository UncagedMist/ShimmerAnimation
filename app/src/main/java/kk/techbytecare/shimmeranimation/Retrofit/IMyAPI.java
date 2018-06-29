package kk.techbytecare.shimmeranimation.Retrofit;

import java.util.List;

import io.reactivex.Observable;
import kk.techbytecare.shimmeranimation.Model.Model;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("photos")
    Observable<List<Model>> getData();
}
