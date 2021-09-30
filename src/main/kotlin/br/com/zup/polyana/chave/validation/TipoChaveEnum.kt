package br.com.zup.polyana.chave.validation

import io.micronaut.validation.validator.constraints.*
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator


enum class TipoChaveEnum {
    CPF{
        override fun valida(valorChave: String?): Boolean {
            if(valorChave.isNullOrBlank()) {
                return false
            }
            if (!valorChave.matches("[0-9]+".toRegex())) {
                return false
            }
            return CPFValidator().run {
                initialize(null)
                isValid(valorChave, null)
            }
        }
    },

    TELEFONE_CELULAR{
        override fun valida(valorChave: String?): Boolean {
            if(valorChave.isNullOrBlank()) {
                return false
            }
            return valorChave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },

    EMAIL{
        override fun valida(valorChave: String?): Boolean {
            if(valorChave.isNullOrBlank()) {
                return false
            }
            return EmailValidator().run {
                initialize(null)
                isValid(valorChave, null)
            }
        }
    },

    CHAVE_ALEATORIA{
        override fun valida(valorChave: String?) = valorChave.isNullOrBlank()
    };

    abstract fun valida(valorChave: String?): Boolean
}

