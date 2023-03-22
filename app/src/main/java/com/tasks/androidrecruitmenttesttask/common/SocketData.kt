package com.tasks.androidrecruitmenttesttask.common

sealed class SocketData<out T> {
    data class Update<out T>(val value: T) : SocketData<T>()
    data class Connected<out T>(val isConnected:Boolean) : SocketData<T>()
    data class Error(val message: String) : SocketData<Nothing>()
}