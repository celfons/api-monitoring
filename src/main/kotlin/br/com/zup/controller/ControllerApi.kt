package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import br.com.zup.api.Request
import br.com.zup.model.ServiceModel
import org.springframework.web.bind.annotation.RestController
import br.com.zup.model.StatusCode
import br.com.zup.service.ServiceApi
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
class ControllerApi(
        private var serviceApi: ServiceApi
): InterfaceApi {

    override fun createService(@RequestBody @Valid request: Request): ServiceModel =
            serviceApi.createService(request)

    override fun readService(): MutableList<ServiceModel>? =
            serviceApi.readService()

    override fun updateService(@RequestBody @Valid request: Request): ServiceModel =
            serviceApi.updateService(request)

    override fun deleteService(@PathVariable("serviceName") serviceName: String) =
            serviceApi.deleteService(serviceName)

    override fun listStatus(): MutableList<StatusCode>? =
            serviceApi.listStatus()

}
