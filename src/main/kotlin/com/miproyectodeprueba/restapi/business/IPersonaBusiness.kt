package com.miproyectodeprueba.restapi.business

import com.miproyectodeprueba.restapi.model.Persona

interface IPersonaBusiness {
    fun list():List<Persona>
    fun load(idPersona:Long):Persona
    fun save(persona:Persona):Persona
    fun remove(idPersona:Long)
}