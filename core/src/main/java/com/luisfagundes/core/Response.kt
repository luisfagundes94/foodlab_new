package com.luisfagundes.core

sealed class Response<T> {

    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val exception: Exception) : Response<T>()

    val isSuccess get() = this is Success<T>
    val isError get() = this is Error<T>

    fun fold(onSuccess: (T) -> Any, onError: (Exception) -> Any): Any =
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(exception)
        }

    companion object {
        suspend fun <V : Any> of(suspendFunction: suspend () -> V): Response<V> = try {
            val value = suspendFunction()
            Success(value)
        } catch (exception: Exception) {
            Error(exception)
        }

        suspend fun <V> listOf(suspendFunction: suspend () -> List<V>): Response<List<V>> = try {
            val value = suspendFunction()
            Success(value)
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}