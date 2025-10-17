package com.simposiumtec.consumoapi.services.response

data class UseCfdiResponse(
    var IdUseCfdi: String = "",
    var Description: String = "",
    var CodeUseCfdi: String = "",
    var AppliesPhysicalPerson: String = "",
    var AppliesMoralPerson: String = ""
)