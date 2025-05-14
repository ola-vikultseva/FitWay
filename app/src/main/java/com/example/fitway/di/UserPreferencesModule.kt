package com.example.fitway.di

import com.example.fitway.onboarding.data.UserPreferencesImpl
import com.example.fitway.onboarding.domain.UserPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserPreferencesModule {

    @Singleton
    @Binds
    abstract fun bindUserPreferences(impl: UserPreferencesImpl): UserPreferences
}