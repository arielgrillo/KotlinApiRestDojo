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

    override fun save(topicStackFeeder: TopicStackFeeder): TopicStackFeeder {
        TODO("Not yet implemented")
    }

    override fun remove(idTopicStackFeeder: Int) {
        TODO("Not yet implemented")
    }
}