package br.com.zup.service

import br.com.zup.api.Request
import br.com.zup.model.Service
import br.com.zup.model.StatusCode
import br.com.zup.repository.ServiceMongoRepository
import br.com.zup.repository.StatusMongoRepository

class ServiceApi(
        private var serviceMongoRepository: ServiceMongoRepository,
        private var statusMongoRepository: StatusMongoRepository
)  {

    fun createService(request: Request): Service {
        serviceMongoRepository.findServiceByName(request.name)?.let {
            return it
        }
        return serviceMongoRepository.save(toService(request))
    }

    fun readService(): MutableList<Service>? {
        return serviceMongoRepository.findAll()
    }

    fun updateService(request: Request): Service {
        return deleteService(request.name).run {
            serviceMongoRepository.save(toService(request))
        }

    }

    fun deleteService(serviceName: String) {
        serviceMongoRepository.findServiceByName(serviceName)?.let {
            serviceMongoRepository.delete(it)
        }
    }

    fun listStatus(): MutableList<StatusCode>? {
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
