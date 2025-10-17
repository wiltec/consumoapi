package com.simposiumtec.consumoapi.services.request

data class PaginationRequest(
    var CurrentPage: String = "",
    var PerPager: String = ""
)