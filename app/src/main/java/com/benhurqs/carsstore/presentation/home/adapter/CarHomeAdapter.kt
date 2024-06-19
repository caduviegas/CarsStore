package com.benhurqs.carsstore.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benhurqs.carsstore.databinding.CarItemBinding
import com.benhurqs.carsstore.domain.model.Car

class CarHomeAdapter (var onItemClicked: (car: Car) -> Unit = {}
) : RecyclerView.Adapter<CarHomeAdapter.HomeViewHolder>() {

    private var carList = mutableListOf<Car>()

    inner class HomeViewHolder(private val binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var car: Car

        init {
            itemView.setOnClickListener {
                if (::car.isInitialized) {
                    onItemClicked(car)
                }
            }
        }

        fun bindView(car: Car) {

            this.car = car

            with(binding) {
                carModelHome.text = car.nomeModelo
                carBrandHome.text = car.marcaNome
                carColor.text = car.cor
                carDoors.text = car.numPortas
                carFuel.text = car.combustivel
                carYear.text = car.ano
                carValue.text = car.valorFipe
            }
        }
    }

    fun setDataList(carList: List<Car>) {
        val diffUtil = AdapterDiffUtil(oldCarList = this.carList, newCarList = carList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.carList = carList.toMutableList()
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CarItemBinding.inflate(inflater, parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(carList[position])
    }

    override fun getItemCount(): Int = carList.size
}
