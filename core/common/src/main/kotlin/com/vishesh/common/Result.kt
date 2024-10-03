package com.vishesh.common

sealed class Result<out D> {
    class Success<out D>(
        val data: D,
    ) : Result<D>()

    class Error(
        val message: String,
        val exception: Throwable,
    ) : Result<Nothing>()
}

fun <D> Result<D>.getOr(fallback: D): D =
    if (this is Result.Success) {
        this.data
    } else {
        fallback
    }

inline fun <D> Result<D>.fold(
    onSuccess: (D) -> Unit,
    onError: (String, Throwable) -> Unit,
) {
    when (this) {
        is Result.Success -> onSuccess(this.data)
        is Result.Error -> onError(this.message, this.exception)
    }
}
