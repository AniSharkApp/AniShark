package ru.anishark.app.data.di.annotation

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Remote

@Qualifier
@Retention(RUNTIME)
annotation class Local

@Qualifier
@Retention(RUNTIME)
annotation class Mock
