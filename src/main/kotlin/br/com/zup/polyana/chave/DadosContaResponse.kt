package br.com.zup.polyana.chave

import org.slf4j.LoggerFactory

//json de retorno
data class DadosContaResponse(
    val tipo: String,
    val instituicao: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun toModel(): ContaAssociada {
        logger.info("Convertendo dados de resposta do cliente externo para conta associada a chave pix")

        return ContaAssociada(
            instituicao = this.instituicao.nome,
            nomeTitular = this.titular.nome,
            cpfTitular = this.titular.cpf,
            agencia = this.agencia,
            numeroConta = this.numero
        )
    }
}

data class InstituicaoResponse(val nome: String, val ispb: String)
data class TitularResponse(val nome: String, val cpf: String)
