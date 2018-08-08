package br.com.zup.repository

import br.com.zup.model.ServiceModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ServiceMongoRepository: MongoRepository<ServiceModel, String> {

    @Query("{name:'?0'}")
    fun findServiceByName(name: String): ServiceModel?

}
