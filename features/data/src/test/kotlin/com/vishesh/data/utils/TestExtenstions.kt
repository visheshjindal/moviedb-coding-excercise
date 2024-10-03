package com.vishesh.data.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun <T> createErrorResponse(
    code: Int,
    errorMessage: String,
): Response<T> {
    require(code >= 400) { "Error code must be >= 400: $code" }

    val errorBody =
        errorMessage
            .toResponseBody("application/json".toMediaTypeOrNull())

    val response =
        okhttp3.Response
            .Builder()
            .code(code)
            .message(errorMessage)
            .protocol(Protocol.HTTP_1_1)
            .request(
                Request
                    .Builder()
                    .url("http://localhost/")
                    .build(),
            ).build()

    return Response.error(errorBody, response)
}
