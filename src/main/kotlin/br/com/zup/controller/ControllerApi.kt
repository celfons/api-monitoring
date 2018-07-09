package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import br.com.zup.repository.CustomerMongoRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerApi(
        private val customerMongoRepository: CustomerMongoRepository
) : InterfaceApi {

    override fun findAll(): List<Any> {
        return customerMongoRepository.findAll()
    }

}
