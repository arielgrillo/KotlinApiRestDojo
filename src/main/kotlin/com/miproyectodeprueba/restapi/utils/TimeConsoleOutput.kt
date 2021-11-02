package com.miproyectodeprueba.restapi.utils

import com.miproyectodeprueba.restapi.business.ITopicStackFeederBusiness
import com.miproyectodeprueba.restapi.model.TopicStackFeeder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TimeConsoleOutput {
    @Autowired
    val topicStackFeederBusiness: ITopicStackFeederBusiness? =null

    @Scheduled(fixedRate = 1000)
    fun showTime(){
        //println(Date().toString())
        try{
            var tsf = TopicStackFeeder("message","this.is.topic",false,LocalDateTime.now(), LocalDateTime.now())
            topicStackFeederBusiness!!.save(tsf)
        }catch (e:Exception){
            println(e.message)
        }
    }
}