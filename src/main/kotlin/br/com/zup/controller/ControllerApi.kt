package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import org.springframework.web.bind.annotation.RestController
import br.com.zup.integration.Integration
import br.com.zup.model.Token
import br.com.zup.model.LoginRequest
import br.com.zup.model.LoginResponse
import br.com.zup.model.Profile
import br.com.zup.repository.TokenMongoRepository
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
class ControllerApi(
        private val integration: Integration,
        private var tokenMongoRepository: TokenMongoRepository
) : InterfaceApi {

    override fun token(): Token {
        return integration.getToken()
    }

    override fun login(@RequestBody @Valid loginRequest: LoginRequest): LoginResponse {
        return integration.getLogin(loginRequest)
    }

    override fun profile(@RequestBody @Valid loginRequest: LoginRequest): Profile {
        return integration.getProfile(loginRequest)
    }

    override fun findTokens(): List<Any> {
        return tokenMongoRepository.findAll()
    }

}
