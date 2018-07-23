package br.com.zup.controller

import br.com.zup.api.InterfaceApi
import br.com.zup.api.Request
import org.springframework.web.bind.annotation.RestController
import br.com.zup.model.Service
import br.com.zup.model.StatusCode
import br.com.zup.repository.ServiceMongoRepository
import br.com.zup.repository.StatusMongoRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RestController
class ControllerApi(
        private var serviceMongoRepository: ServiceMongoRepository,
        private var statusMongoRepository: StatusMongoRepository
) : InterfaceApi {

    override fun createService(@RequestBody @Valid request: Request): Service {
        serviceMongoRepository.findServiceByName(request.name)?.let {
            return it
        }
        val service = toService(request)
        return serviceMongoRepository.save(service)
    }

    override fun readService(): MutableList<Service>? {
        return serviceMongoRepository.findAll()
    }

    override fun updateService(@RequestBody @Valid request: Request): Service {
        serviceMongoRepository.findServiceByName(request.name)?.let {
            serviceMongoRepository.delete(it)
        }
        val service = toService(request)
        return serviceMongoRepository.save(service)
    }

    override fun deleteService(@PathVariable("serviceName") serviceName: String) {
        serviceMongoRepository.findServiceByName(serviceName)?.let {
            serviceMongoRepository.delete(it)
        }
    }

    override fun listStatus(): MutableList<StatusCode>? {
        return statusMongoRepository.findAll()
    }

    private fun toService(request: Request): Service {
        return Service(
                name = request.name,
                url = request.url,
                method = Service.Method.valueOf(request.method.name),
                headers = request.headers,
                queryParam = request.queryParam,
                data = request.data.toString()
        )
    }

}
