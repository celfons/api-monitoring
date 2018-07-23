package br.com.zup.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
data class Token(
        var access_token: String?,
        var token_type: String?,
        var expires_in: Int?
)
