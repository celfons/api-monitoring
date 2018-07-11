package br.com.zup.api

import br.com.zup.model.LoginResponse
import br.com.zup.model.Token
import br.com.zup.model.LoginRequest
import br.com.zup.model.Profile
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

interface InterfaceApi{

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/token",
            method = [(RequestMethod.GET)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun token(): Token

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/login",
            method = [(RequestMethod.POST)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun login(@RequestBody @Valid loginRequest: LoginRequest): LoginResponse


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/profile",
            method = [(RequestMethod.POST)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun profile(@RequestBody @Valid loginRequest: LoginRequest): Profile

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/find",
            method = [(RequestMethod.GET)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun findStatus() : List<Any>


}
