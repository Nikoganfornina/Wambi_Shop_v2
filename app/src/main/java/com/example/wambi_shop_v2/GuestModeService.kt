package com.example.wambi_shop_v2

import android.Manifest
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.CountDownTimer
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class GuestModeService : IntentService("GuestModeService") {

    override fun onHandleIntent(intent: Intent?) {
        // Iniciar cuenta regresiva de 30 segundos
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Aquí podrías actualizar el tiempo restante si es necesario
            }

            override fun onFinish() {
                // Mostrar la notificación cuando termine el temporizador
                showNotification()
            }
        }.start()
    }

    private fun showNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Crear el canal de notificación (para Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "guest_mode_channel",
                "Guest Mode Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, "guest_mode_channel")
            .setContentTitle("Modo Invitado Finalizado")
            .setContentText("Acceda con cuenta registrada para poder disfrutar del aplicativo al máximo")
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Utiliza un ícono predeterminado de Android
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Prioridad de la notificación
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no se tienen los permisos, se pueden solicitar aquí.
            return
        }
        NotificationManagerCompat.from(this).notify(1, notification)
    }
}
