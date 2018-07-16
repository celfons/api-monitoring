package br.com.zup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="status")
data class StatusCode(
        val code: Int,
        val service: String,
        val date: String,
        @Id
        val id: String? = null
)
