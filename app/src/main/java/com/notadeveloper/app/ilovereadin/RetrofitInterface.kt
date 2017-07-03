package com.notadeveloper.app.ilovereadin

import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by krsnv on 03-Jul-17.
 */
interface RetrofitInterface {
    @FormUrlEncoded
    @POST("api")
    fun authUser(@Field("user") user: String, @Field("pass") pass: String, @Field("type") type: String = "get_memid", @Field("public_key") public_key: String = "qX6kXT15Iq", @Field("hash") hash: String = "4bd1af700fcc6d2dde5e6ab13133fc568d9b7644"): Observable<Result>

    companion object Factory {
        fun create(): RetrofitInterface {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create().asLenient())
                    .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
                    .baseUrl("http://iloveread.in/")
                    .build()

            return retrofit.create(RetrofitInterface::class.java)
//        USAGE
//        val apiService = RetrofitInterface.create()
//        apiService.search(/* search params go in here */)
        }
    }

}

