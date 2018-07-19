package br.com.zup

import br.com.zup.integration.Integration
import br.com.zup.repository.ServiceMongoRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TimerTask(
        private val integration: Integration,
        private var serviceMongoRepository: ServiceMongoRepository
){

    @Scheduled(fixedRate = 36000)
    fun run() {

        val listServices = serviceMongoRepository.findAll().sortedBy { it.order }

        val token = integration.getToken()

        listServices.forEach{
            integration.integrationService(it, token)
        }

    }

}
