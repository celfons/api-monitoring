package br.com.zup

import br.com.zup.integration.Integration
import br.com.zup.repository.ServiceMongoRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(
        basePackages = ["br.com.zup"]
)
open class CommandApplication(
        private val integration: Integration,
        private var serviceMongoRepository: ServiceMongoRepository
){

    @Scheduled(fixedRate = 36000)
    fun run() =

            serviceMongoRepository.findAll().forEach {
                integration.integrationService(it)
            }

}

fun main(args: Array<String>) {

    SpringApplication.run(CommandApplication::class.java, *args)

}
