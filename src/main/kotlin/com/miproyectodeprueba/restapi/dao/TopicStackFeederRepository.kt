package com.miproyectodeprueba.restapi.dao

import com.miproyectodeprueba.restapi.model.TopicStackFeeder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicStackFeederRepository :JpaRepository<TopicStackFeeder,Int>