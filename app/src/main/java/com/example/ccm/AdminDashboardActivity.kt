package com.example.dentigest

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton // Importar ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val logoutButton: ImageButton = findViewById(R.id.btn_logout_admin)

        logoutButton.setOnClickListener {
            performLogout()
        }
    }

    private fun performLogout() {
        Toast.makeText(this, "Cerrando sesión de Administrador...", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
