package com.sambitprakash.bookmymovie.repositoryFacade.networkRepository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sambitprakash.bookmymovie.utils.internetManager.InternetManager
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import okhttp3.RequestBody.Companion.toRequestBody

class NetworkManager(private val url: String,
                     private val requestMethod: RequestMethod = RequestMethod.GET,
                     val context: Context) {

    fun <RequestData>createRequest(requestData: RequestData) : Request {
        val body = Gson().toJson(requestData)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = body.toRequestBody(mediaType)

        return when (requestMethod) {
            RequestMethod.GET -> Request.Builder().url(url).build()
            RequestMethod.POST -> Request.Builder().url(url).post(requestBody).build()
            RequestMethod.PUT -> Request.Builder().url(url).put(requestBody).build()
            RequestMethod.PATCH -> Request.Builder().url(url).patch(requestBody).build()
            RequestMethod.DELETE -> Request.Builder().url(url).delete(requestBody).build()
        }
    }

    inline fun <RequestData, reified NetworkResponse> makeRequest(requestData: RequestData? = null,
                                                                  crossinline completionHandler: (Result<NetworkResponse, Error>) -> Unit) {
        if (!InternetManager().isNetworkAvailabe(context)) {
            completionHandler(Result.Failure(Error("Internet is not available")))
            return
        }

        val request = createRequest(requestData)
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val jsonResponse = GsonBuilder().create()
                    val movieResponse = jsonResponse.fromJson(body, NetworkResponse::class.java)
                    completionHandler(Result.Success(movieResponse))
                } else {
                    completionHandler(Result.Failure(Error("")))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                completionHandler(Result.Failure(Error(e)))
            }
        })
    }
}