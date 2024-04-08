package com.tuan04.lab5api.retrofit1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterDelete {
    @FormUrlEncoded
    @POST("delete_product.php")
    Call<SvResponseDelete> deletePrd(@Field("pid") String pid);
}
