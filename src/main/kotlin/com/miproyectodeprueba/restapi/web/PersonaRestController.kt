package com.miproyectodeprueba.restapi.web

import com.miproyectodeprueba.restapi.business.IPersonaBusiness
import com.miproyectodeprueba.restapi.exception.BusinessException
import com.miproyectodeprueba.restapi.exception.NotFoundException
import com.miproyectodeprueba.restapi.model.Persona
import com.miproyectodeprueba.restapi.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val personaBusiness: IPersonaBusiness?=null

    @GetMapping("")
    fun list():ResponseEntity<List<Persona>>{
        return try{
            ResponseEntity(personaBusiness!!.list(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPersona:Long):ResponseEntity<Any>{
        return try {
            ResponseEntity(personaBusiness!!.load(idPersona),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_PERSONAS+"/"+persona.id) //retorna un header de nombre location con el id de la persona
            ResponseEntity(responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona:Long):ResponseEntity<Any>{
        return try{
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}