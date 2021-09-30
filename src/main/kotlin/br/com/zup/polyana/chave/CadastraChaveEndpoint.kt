package br.com.zup.polyana.chave

import br.com.zup.polyana.*
import br.com.zup.polyana.shared.ErrorHandler
import io.grpc.stub.StreamObserver
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@ErrorHandler           //interceptar o método
@Singleton
class CadastraChaveEndpoint(@Inject private val service: NovaChaveService):
    PixKeymanagerGrpcServiceGrpc.PixKeymanagerGrpcServiceImplBase() {


    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun cadastrar(
        request: PixRequest,
        responseObserver: StreamObserver<PixResponse>
    ) {

        val novaChave = request.toModel()                         //transforma a requisição em um dto
        val chaveCriada = service.cadastra(novaChave)             //realiza validações e grava a chave no banco

        logger.info("Montando resposta")
        val response = PixResponse.newBuilder()
            .setClienteId(chaveCriada.clienteId.toString())
            .setPixId(chaveCriada.id.toString())
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}