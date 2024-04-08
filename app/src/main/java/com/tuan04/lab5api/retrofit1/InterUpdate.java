package com.tuan04.lab5api.retrofit1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterUpdate {
    @FormUrlEncoded
    @POST("update_product.php")
    Call<SvResponseUpdate> updatePrd(
            @Field("pid") String pid,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );
}
