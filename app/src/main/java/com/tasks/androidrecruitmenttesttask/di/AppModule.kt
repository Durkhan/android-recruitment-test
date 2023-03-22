package com.tasks.androidrecruitmenttesttask.di


import com.tasks.androidrecruitmenttesttask.common.Constants.BASE_URL
import com.tasks.androidrecruitmenttesttask.common.Constants.PATH_ENDPOINT
import com.tasks.androidrecruitmenttesttask.data.remote.WebSocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesSocket():Socket {
        val options = IO.Options().apply { path = PATH_ENDPOINT }
        val uri = URI.create(BASE_URL)
        return IO.socket(uri, options)
    }

    @Singleton
    @Provides
    fun providesRepository(socket: Socket): WebSocketRepository {
        return WebSocketRepository(socket)
    }

}