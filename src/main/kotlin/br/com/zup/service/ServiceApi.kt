package br.com.zup.service

import br.com.zup.api.Request
import br.com.zup.api.toServiceModel
import br.com.zup.model.ServiceModel
import br.com.zup.model.StatusCode
import br.com.zup.repository.ServiceMongoRepository
import br.com.zup.repository.StatusMongoRepository

class ServiceApi(
        private var serviceMongoRepository: ServiceMongoRepository,
        private var statusMongoRepository: StatusMongoRepository
)  {

    fun createService(request: Request): ServiceModel =
            findServiceByName(request.name)?.let {
                it
            }.let {
                serviceMongoRepository.save(request.toServiceModel())
            }

    fun readService(): MutableList<ServiceModel>? =
            serviceMongoRepository.findAll()

    fun updateService(request: Request): ServiceModel =
            deleteService(request.name).run {
                serviceMongoRepository.save(request.toServiceModel())
            }

    fun deleteService(serviceName: String) {
        findServiceByName(serviceName)?.let {
            serviceMongoRepository.delete(it)
        }
    }

    fun listStatus(): MutableList<StatusCode>? =
            statusMongoRepository.findAll()

    private fun findServiceByName(serviceName: String): ServiceModel? =
            serviceMongoRepository.findServiceByName(serviceName)


}
