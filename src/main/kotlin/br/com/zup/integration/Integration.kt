package br.com.zup.integration

import br.com.zup.model.StatusCode
import br.com.zup.model.Service as ServiceModel
import br.com.zup.model.Token
import br.com.zup.repository.StatusMongoRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import khttp.responses.Response
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import khttp.post as POST
import khttp.get as GET

@Service
class Integration(
        private var statusMongoRepository: StatusMongoRepository
) {

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

    }

}

