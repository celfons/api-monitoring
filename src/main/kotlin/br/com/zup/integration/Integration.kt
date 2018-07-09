package br.com.zup.integration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import khttp.get as GET

@Service
class Integration {

    @Value("\${url}")
    private lateinit var url: String

    fun getAnything(header: String) {

        val response = GET(url = url)
    }


}