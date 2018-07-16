package br.com.zup

import br.com.zup.integration.Integration
import br.com.zup.repository.ServiceMongoRepository

class TimerTask(
        private val integration: Integration,
        private var serviceMongoRepository: ServiceMongoRepository
){

    fun run() {

        val listServices = serviceMongoRepository.findAll()

        listServices.forEach{
            integration.integrationService(it)
        }

        Thread.sleep(600 * 1000)
    }

}