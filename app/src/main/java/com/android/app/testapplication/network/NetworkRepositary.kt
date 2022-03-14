package com.android.app.testapplication.network

import retrofit2.Retrofit

class NetworkRepositary(private val retrofit: Retrofit) {

    private val apiInterface = retrofit.create(APIInterface::class.java)

    suspend fun fetchData(apiResponseCallBack: APIResponseCallBack) {
        try {
            val response = apiInterface.getItemsInformation()
            if (response != null && response.isNotEmpty()) {
                apiResponseCallBack.onSuccess(response)
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
            apiResponseCallBack.onFailure(exp.message ?: "")
        }
    }
}