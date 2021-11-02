package com.miproyectodeprueba.restapi.web

import com.miproyectodeprueba.restapi.business.ITopicStackFeederBusiness
import com.miproyectodeprueba.restapi.model.TopicStackFeeder
import com.miproyectodeprueba.restapi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Connection

@RestController
@RequestMapping(Constants.URL_BASE_TSF)
class TopicStackFeederController {
    @Autowired
    val topicStackFeederBusiness: ITopicStackFeederBusiness? =null

    @GetMapping("")
    fun list(): ResponseEntity<List<TopicStackFeeder>> {
        return try{
            ResponseEntity(topicStackFeederBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}