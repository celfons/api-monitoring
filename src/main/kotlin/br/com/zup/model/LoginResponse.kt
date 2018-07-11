package br.com.zup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="login")
data class LoginResponse(
        @Id
        var id: String?,
        var usuario: String?,
        var nome: String?,
        var email: String?
){

    override fun toString(): String{
        return "{usuario= ${usuario}, nome = ${nome}, email = ${email}}"
    }
}