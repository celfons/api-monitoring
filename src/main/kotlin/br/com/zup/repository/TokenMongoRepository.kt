package br.com.zup.repository

import br.com.zup.model.Token
import org.springframework.data.mongodb.repository.MongoRepository

interface TokenMongoRepository: MongoRepository<Token, String>
