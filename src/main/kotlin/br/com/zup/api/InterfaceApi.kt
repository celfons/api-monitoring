package br.com.zup.api

import br.com.zup.model.Customer
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

interface InterfaceApi{

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.GET)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun findAll() : List<Any>

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.POST)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun save(@RequestBody customer: Customer): Customer

}
