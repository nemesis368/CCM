package com.example.dentigest

// Modelo de datos para un Taller, ahora con descripción larga y cupo máximo
data class Taller(
    val id: Int,
    val titulo: String,
    val descripcion: String, // Descripción corta para la lista
    val iconoResId: Int,    // ID del recurso (drawable) del icono
    val descripcionLarga: String, // Nueva: Descripción detallada para la pantalla de detalles
    val cupoMaximo: Int         // Nueva: Cupo limitado para el taller
)
