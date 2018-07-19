package br.com.zup.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class Token(
        var id: String?,
        var access_token: String?
)
