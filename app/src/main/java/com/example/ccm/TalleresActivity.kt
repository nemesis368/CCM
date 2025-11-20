package com.example.dentigest

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TallerActivity : AppCompatActivity() {

    // 🌟 NUEVA VARIABLE: Se agrega la referencia al botón de menú/salida
    private lateinit var exitMenuButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talleres)

        val recyclerView: RecyclerView = findViewById(R.id.rv_talleres)
        val logoutButton: ImageButton = findViewById(R.id.btn_logout) // Botón de Cerrar Sesión
        exitMenuButton = findViewById(R.id.btn_menu_exit_taller) // 🌟 Encontrar el nuevo botón por ID

        val listaTalleres = crearDatosDeEjemplo()

        val onItemClicked: (Taller) -> Unit = { taller ->
            // En lugar de un Toast, ahora navegaremos a la pantalla de detalles
            navigateToTallerDetail(taller)
        }

        val adapter = TalleresAdapter(listaTalleres, onItemClicked)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Listener para el botón de cerrar sesión
        logoutButton.setOnClickListener {
            performLogout()
        }

        // 🌟 Listener para el botón de SALIR/MENÚ
        exitMenuButton.setOnClickListener {
            closeApplication()
        }
    }

    private fun crearDatosDeEjemplo(): List<Taller> {
        return listOf(
            Taller(1, "Taller de Cocina Saludable", "Aprende recetas fáciles y nutritivas para una sonrisa brillante.", R.drawable.ic_taller_cocina, "Descubre el mundo de la cocina saludable con recetas fáciles y deliciosas que te ayudarán a mantener una boca sana y una sonrisa radiante. Incluye demostraciones prácticas y degustación. ¡No te lo pierdas!", 20),
            Taller(2, "Taller de Dibujo Dental", "Descubre técnicas de dibujo para entender la estructura dental.", R.drawable.ic_taller_dibujo, "Explora la anatomía dental a través del arte del dibujo. Este taller te permitirá visualizar y comprender mejor las estructuras de la boca, mejorando tu conocimiento y precisión. Materiales incluidos.", 15),
            Taller(3, "Taller de Computación Clínica", "Manejo del software clínico y agenda digital.", R.drawable.ic_taller_computacion, "Aprende a usar de forma eficiente el software de gestión clínica, a manejar la agenda digital, y a optimizar los procesos administrativos de tu consultorio. Nivel básico a intermedio.", 10),
            Taller(4, "Taller de Música Relajante", "Cómo la música puede reducir la ansiedad en el consultorio.", R.drawable.ic_taller_musica, "Explora los beneficios de la musicoterapia para crear un ambiente más relajante y confortable en tu clínica dental. Aprende a seleccionar y aplicar música para reducir la ansiedad de tus pacientes.", 25),
            // NUEVO TALLER: Primeros Auxilios Dentales
            Taller(5, "Primeros Auxilios Dentales", "Qué hacer ante una emergencia dental.", R.drawable.ic_first_aid_dental, "Prepárate para cualquier eventualidad. Este taller te enseñará las acciones inmediatas y correctas a tomar ante golpes, fracturas o luxaciones dentales, y cómo estabilizar al paciente antes de la atención profesional. Incluye simulacros prácticos.", 12)
        )
    }

    // Función para navegar a la pantalla de detalles del taller
    private fun navigateToTallerDetail(taller: Taller) {
        val intent = Intent(this, TallerDetailActivity::class.java).apply {
            // Pasar los datos del taller a la nueva Activity
            putExtra("TALLER_ID", taller.id)
            putExtra("TALLER_TITULO", taller.titulo)
            putExtra("TALLER_DESCRIPCION_LARGA", taller.descripcionLarga) // Nueva descripción larga
            putExtra("TALLER_ICONO_RES_ID", taller.iconoResId)
            putExtra("TALLER_CUPO", taller.cupoMaximo) // Nuevo cupo máximo
        }
        startActivity(intent)
    }

    // Función para cerrar sesión
    private fun performLogout() {
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Finaliza esta actividad
    }

    // 🌟 NUEVA FUNCIÓN APLICADA: Cierra completamente la aplicación
    private fun closeApplication() {
        Toast.makeText(this, "Cerrando aplicación...", Toast.LENGTH_SHORT).show()
        // finishAffinity() termina esta actividad y todas las actividades de la tarea, cerrando el proceso.
        finishAffinity()
    }
}
