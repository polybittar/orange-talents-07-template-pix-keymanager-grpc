package br.com.zup.polyana.shared.handler

import br.com.zup.polyana.shared.ExceptionHandler
import br.com.zup.polyana.shared.ExceptionHandler.StatusWithDetails
import io.grpc.Status
import jakarta.inject.Singleton

@Singleton
class ChaveExistenteExceptionHandler : ExceptionHandler<ChaveExistenteException> {

    override fun handle (e: ChaveExistenteException): StatusWithDetails {
        return StatusWithDetails(Status.ALREADY_EXISTS
            .withDescription(e.message)
            .withCause(e))
    }

    override fun supports(e: Exception): Boolean {
        return e is ChaveExistenteException
    }
}