package br.com.zup.repository

import br.com.zup.model.StatusCode
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StatusMongoRepository: MongoRepository<StatusCode, String>