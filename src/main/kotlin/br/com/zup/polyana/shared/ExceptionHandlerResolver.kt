package br.com.zup.polyana.shared

import br.com.zup.polyana.shared.handler.DefaultExceptionHandler
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
//busca o handler adequado para uma exceção
class ExceptionHandlerResolver(
    @Inject private val handlers: List<ExceptionHandler<*>>,
) {

    private var defaultHandler: ExceptionHandler<Exception> = DefaultExceptionHandler()

    fun resolve(e: Exception): ExceptionHandler<*> {
        val procuraHandlers = handlers.filter { it.supports(e) }

        if (procuraHandlers.size > 1)
            throw IllegalStateException("Muitos handlers suportam a mesma exception '${e.javaClass.name}': $procuraHandlers")

        return procuraHandlers.firstOrNull() ?: defaultHandler
    }

}