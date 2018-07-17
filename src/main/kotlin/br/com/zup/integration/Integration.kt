package br.com.zup.integration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import khttp.get as GET

@Service
class Integration {

<<<<<<< Updated upstream
    @Value("\${url}")
    private lateinit var url: String

    fun getAnything(header: String) {
=======
    fun integrationService(service: ServiceModel){

        val response = if(service.method == ServiceModel.Method.GET){

            GET(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")).plus(service.headers!!),
                    params = service.queryParam!!,
                    json = service.data
            )

        }

        else {

            POST(
                    url = service.url,
                    headers = mapOf(Pair("Content-Type", "application/json")).plus(service.headers!!),
                    params = service.queryParam!!,
                    json = service.data
            )

        }

        statusMongoRepository.save(StatusCode(response.statusCode, service.name, ZonedDateTime.now().toString()))
>>>>>>> Stashed changes

        val response = GET(url = url)
    }


}