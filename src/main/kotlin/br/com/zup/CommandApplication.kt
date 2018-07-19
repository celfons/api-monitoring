package br.com.zup

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(
        basePackages = ["br.com.zup"]
)
open class CommandApplication

fun main(args: Array<String>) {

    SpringApplication.run(CommandApplication::class.java, *args)

}
