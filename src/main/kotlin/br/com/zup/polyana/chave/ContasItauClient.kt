package br.com.zup.polyana.chave

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client


@Client("\${itau.conta.url}")
interface ContasItauClient {

    @Get("/api/v1/clientes/{clienteId}/contas{?tipo}",
        consumes = [MediaType.APPLICATION_JSON],
        produces = [MediaType.APPLICATION_JSON])
    fun buscaContaByTipo(@PathVariable("clienteId") clienteId: String,
                         @QueryValue("tipo") tipo: String): HttpResponse<DadosContaResponse>
}