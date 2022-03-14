package com.android.app.testapplication.network

interface APIResponseCallBack {
    fun onSuccess(response: List<Response>)

    fun onFailure(error: String)
}