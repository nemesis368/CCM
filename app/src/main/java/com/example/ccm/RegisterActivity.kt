package com.example.ccm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ccm.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirmRegister.setOnClickListener {
            // .trim() elimina espacios al inicio y al final por accidente
            val name = binding.etRegName.text.toString().trim()
            val user = binding.etRegUser.text.toString().trim()
            val pass = binding.etRegPassword.text.toString().trim()

            if (name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val prefs = getSharedPreferences("MisDatosCCM", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("usuario_guardado", user)
                editor.putString("password_guardado", pass)

                // USAMOS commit() AQUÍ: Es síncrono y nos asegura que se guardó antes de cambiar de pantalla
                val guardadoExitoso = editor.commit()

                if (guardadoExitoso) {
                    Toast.makeText(this, "Usuario guardado: $user", Toast.LENGTH_LONG).show()

                    // Regresar al Login
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}