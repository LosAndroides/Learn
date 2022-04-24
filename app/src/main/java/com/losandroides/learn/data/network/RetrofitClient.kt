package com.losandroides.learn.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.losandroides.learn.BuildConfig
import com.losandroides.learn.data.network.item.ItemService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class RetrofitClient(private val appContext: Context) {

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/soygabimoreno/" +
                "5a384e6f56499952467c3f9aed332713/raw/9c53f9770f953c600b29541bb7bc412f0c0426ba/"
        private const val HEADER_ACCEPT_LANGUAGE = "Accept-Language"
    }

    val itemService: ItemService by lazy {
        buildRetrofit().create(ItemService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(getHeaderInterceptor())
            .addInterceptor(getChuckerInterceptor())
            .build()
    }

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()
            builder.header(
                HEADER_ACCEPT_LANGUAGE,
                Locale.getDefault().language
            )
            it.proceed(builder.build())
        }
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            } else {
                it.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun getChuckerInterceptor(): ChuckerInterceptor =
        ChuckerInterceptor.Builder(appContext)
            .collector(ChuckerCollector(appContext))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
}
