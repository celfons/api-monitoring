package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import org.springframework.web.bind.annotation.RestController
import br.com.zup.integration.Integration
import br.com.zup.model.Token
import br.com.zup.repository.TokenMongoRepository

@RestController
class ControllerApi(
        private val integration: Integration,
        private var tokenMongoRepository: TokenMongoRepository
) : InterfaceApi {

    override fun token(): Token {
        return integration.getToken()
    }

    override fun findAll(): List<Any> {
        return tokenMongoRepository.findAll()
    }

}
