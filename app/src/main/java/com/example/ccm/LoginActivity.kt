package com.example.ccm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.ccm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isAdminMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            Glide.with(this).load(R.drawable.loginn).into(binding.loggonGif)
        } catch (e: Exception) { e.printStackTrace() }

        binding.etUser.doOnTextChanged { _, _, _, _ -> binding.tilUser.error = null }
        binding.etPassword.doOnTextChanged { _, _, _, _ -> binding.tilPassword.error = null }

        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                binding.tilUser.error = null
                binding.tilPassword.error = null
                when (checkedId) {
                    R.id.btnTypeClient -> {
                        isAdminMode = false
                        binding.tvLoginTitle.text = "Acceso Clientes"
                        binding.tilUser.helperText = "Ej: cliente"
                        binding.tvRegisterLink.visibility = View.VISIBLE
                    }
                    R.id.btnTypeAdmin -> {
                        isAdminMode = true
                        binding.tvLoginTitle.text = "Acceso Admin"
                        binding.tilUser.helperText = "Usuario administrativo"
                        binding.tvRegisterLink.visibility = View.GONE
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            // .trim() aquí también es vital
            val inputUser = binding.etUser.text.toString().trim()
            val inputPass = binding.etPassword.text.toString().trim()

            var hayError = false
            if (inputUser.isEmpty()) { binding.tilUser.error = "Requerido"; hayError = true }
            if (inputPass.isEmpty()) { binding.tilPassword.error = "Requerido"; hayError = true }
            if (hayError) return@setOnClickListener

            if (isAdminMode) {
                if (inputUser == "admin" && inputPass == "admin") {
                    startActivity(Intent(this, AdminActivity::class.java)); finish()
                } else {
                    binding.tilPassword.error = "Credenciales incorrectas"
                }
            } else {
                val prefs = getSharedPreferences("MisDatosCCM", Context.MODE_PRIVATE)
                val savedUser = prefs.getString("usuario_guardado", "") ?: ""
                val savedPass = prefs.getString("password_guardado", "") ?: ""

                // --- DIAGNÓSTICO (BORRAR LUEGO) ---
                // Esto te dirá qué tiene el celular guardado en la panza
                // Si sale vacío, el registro falló. Si sale el nombre, estás escribiendo mal la contraseña.
                // Toast.makeText(this, "Memoria: $savedUser / $savedPass", Toast.LENGTH_LONG).show()
                // ----------------------------------

                val esDefault = (inputUser == "cliente" && inputPass == "1234")
                val esNuevo = (inputUser == savedUser && inputPass == savedPass && savedUser.isNotEmpty())

                if (esDefault || esNuevo) {
                    Toast.makeText(this, "Bienvenido $inputUser", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    binding.tilPassword.error = "Usuario o contraseña incorrectos"
                }
            }
        }

        binding.tvRegisterLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}