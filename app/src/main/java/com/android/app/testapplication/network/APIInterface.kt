package com.android.app.testapplication.network

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET(APIConstants.URL_PATH)
    suspend fun getItemsInformation(): List<Response>?
}