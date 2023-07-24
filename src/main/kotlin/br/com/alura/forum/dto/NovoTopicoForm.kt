package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NovoTopicoForm (
        @field:NotEmpty
        val titulo: String,

        @field:NotEmpty
        val mensagem: String,

        @field:NotNull
        val idCurso: Long,

        @field:NotNull
        val idAutor: Long
)
