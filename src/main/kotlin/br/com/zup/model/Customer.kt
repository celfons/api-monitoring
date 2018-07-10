package br.com.zup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="customer")
data class Customer(
        @Id
        var id: String?,
        var name: String,
        var age: Int
){

    constructor(): this("", "",0)

    override fun toString(): String{
        return "{id= ${id}, name = ${name}, age = ${age}}"
    }
}
