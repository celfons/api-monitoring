package br.com.zup.service

import br.com.zup.api.Request
import br.com.zup.model.ServiceModel as Service
import br.com.zup.model.StatusCode
import br.com.zup.repository.ServiceMongoRepository
import br.com.zup.repository.StatusMongoRepository
import org.springframework.stereotype.Component

@Component
class ServiceApi(
        private var serviceMongoRepository: ServiceMongoRepository,
        private var statusMongoRepository: StatusMongoRepository
)  {

    fun createService(request: Request): Service {
        serviceMongoRepository.findServiceByName(request.name)?.let {
            return it
        }
        val service = toService(request)
        return serviceMongoRepository.save(service)
    }

    fun readService(): MutableList<Service>? {
        return serviceMongoRepository.findAll()
    }

    fun updateService(request: Request): Service {
        serviceMongoRepository.findServiceByName(request.name)?.let {
            serviceMongoRepository.delete(it)
        }
        val service = toService(request)
        return serviceMongoRepository.save(service)
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
