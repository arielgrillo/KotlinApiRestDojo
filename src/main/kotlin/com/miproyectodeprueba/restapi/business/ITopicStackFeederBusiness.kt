package com.miproyectodeprueba.restapi.business

import com.miproyectodeprueba.restapi.model.TopicStackFeeder

interface ITopicStackFeederBusiness {
    fun list():List<TopicStackFeeder>
    fun load(idTopicStackFeeder:Int): TopicStackFeeder
    fun save(topicStackFeeder: TopicStackFeeder): TopicStackFeeder
    fun remove(idTopicStackFeeder:Int)

}