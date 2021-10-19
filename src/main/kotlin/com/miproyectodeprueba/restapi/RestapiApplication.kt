package com.miproyectodeprueba.restapi

import com.miproyectodeprueba.restapi.dao.PersonaRepository
import com.miproyectodeprueba.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication:CommandLineRunner{

	@Autowired
	val personaRepository:PersonaRepository?=null

	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

		val persona1 = Persona(215215254,"Esteban","Quito", LocalDate.parse("25-04-1996",formatter))

		personaRepository!!.save(persona1)
	}

}

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}
