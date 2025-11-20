package com.example.ccm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ccm.databinding.FragmentWorkshopBinding

class WorkshopFragment : Fragment(R.layout.fragment_workshop) {

    companion object {
        fun newInstance(name: String, desc: String, schedule: String): WorkshopFragment {
            val f = WorkshopFragment()
            val args = Bundle()
            args.putString("NAME", name)
            args.putString("DESC", desc)
            args.putString("SCH", schedule)
            f.arguments = args
            return f
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentWorkshopBinding.bind(view)

        val name = arguments?.getString("NAME") ?: "Taller"
        binding.tvWorkshopTitle.text = name
        binding.tvDescription.text = arguments?.getString("DESC")
        binding.tvSchedule.text = arguments?.getString("SCH")

        binding.btnInscribir.setOnClickListener {
            Toast.makeText(context, "Inscripción solicitada para $name", Toast.LENGTH_SHORT).show()
        }
    }
}