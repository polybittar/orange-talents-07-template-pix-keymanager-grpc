package br.com.zup.polyana.shared

import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Retention(RUNTIME)
@Target(CLASS, TYPE)
@Around
@Type(ExceptionHandlerInterceptor::class)
annotation class ErrorHandler
