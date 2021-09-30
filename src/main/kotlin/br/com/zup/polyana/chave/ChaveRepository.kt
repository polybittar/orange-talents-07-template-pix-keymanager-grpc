package br.com.zup.polyana.chave

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChaveRepository: JpaRepository<Chave, UUID> {
    fun existsByValorChave(valorChave: String?): Boolean
}
