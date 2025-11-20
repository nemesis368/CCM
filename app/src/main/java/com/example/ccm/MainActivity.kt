package com.example.ccm

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.ccm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.nav_open, R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            loadFragment(HealthFragment.newInstance("Bienvenida"), "Inicio")
            binding.navView.setCheckedItem(R.id.nav_general) // Opcional: marca uno por defecto
        }

        binding.navView.setNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            var title = ""

            when (item.itemId) {
                // --- SALUD ---
                R.id.nav_dental -> { fragment = HealthFragment.newInstance("Dental"); title = "Dental" }
                R.id.nav_psicologia -> { fragment = HealthFragment.newInstance("Psicología"); title = "Psicología" }
                R.id.nav_general -> { fragment = HealthFragment.newInstance("Medicina General"); title = "General" }
                R.id.nav_quiro -> { fragment = HealthFragment.newInstance("Quiropráctico"); title = "Quiropráctico" }
                R.id.nav_podologia -> { fragment = HealthFragment.newInstance("Podología"); title = "Podología" }
                R.id.nav_fisica -> { fragment = HealthFragment.newInstance("Terapia Física"); title = "Física" }

                // --- TALLERES ---
                R.id.nav_inicial -> {
                    fragment = WorkshopFragment.newInstance("Educación Inicial", "Niños 3-5 años", "Lun-Vie 9AM"); title = "Ed. Inicial"
                }
                R.id.nav_computacion -> {
                    fragment = WorkshopFragment.newInstance("Computación", "Office y Redes", "Mar-Jue 4PM"); title = "Computación"
                }
                R.id.nav_dibujo -> {
                    fragment = WorkshopFragment.newInstance("Dibujo", "Técnicas de arte", "Sábados 10AM"); title = "Dibujo"
                }
                R.id.nav_auxilios -> {
                    fragment = WorkshopFragment.newInstance("Primeros Auxilios", "Certificación RCP", "Intensivo"); title = "P. Auxilios"
                }
            }

            if (fragment != null) {
                loadFragment(fragment, title)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // MANEJO MODERNO DE BOTÓN ATRÁS (ANDROID 13+)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    // Si el drawer está cerrado, volvemos al login
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        supportActionBar?.title = title
    }
}