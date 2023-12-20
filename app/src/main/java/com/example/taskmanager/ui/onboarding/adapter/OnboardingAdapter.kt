package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemVpBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick:()->Unit)
    : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        OnBoarding("https://www.shutterstock.com/image-illustration/convenient-word-on-ring-around-260nw-208554790.jpg","Удобство","при переводах и платежах"),
        OnBoarding("https://math-media.byjusfutureschool.com/bfs-math/2022/05/27171640/wall-office-clock-with-black-red-hands-white-dial_1284-8992.webp","Экономия времени","Оплачивайте в 2 клика"),
        OnBoarding("https://media.istockphoto.com/id/1423854750/vector/industrial-worker-holding-safety-first-sign-engineer-with-his-personal-protective-equipment.jpg?s=612x612&w=0&k=20&c=g1Dk752yoyWfapIRDlp8YUxKPrOdEAB8MNOAhBRbz04=","Безопасность","Без рисков и затрат"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemVpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemVpBinding) :
        ViewHolder(binding.root) {

        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text=onBoarding.desc
            Glide.with(imgBoard).load(onBoarding.image).into(imgBoard)
            btnStart.setOnClickListener{ onClick() }
            skip.setOnClickListener{ onClick() }
            skip.isInvisible=adapterPosition== list.lastIndex
            btnStart.isVisible= adapterPosition ==list.lastIndex
        }
    }
}
