package com.example.sampleapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_provinces.view.*

class ProvinceAdapter(val context: Context, val datas: Array<Data>) :
    RecyclerView.Adapter<ProvinceAdapter.ProviderHolder>() {
    class ProviderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val country = view.tv_province!!
        val confirm = view.tv_confirm!!
        val recovered = view.tv_death!!
        val death = view.tv_active!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderHolder {
        return ProviderHolder(
            LayoutInflater.from(context).inflate(R.layout.list_provinces, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProviderHolder, position: Int) {
        val data = datas[position]
        holder.country.text = data.country
        holder.confirm.text = data.confirmed.toString()
        holder.death.text = data.deaths.toString()
        holder.recovered.text = data.recovered.toString()
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}