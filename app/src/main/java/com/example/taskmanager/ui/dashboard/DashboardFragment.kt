package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDashboardBinding
import com.example.taskmanager.model.Film
import com.example.taskmanager.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveFilm.setOnClickListener {
            val data = Film(binding.etFilm.text.toString(), binding.etDirector.text.toString())
            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
                db.collection(it1)
                    .add(data)
                    .addOnSuccessListener {
                        showToast(getString(R.string.succes_msg))
                        binding.etFilm.text?.clear()
                        binding.etDirector.text?.clear()
                    }
                    .addOnFailureListener {
                        showToast(it.message.toString())
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}