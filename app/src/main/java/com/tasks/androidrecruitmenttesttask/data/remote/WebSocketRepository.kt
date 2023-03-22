package com.tasks.androidrecruitmenttesttask.data.remote

import com.google.gson.*
import com.tasks.androidrecruitmenttesttask.common.SocketData
import com.tasks.androidrecruitmenttesttask.data.model.DataModel
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import io.socket.client.Socket
import io.socket.client.Socket.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class WebSocketRepository @Inject constructor(private var socket: Socket) {

    fun observeWebSocketData():Flow<SocketData<List<DataResponse>>>{
        socket.connect()

        return callbackFlow {
            socket.on(EVENT_CONNECT){
                trySend(SocketData.Connected(true))
            }
            socket.on(EVENT_DISCONNECT) { error ->
                trySend(SocketData.Error(error[0].toString()))
            }
            socket.on(EVENT_CONNECT_ERROR) { error ->
                trySend(SocketData.Error(error[0].toString()))
            }
            socket.on(io.socket.engineio.client.Socket.EVENT_MESSAGE) { data ->
                val result = data[0].toString()
                val dataFromJson = Gson().fromJson(result, DataModel::class.java)
                trySend(SocketData.Update(dataFromJson.result))
            }

            awaitClose { socket.close() }
        }
    }
}

