package com.example.ccm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ccm.databinding.FragmentHealthBinding

class HealthFragment : Fragment(R.layout.fragment_health) {

    private var serviceName: String = "Salud"

    companion object {
        fun newInstance(service: String): HealthFragment {
            val fragment = HealthFragment()
            val args = Bundle()
            args.putString("SERVICE_NAME", service)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHealthBinding.bind(view)

        serviceName = arguments?.getString("SERVICE_NAME") ?: "Servicio"
        binding.tvHealthTitle.text = "Agenda: $serviceName"

        var selectedDate = ""
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            selectedDate = "$day/${month + 1}/$year"
        }

        binding.btnAgendar.setOnClickListener {
            val name = binding.etPatientName.text.toString()
            if (selectedDate.isEmpty()) {
                Toast.makeText(context, "Selecciona una fecha", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
                binding.etPatientName.error = "Campo requerido"
            } else {
                Toast.makeText(context, "Cita registrada en $serviceName para el $selectedDate", Toast.LENGTH_LONG).show()
                binding.etPatientName.text?.clear()
            }
        }
    }
}