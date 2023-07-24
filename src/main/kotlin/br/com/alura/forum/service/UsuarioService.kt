package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import java.util.*

class UsuarioService (var usuarios: List<Usuario >){

    init{
        val usuario = Usuario(
                id = 1,
                nome = "Tiago Bezerra",
                email = "tiago@gmail.com"
        )
        usuarios = Arrays.asList(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter({
            c -> c.id == id
        }).findFirst().get()
    }
}