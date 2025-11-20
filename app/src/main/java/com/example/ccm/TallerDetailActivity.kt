package com.example.dentigest

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TallerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taller_detail)

        // Configurar la Toolbar con botón de retroceso
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilitar el botón de "volver"

        // Obtener referencias de las vistas
        val iconImageView: ImageView = findViewById(R.id.iv_detail_icon)
        val titleTextView: TextView = findViewById(R.id.tv_detail_title)
        val descriptionLongTextView: TextView = findViewById(R.id.tv_detail_description_long)
        val cupoTextView: TextView = findViewById(R.id.tv_detail_cupo)
        val inscribirseButton: Button = findViewById(R.id.btn_inscribirse)

        // Obtener los datos del Intent
        val id = intent.getIntExtra("TALLER_ID", -1)
        val titulo = intent.getStringExtra("TALLER_TITULO")
        val descripcionLarga = intent.getStringExtra("TALLER_DESCRIPCION_LARGA")
        val iconoResId = intent.getIntExtra("TALLER_ICONO_RES_ID", R.drawable.ic_launcher_foreground) // Default
        val cupoMaximo = intent.getIntExtra("TALLER_CUPO", 0)

        // Asignar los datos a las vistas
        iconImageView.setImageResource(iconoResId)
        titleTextView.text = titulo
        descriptionLongTextView.text = descripcionLarga
        cupoTextView.text = "Cupo máximo: $cupoMaximo personas"
        supportActionBar?.title = titulo // Pone el título del taller en la Toolbar

        inscribirseButton.setOnClickListener {
            Toast.makeText(this, "Te has inscrito al taller de $titulo", Toast.LENGTH_SHORT).show()
            // Aquí iría la lógica real de inscripción: enviar a un servidor, actualizar base de datos, etc.
        }
    }

    // Manejar el clic en el botón de retroceso de la Toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
