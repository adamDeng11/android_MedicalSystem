package www.test.com.medical_system.callback;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import www.test.com.medical_system.bean.Heal;

/**
 * Created by adamDeng
 * on 2021-09-22
 */
public interface HealService {

    @GET("health/index")
    Call<Heal> getHealth(@Query("key") String key,@Query("num") int num);


}
