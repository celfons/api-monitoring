package br.com.zup.controller

import br.com.zup.api.InterfaceApi
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

    override fun createService(@RequestBody @Valid service: Service): Service {
        serviceMongoRepository.findServiceByName(service.name)?.let {
            return it
        }
        return serviceMongoRepository.save(service)
    }

    override fun readService(): MutableList<Service>? {
        return serviceMongoRepository.findAll()
    }

    override fun updateService(@RequestBody @Valid service: Service): Service {
        serviceMongoRepository.findServiceByName(service.name)?.let {
            serviceMongoRepository.delete(it)
        }
        return serviceMongoRepository.save(service)
    }

    override fun deleteService(@PathVariable("serviceName") serviceName: String) {
        serviceMongoRepository.delete(serviceName)
    }

    override fun listStatus(): MutableList<StatusCode>? {
        return statusMongoRepository.findAll()
    }

}
