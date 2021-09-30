package br.com.zup.polyana.chave

import br.com.zup.polyana.TipoChave
import br.com.zup.polyana.TipoConta
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Chave(

    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoChave: TipoChave,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    val valorChave: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoConta: TipoConta,

    @field:NotNull
    @Embedded
    val conta: ContaAssociada
){
    @Id
    @GeneratedValue
    val id: UUID? = null

    @Column(nullable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()
}
