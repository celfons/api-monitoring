package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import br.com.zup.response.ResponseApi
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerApi : InterfaceApi {

    override fun test(): ResponseApi {
        return ResponseApi("ok")
    }

}
