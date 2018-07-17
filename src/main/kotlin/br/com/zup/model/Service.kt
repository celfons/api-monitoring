package br.com.zup.model

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document(collection="service")
data class Service(
        @Id
        val id: String?,
        val name: String,
        val url: String,
        val method: Method,
        val data: JsonNode?,
        val queryParam: Map<String, String>? = mapOf()
){
    enum class Method{
        GET,
        POST
    }
}