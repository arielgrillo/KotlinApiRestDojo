package com.miproyectodeprueba.restapi.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "TopicStackFeeder")
data class TopicStackFeeder (
    @Column(name = "Message", nullable = false)
    val Message:String,
    @Column(name = "Topic", nullable = false)
    val Topic:String,
    @Column(name = "ProcessedByJob", nullable = false)
    val ProcessedByJob:Boolean,
    @Column(name = "CreateDate", nullable = false)
    val CreateDate:LocalDateTime,
    @Column(name = "AlterDAte", nullable = false)
    val AlterDAte:LocalDateTime){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var PK_TopicStackFeeder:Int = 0
}