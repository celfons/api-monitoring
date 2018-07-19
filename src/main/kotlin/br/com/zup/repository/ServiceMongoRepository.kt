package br.com.zup.repository

import br.com.zup.model.Service
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ServiceMongoRepository: MongoRepository<Service, String> {

    @Query("{name:'?0'}")
    fun findServiceByName(name: String): Service?

}
