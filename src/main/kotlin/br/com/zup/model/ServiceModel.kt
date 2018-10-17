package br.com.zup.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document(collection="services")
data class ServiceModel(
        @Id
        val id: String? = null,
        val name: String,
        val url: String,
        val method: Method,
        val data: String?,
        val queryParam: Map<String, String>? = mapOf(),
        val headers: Map<String, String>? = mapOf()
){
    enum class Method{
        GET,
        POST,
        PUT,
        DELETE
    }
}
