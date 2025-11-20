package com.example.quiropracticaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

/**
 * Actividad responsable de registrar, editar y ver detalles de una cita.
 * Hereda de AppCompatActivity para usar el sistema de Vistas XML.
 */
class AppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Carga el diseño XML de la vista
        // La referencia R.layout.activity_appointment es la correcta.
        setContentView(R.layout.activity_appointment)

        setupSaveButton()
    }

    private fun setupSaveButton() {
        // Enlaza el botón con su ID del XML
        val saveButton: Button = findViewById(R.id.button_save_appointment)

        // Configura el evento de clic
        saveButton.setOnClickListener {

            // 1. Obtener los datos de los campos de texto
            val clientName = findViewById<TextInputEditText>(R.id.edit_text_client_name).text.toString()
            val date = findViewById<TextInputEditText>(R.id.edit_text_date).text.toString()
            val time = findViewById<TextInputEditText>(R.id.edit_text_time).text.toString()
            val notes = findViewById<TextInputEditText>(R.id.edit_text_notes).text.toString()

            // 2. Lógica simple de validación
            if (clientName.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Por favor, completa Nombre, Fecha y Hora.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // 3. Simulación de guardado
            val message = "Cita de $clientName registrada con éxito el $date a las $time."

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            // Si quieres volver al Dashboard después de guardar:
            // finish()
        }
    }
}
