package com.miproyectodeprueba.restapi.business

import com.miproyectodeprueba.restapi.dao.PersonaRepository
import com.miproyectodeprueba.restapi.dao.TopicStackFeederRepository
import com.miproyectodeprueba.restapi.exception.BusinessException
import com.miproyectodeprueba.restapi.model.TopicStackFeeder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopicStackFeederBusiness: ITopicStackFeederBusiness {
    @Autowired //Inyección de dependecias
    val topicStackFeederRepository: TopicStackFeederRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<TopicStackFeeder> {
        try {
            return topicStackFeederRepository!!.findAll()
        }catch (ex:Exception){
            throw BusinessException(ex.message)
        }
    }

    override fun load(idTopicStackFeeder: Int): TopicStackFeeder {
        TODO("Not yet implemented")
    }

    @Throws(BusinessException::class)
    override fun save(topicStackFeeder: TopicStackFeeder): TopicStackFeeder {
        try {
            return topicStackFeederRepository!!.save(topicStackFeeder)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

    }

    override fun remove(idTopicStackFeeder: Int) {
        TODO("Not yet implemented")
    }
}