package br.com.zup.integration

import br.com.zup.model.StatusCode
import br.com.zup.model.Service as ServiceModel
import br.com.zup.repository.StatusMongoRepository
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

        val response = when {
            service.method == ServiceModel.Method.GET ->
                GET(
                    url = service.url,
                    headers = service.headers!!,
                    params = service.queryParam!!,
                    json = JSONObject(service.data)
                )
            else ->
                POST(
                    url = service.url,
                    headers = service.headers!!,
                    params = service.queryParam!!,
                    json = JSONObject(service.data)
                )
        }

        statusMongoRepository.save(StatusCode(response.statusCode, service.name, ZonedDateTime.now().toString()))

    }

}
