package co.rysr.rysr.API;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


public interface BraintreeService {

    @GET("/client_token")
    Call<ResponseBody> getClientToken();

    @FormUrlEncoded
    @POST("/checkout")
    Call<ResponseBody> checkout(@Field("payment_method_nonce") String nonce);

    @FormUrlEncoded
    @POST("/donate")
    Call<ResponseBody> donate(@Field("payment_method_nonce") String nonce, @Field("amount") float amount);

}