package com.figuredin.controllers.Controller.other

class NetworkStateResource<T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(message: String?, data: T): NetworkStateResource<T?> {
            return NetworkStateResource(
                Status.SUCCESS,
                data,
                message
            )
        }

        fun <T> error(message: String?): NetworkStateResource<T?> {
            return NetworkStateResource(
                Status.ERROR,
                null,
                message
            )
        }

        fun <T> loading(): NetworkStateResource<T?> {
            return NetworkStateResource(
                Status.LOADING,
                null,
                null
            )
        }


    }

}