package ru.icecreamru.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.icecreamru.data.repository.NetworkUsersRepository
import ru.icecreamru.data.repository.UsersRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsUsersRepo(
        repository: NetworkUsersRepository,
    ): UsersRepository
}