package com.simposiumtec.consumoapi.services.request

data class FindUseCfdiRequest(
    var Description: String = "",
    var CodeUseCfdi: String = "",
    var AppliesPhysicalPerson: String = "",
    var AppliesMoralPerson: String = "",
    var Pagination: PaginationRequest = PaginationRequest()
)