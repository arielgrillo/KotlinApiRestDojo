package com.miproyectodeprueba.restapi.business

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.miproyectodeprueba.restapi.dao.PersonaRepository
import com.miproyectodeprueba.restapi.exception.BusinessException
import com.miproyectodeprueba.restapi.exception.NotFoundException
import com.miproyectodeprueba.restapi.model.Persona
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonaBusiness : IPersonaBusiness {

    @Autowired //Inyección de dependecias
    val personaRepository: PersonaRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Persona> {
        try {
            return personaRepository!!.findAll()
        } catch (ex: Exception) {
            throw BusinessException(ex.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona> //Saber si ese dato está en la base de datos para retornarlo, si no retorna NotFoundException
        try {
            op = personaRepository!!.findById(idPersona)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la persona con id $idPersona")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try {
            sendToTopic(persona)
            return personaRepository!!.save(persona)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun remove(idPersona: Long) {
        val op: Optional<Persona> //Saber si ese dato está en la base de datos para retornarlo, si no retorna NotFoundException
        try {
            op = personaRepository!!.findById(idPersona)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la persona con id $idPersona")
        } else {
            try {
                personaRepository!!.deleteById(idPersona)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    private fun sendToTopic(person: Persona){
        val jsonMapper = ObjectMapper().apply {
            registerKotlinModule()
            findAndRegisterModules()
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
        val personJson = jsonMapper.writeValueAsString(person)

        val producer = createProducer()
        //
        val message = ProducerRecord(
            "priv.motor.carfactory.topicstackfeeder.prueba", // topic
            Math.random().toString(),
            personJson // value
        )
        try {
            producer.send(message)
        }catch (e:Exception){
            println(e.message)
        }

    }
    private fun createProducer(): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = "kafka-greensilence.storage.mpi-internal.com:9094" //cuando está arriba debe llevar el internal kafka-greensilence-internal.storage.mpi-internal.com:9094
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"//StringSerializer::class.java.canonicalName
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"//StringSerializer::class.java.canonicalName
        props["sasl.mechanism"] = "SCRAM-SHA-256"
        props["security.protocol"] = "SASL_SSL"
        props["sasl.jaas.config"] = "org.apache.kafka.common.security.scram.ScramLoginModule required username='ariel.grillo' password='k0hRICuwcZnyfWUogsETjV4BbKg4NrmV';"
        return KafkaProducer<String, String>(props)
    }
}