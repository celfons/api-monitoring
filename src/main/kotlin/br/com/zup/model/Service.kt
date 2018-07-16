package br.com.zup.model

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document(collection="service")
data class Service(
        @Id
        val id: String? = null,
        val name: String,
        val url: String,
        val method: Method,
        val data: JsonNode?
){
    enum class Method{
        GET,
        POST
    }
}