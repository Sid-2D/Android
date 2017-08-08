package com.example.siddharth.socketio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    val socket = try { IO.socket("http://192.168.1.13:3000") } catch (e: URISyntaxException) { null }
    var textView: TextView? = null
    var send: Button? = null
    var inputText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView) as? TextView
        send = findViewById(R.id.button) as? Button
        inputText = findViewById(R.id.editText) as? EditText
        setSendListener()
        connectSocket()
    }

    fun setSendListener() {
        send?.setOnClickListener {
            val message = inputText?.text
            socket?.emit("appEvent", message)
        }
    }

    fun connectSocket() {
        socket?.on(Socket.EVENT_CONNECT, Emitter.Listener {
            runOnUiThread {
                socket?.emit("appEvent", "hi")
                textView?.text = "Connection Established"
            }
        })
        socket?.on("serverEvent", Emitter.Listener (fun (args){
            runOnUiThread {
                textView?.append("\nMessage Received: " + args[0].toString())
            }
        }))
        socket?.on(Socket.EVENT_DISCONNECT, Emitter.Listener {
            runOnUiThread {
                textView?.text = "Server Disconnected"
            }
        })
        socket?.connect()
    }
}