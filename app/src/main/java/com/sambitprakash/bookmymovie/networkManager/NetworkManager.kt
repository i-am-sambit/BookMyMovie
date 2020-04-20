package com.sambitprakash.bookmymovie.networkManager

import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class NetworkManager(val url: String) {

    inline fun <reified NetworkResponse> makeRequest(crossinline completionHandler: (Result<NetworkResponse, Error>) -> Unit) {
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val jsonResponse = GsonBuilder().create()
                val movieResponse = jsonResponse.fromJson(body, NetworkResponse::class.java)
                completionHandler(Result.Success(movieResponse))
            }

            override fun onFailure(call: Call, e: IOException) {
                completionHandler(Result.Failure(Error(e)))
            }
        })
    }
}