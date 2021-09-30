package br.com.zup.polyana.chave

import br.com.zup.polyana.TipoChave
import br.com.zup.polyana.TipoConta
import br.com.zup.polyana.chave.validation.TipoChaveEnum
import br.com.zup.polyana.chave.validation.ValidaChave
import br.com.zup.polyana.chave.validation.ValidaUUID
import io.micronaut.core.annotation.Introspected
import org.slf4j.LoggerFactory
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidaChave
@Introspected
data class NovaChave(

    @ValidaUUID
    @field:NotBlank
    val clienteId: String,

    @field:NotNull
    val tipoChave: TipoChaveEnum?,

    @field:Size(max = 77)
    val valorChave: String,

    @field:NotNull
    val tipoConta: TipoConta?,

    ) {
        private val logger = LoggerFactory.getLogger(this::class.java)

        fun toModel(conta: ContaAssociada): Chave {

            logger.info("Convertendo a classe DTO para a classe de domínio da chave Pix")
            return Chave(
                clienteId = UUID.fromString(this.clienteId),
                tipoChave = TipoChave.valueOf(this.tipoChave!!.name),
                valorChave = if (this.tipoChave == TipoChaveEnum.CHAVE_ALEATORIA) UUID.randomUUID().toString() else this.valorChave,
                tipoConta = TipoConta.valueOf(this.tipoConta!!.name),
                conta = conta
            )
    }
}
