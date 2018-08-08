package br.com.zup.api

import com.fasterxml.jackson.databind.JsonNode

data class Request(
        val name: String,
        val url: String,
        val method: Method,
        val data: JsonNode?,
        val order: Int,
        val queryParam: Map<String, String>? = mapOf(),
        val headers: Map<String, String>? = mapOf()
){
    enum class Method{
        GET,
        POST
    }
}
