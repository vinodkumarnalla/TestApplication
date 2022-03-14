package com.android.app.testapplication.network

import java.io.Serializable


data class Response(

    var name: String? = null,
    var description: String? = null,
    var image: String? = null

): Serializable