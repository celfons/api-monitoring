package br.com.zup.integration

import br.com.zup.model.ServiceModel.Method.*
import br.com.zup.model.StatusCode
import br.com.zup.repository.StatusMongoRepository
import khttp.responses.Response
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import br.com.zup.model.ServiceModel
import khttp.get as GET
import khttp.post as POST
import khttp.put as PUT
import khttp.delete as DELETE

@Service
class Integration(
        private var statusMongoRepository: StatusMongoRepository
) {

    fun integrationService(service: ServiceModel) =
            when (service.method) {
                GET -> requestGet(service).statusCode
                POST -> requestPost(service).statusCode
                PUT -> requestPut(service).statusCode
                else -> requestDelete(service).statusCode
            }.also {
                statusMongoRepository.save(StatusCode(it, service.name, ZonedDateTime.now().toString()))
            }

    private fun requestGet(service: ServiceModel): Response =
            GET(
                url = service.url,
                headers = service.headers!!,
                params = service.queryParam!!,
                json = JSONObject(service.data)
            )

    private fun requestPost(service: ServiceModel): Response =
            POST(
                url = service.url,
                headers = service.headers!!,
                params = service.queryParam!!,
                json = JSONObject(service.data)
            )

    private fun requestPut(service: ServiceModel): Response =
            PUT(
                url = service.url,
                headers = service.headers!!,
                params = service.queryParam!!,
                json = JSONObject(service.data)
            )

    private fun requestDelete(service: ServiceModel): Response =
            DELETE(
                url = service.url,
                headers = service.headers!!,
                params = service.queryParam!!,
                json = JSONObject(service.data)
            )

}
