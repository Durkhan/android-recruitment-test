package com.tasks.androidrecruitmenttesttask.di


import android.app.Application
import com.tasks.androidrecruitmenttesttask.common.Constants.BASE_URL
import com.tasks.androidrecruitmenttesttask.common.Constants.PATH_ENDPOINT
import com.tasks.androidrecruitmenttesttask.data.db.datasource.DataDao
import com.tasks.androidrecruitmenttesttask.data.db.helper.DatabaseHelper
import com.tasks.androidrecruitmenttesttask.data.remote.WebSocketRepository
import com.tasks.androidrecruitmenttesttask.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.net.URI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesSocket():Socket {
        val options = IO.Options().apply {
            path = PATH_ENDPOINT
            transports = arrayOf(WebSocket.NAME)
        }
        val uri = URI.create(BASE_URL)
        return IO.socket(uri, options)
    }

    @Singleton
    @Provides
    fun providesRepository(socket: Socket): WebSocketRepository {
        return WebSocketRepository(socket)
    }

    @Singleton
    @Provides
    fun provideDatabaseHelper(application: Application): DatabaseHelper {
        return DatabaseHelper(application)
    }

    @Singleton
    @Provides
    fun provideDataRepository(dao: DataDao): DataRepository {
        return DataRepository(dao)
    }

    @Singleton
    @Provides
    fun provideDataDao(helper: DatabaseHelper): DataDao {
        return helper.dao()
    }

}