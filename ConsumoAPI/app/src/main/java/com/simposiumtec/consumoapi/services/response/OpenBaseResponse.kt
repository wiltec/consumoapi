package com.simposiumtec.consumoapi.services.response

open class OpenBaseResponse (
    var TotalRecords: String = "",
    var CurrentPage: String = "",
    var TotalPages: String = "",
    var Code: String = "",
    var Message: String = ""
)