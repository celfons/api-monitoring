package br.com.zup.api

import br.com.zup.model.ServiceModel
import br.com.zup.model.StatusCode
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.Valid

interface InterfaceApi{

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.POST)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun createService(@RequestBody @Valid request: Request): ServiceModel

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.GET)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun readService(): MutableList<ServiceModel>?

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.PUT)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun updateService(@RequestBody @Valid request: Request): ServiceModel

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/api",
            method = [(RequestMethod.DELETE)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun deleteService(@PathVariable("serviceName") serviceName: String): Unit?

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
            "/status",
            method = [(RequestMethod.GET)],
            produces = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun listStatus(): MutableList<StatusCode>?
}
