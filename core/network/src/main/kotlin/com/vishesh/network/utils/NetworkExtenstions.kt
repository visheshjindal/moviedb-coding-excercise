package com.vishesh.network.utils

import com.vishesh.common.Constants
import com.vishesh.common.CoroutineDispatcherProvider
import com.vishesh.common.Result
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T, R> apiCallToResult(
    apiCall: suspend () -> Response<T>,
    transform: suspend (T) -> Result<R>,
    mapError: ((Throwable) -> String)? = null,
    coroutineDispatcher: CoroutineDispatcherProvider,
): Result<R> =
    withContext(coroutineDispatcher.io) {
        runCatching {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body =
                    response.body() ?: throw IllegalStateException(Constants.MALFORMED_DATA_ERROR)
                transform(body)
            } else {
                Result.Error(
                    "${Constants.HTTP_ERROR} ${response.code()} : ${response.message()}",
                    IllegalStateException(),
                )
            }
        }.getOrElse { e ->
            mapError?.let {
                Result.Error(it(e), e)
            } ?: Result.Error(e.message ?: Constants.UNEXPECTED_ERROR, e)
        }
    }
