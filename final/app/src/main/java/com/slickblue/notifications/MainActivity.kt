package com.slickblue.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import android.widget.Button

const val CHANNEL_ID = "com.slickblue.channel_id"
const val notif_1_ID = 1

class MainActivity : AppCompatActivity() {


    var textTitle = "Ma notification"
    var textContent = "Ceci est une notification"
    var channel_name = "channel name"
    var channel_description = "channel description"

    lateinit var mBuilder: NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val intent = Intent(this, NotificationDetails::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.heart)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Un très long text qu'il est impossible d'afficher sur une notification normale, nous devons donc l'étendre pour l'afficher. Bla bla bla bla blablablablablablablablablablablablab bla blab blablabablablablalb"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        val button1 = findViewById<Button>(R.id.notification_1)
        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                showNotification1()
            }
        })
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channel_name
            val descriptionText = channel_description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification1() {
        with(NotificationManagerCompat.from(this)) {
            notify(notif_1_ID, mBuilder.build())
        }
    }
}
