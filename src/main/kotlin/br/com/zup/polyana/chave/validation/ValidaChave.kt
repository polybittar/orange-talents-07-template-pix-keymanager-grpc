package br.com.zup.polyana.chave.validation

import br.com.zup.polyana.chave.NovaChave
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidaChaveValidator::class])
@Retention(RUNTIME)
@Target(CLASS, TYPE)
annotation class ValidaChave (
    val message: String = "Não é uma chave Pix (\${validatedValue.tipoChave})",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class ValidaChaveValidator: ConstraintValidator<ValidaChave, NovaChave> {

    override fun isValid(
        value: NovaChave?,
        annotationMetadata: AnnotationValue<ValidaChave>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value?.tipoChave == null)
            return false
        return value.tipoChave.valida(value.valorChave)
    }
}

