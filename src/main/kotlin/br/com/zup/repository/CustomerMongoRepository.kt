package br.com.zup.repository

import br.com.zup.model.Customer

import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerMongoRepository: MongoRepository<Customer, String>
