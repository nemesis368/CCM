package com.example.quiropracticaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent // Necesario para cambiar de actividad
import androidx.cardview.widget.CardView // Necesario para usar CardView en Kotlin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Llamar a la función que configura los botones
        setupDashboardListeners()
    }

    private fun setupDashboardListeners() {
        // Enlazar la tarjeta del Dashboard usando su ID
        val cardCitas = findViewById<CardView>(R.id.card_citas)

        // Configurar qué pasa cuando el usuario hace clic en la tarjeta
        cardCitas.setOnClickListener {
            // 2. Crear un Intent para ir de MainActivity a AppointmentActivity
            val intent = Intent(this, AppointmentActivity::class.java)

            // 3. Iniciar la nueva actividad
            startActivity(intent)
        }

        // Futuro: Aquí harías lo mismo para la tarjeta de Clientes (card_clientes)
    }
}
