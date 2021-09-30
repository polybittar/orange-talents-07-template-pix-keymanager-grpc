package br.com.zup.polyana.chave

import br.com.zup.polyana.shared.handler.ChaveExistenteException
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChaveService(
    @Inject val repository: ChaveRepository,
    @Inject val itauClient: ContasItauClient
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun cadastra(@Valid novaChave: NovaChave): Chave {

        if (repository.existsByValorChave(novaChave.valorChave)) {
            logger.warn("Chave existente, lançando exceção")
            throw ChaveExistenteException("Chave Pix '${novaChave.valorChave}' já existe")
        }

        logger.info("Buscando os dados da conta no ERP do Itaú")
        //consulta ao erp itau informando id e tipo de conta do cliente
        val response = itauClient.buscaContaByTipo(novaChave.clienteId!!, novaChave.tipoConta!!.name)
        //quando o response é 404, o body é nulo e a exceção é lançada
        //toModel para converter para conta associada
        val conta = response.body()?.toModel()?: throw IllegalStateException("Cliente não encontrado no Itaú")

        logger.info("Salvando no banco de dados")
        //convertendo novaChave para chave(entidade)
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave
    }

}
