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

//Nous instancions ici des constantes nécessaires à la création de notre notification, leur valeur importe peu pour ce TP.
const val CHANNEL_ID = "com.slickblue.channel_id"
const val notif_1_ID = 1

class MainActivity : AppCompatActivity() {

    //On instancie les variables nécessaires à la construction de notre notification. Leur valeur importe peu.
    val textTitle = "Ma notification"
    val textContent = "Ceci est une notification"
    val channel_name = "channel name"
    val channel_description = "channel description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Nous appelons ici la fonction qui va créer le canal pour notre notification, ceci est indispensable depuis Android 8.0.
        createNotificationChannel()

        //Nous créons ici notre bouton qui va nous permettre de créer et d'afficher notre notification.
        val button1 = findViewById<Button>(R.id.notification_1)

        //Nous ajoutons un listener pour afficher la notification lors d'un appui sur le bouton.
        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                createNotification()
            }
        })
    }

    //Cette fonction se charge de créer et d'afficher la notification.
    private fun createNotification() {
        //Nous créons l'intent qui sera appelée lors d'un appui sur notre notification
        val intent = Intent(this, NotificationDetails::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        //Nous créons ici la notification et l'initialisons avec différentes fonctions et paramètres.
        var mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.heart)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Un très long text qu'il est impossible d'afficher sur une notification normale, nous devons donc l'étendre pour l'afficher. Bla bla bla bla blablablablablablablablablablablablab bla blab blablabablablablalb"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.heart, "ACTION", pendingIntent)
                .setAutoCancel(true)

        //Ici on affiche la notification
        with(NotificationManagerCompat.from(this)) {
            notify(notif_1_ID, mBuilder.build())
        }
    }

    //cette fonction va nous servir à créer un canal pour notre notification, obligatoire depuis Android 8.0
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
}
