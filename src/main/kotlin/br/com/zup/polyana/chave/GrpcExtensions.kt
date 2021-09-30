package br.com.zup.polyana.chave

import br.com.zup.polyana.PixRequest
import br.com.zup.polyana.TipoConta
import br.com.zup.polyana.TipoChave
import br.com.zup.polyana.chave.validation.TipoChaveEnum
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger(PixRequest::class.java)

fun PixRequest.toModel(): NovaChave {

    logger.info("Convertendo dados de requisição para a classe DTO da chave Pix")

    return NovaChave(
        clienteId = clienteId,
        tipoChave = when (this.tipoChave) {
            TipoChave.CHAVE_DESCOHECIDA -> null
            else -> TipoChaveEnum.valueOf(tipoChave.name)
        },
        valorChave = valorChave,
        tipoConta = when (this.tipoConta) {
            TipoConta.CONTA_DESCONHECIDA -> null
            else -> TipoConta.valueOf(tipoConta.name)
        }
    )
}