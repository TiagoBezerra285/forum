package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico> = ArrayList(),
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encotrado"
        ) {

    fun listar(): List<TopicoView> {
       return topicos.stream().map {
           t -> topicoViewMapper.map(t)
       }.collect(Collectors.toList())
    }

    fun buscarporId(id: Long): TopicoView {
      val topico = topicos.stream().filter({
          t -> t.id == id
      }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico);
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm) : TopicoView{
        val topico = topicos.stream().filter({
            t -> t.id == form.id
        }).findFirst().get()
        val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                status = topico.status,
                dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter({
            t -> t.id == id
        }).findFirst().get()
        topicos = topicos.minus(topico)
    }
}