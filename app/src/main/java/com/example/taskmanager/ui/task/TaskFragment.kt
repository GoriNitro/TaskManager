package com.example.taskmanager.ui.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        val task = arguments?.getSerializable(HomeFragment.TASK_UPDATE_KEY) as Task?
        if (task != null) {
            binding.btnSave.text = (getString(R.string.update))
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.desc)
        }
        binding.btnSave.setOnClickListener {

            if (task != null) {
                updateTask()

            } else {
                insertTask()
            }
        }
    }

    private fun updateTask() {
            val task = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        App.db.taskDao().update(task)
        findNavController().navigateUp()
    }

    private fun insertTask() {
        val data =
            Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }
    companion object {
        const val TASK_REQUEST_KEY = "task.request.key"
        const val TASK_KEY = "task.key"
    }
}