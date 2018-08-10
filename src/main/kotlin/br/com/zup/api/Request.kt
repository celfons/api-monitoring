package br.com.zup.api

import br.com.zup.model.ServiceModel
import com.fasterxml.jackson.databind.JsonNode

data class Request(
        val name: String,
        val url: String,
        val method: Method,
        val data: JsonNode?,
        val order: Int,
        val queryParam: Map<String, String>? = mapOf(),
        val headers: Map<String, String>? = mapOf()
) {
    enum class Method {
        GET,
        POST
    }
}

fun Request.toServiceModel(): ServiceModel =
        ServiceModel(
                name = this.name,
                url = this.url,
                method = ServiceModel.Method.valueOf(this.method.name),
                headers = this.headers,
                queryParam = this.queryParam,
                data = this.data.toString()
        )
