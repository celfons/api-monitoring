package br.com.zup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="token")
data class Token(
        @Id
        var id: String?,
        var access_token: String?,
        var token_type: String?,
        var expires_in: String?
){

    override fun toString(): String{
        return "{access_token= ${access_token}, token_type = ${token_type}, expires_in = ${expires_in}}"
    }
}