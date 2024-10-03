package com.vishesh.network.interceptors

import com.vishesh.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptorImpl : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(newRequestWithAccessToken(BuildConfig.TOKEN, request))
    }

    private fun newRequestWithAccessToken(
        accessToken: String,
        request: Request,
    ): Request =
        request
            .newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}
