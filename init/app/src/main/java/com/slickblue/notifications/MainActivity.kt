package com.slickblue.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat

const val CHANNEL_ID = "com.slickblue.channel_id"
const val notif_1_ID = 1

class MainActivity : AppCompatActivity() {

    //On instancie les variables nécessaires à la construction de notre notification. Leur valeur importe peu.
    var textTitle = "Ma notification"
    var textContent = "Ceci est une notification"
    var channel_name = "channel name"
    var channel_description = "channel description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
