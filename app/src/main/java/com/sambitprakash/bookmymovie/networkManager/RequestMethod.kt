package com.sambitprakash.bookmymovie.networkManager

enum class RequestMethod {
    GET, POST, PUT, PATCH, DELETE
}

//sealed class RequestMethod {
//    class GET : RequestMethod()
//    class POST<Request>(val request: Request) : RequestMethod()
//    class PUT<Request>(val request: Request) : RequestMethod()
//    class PATCH<Request>(val request: Request) : RequestMethod()
//    class DELETE<Request>(val request: Request) : RequestMethod()
//}