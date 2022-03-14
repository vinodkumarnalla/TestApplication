package com.android.app.testapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.testapplication.network.APIConstants
import com.android.app.testapplication.network.APIResponseCallBack
import com.android.app.testapplication.network.NetworkRepositary
import com.android.app.testapplication.network.Response
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val resultData = MutableLiveData<List<Response>>()
    private val errorData = MutableLiveData<String>()
    private val progressLiveData = MutableLiveData<Boolean>()

    fun fetchData() {

        viewModelScope.launch {
            progressLiveData.postValue(true)
            val networkRepositary = NetworkRepositary(getRetrofitObject());
            networkRepositary.fetchData(APIResponse())
        }
    }

    private fun getRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inner class APIResponse : APIResponseCallBack {
        override fun onSuccess(response: List<Response>) {
            progressLiveData.postValue(false)
            resultData.postValue(response)
        }

        override fun onFailure(error: String) {
            progressLiveData.postValue(false)
            errorData.postValue(error)
        }

    }

    fun getResultLiveData():LiveData<List<Response>>{
        return resultData
    }

    fun getProgressLiveData():LiveData<Boolean>{
        return progressLiveData
    }
    fun getErrorLiveData():LiveData<String>{
        return errorData
    }
}