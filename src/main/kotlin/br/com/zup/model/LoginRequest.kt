package br.com.zup.model

import org.apache.commons.codec.binary.Base64
import org.hibernate.validator.constraints.NotBlank

data class LoginRequest(
        @field:NotBlank var usuario: String? = null,
        @field:NotBlank var senha: String? = null
) {

    fun base64(): LoginRequest {
        return LoginRequest(
                usuario = usuario,
                senha = Base64.encodeBase64(senha?.toByteArray()).toString()
        )
    }
}
