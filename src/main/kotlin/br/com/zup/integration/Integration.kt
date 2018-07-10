package br.com.zup.integration

import br.com.zup.model.Token
import br.com.zup.repository.TokenMongoRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import khttp.post as POST

@Service
class Integration(
        private var tokenMongoRepository: TokenMongoRepository
) {

    @Value("\${token}")
    private lateinit var token: String

    fun getToken(): Token{

        val response: Response = POST(
                url = token,
                params = mapOf(
                    Pair("grant_type","client_credentials"),
                    Pair("client_id","887133a1-31ed-498a-885e-a34077b2fa0a"),
                    Pair("client_secret","59ef5595-0231-40a0-a1fc-48245cdcac65")
                )
        )

       val token: Token = jacksonObjectMapper().readValue(response.text)

        return tokenMongoRepository.save(token)

    }

}
