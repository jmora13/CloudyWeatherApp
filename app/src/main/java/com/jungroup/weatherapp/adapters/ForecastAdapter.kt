package com.jungroup.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.jungroup.weatherapp.R
import com.jungroup.weatherapp.forecast.Period


class ForecastAdapter : ListAdapter<Period, ForecastAdapter.ForecastViewHolder>(ForecastComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastViewHolder {
        return ForecastViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        //RECYCLERVIEW ANIMATION
        val current = getItem(position)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.fade_scale_animation
        )
        holder.bind(current)
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeOfDay: TextView = itemView.findViewById(R.id.timeOfDay)
        private val weatherIcon: ImageView = itemView.findViewById(R.id.weather_icon)
        private val currentTemperature: TextView = itemView.findViewById(R.id.current_temperature)
        private val weatherDescription: TextView = itemView.findViewById(R.id.weather_description)

        //NEED HEADER IN ORDER FOR GLIDE TO FETCH IMAGE FROM WEB
        private val USER_AGENT = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
        fun bind(periodItem: Period?){
            //INITALIZE TEXT AND FIELDS AND IMAGE
            timeOfDay.text = periodItem!!.name
            currentTemperature.text = periodItem.temperature.toString() + "°"
            weatherDescription.text = periodItem.shortForecast
            val glideUrl = GlideUrl(
                periodItem.icon,
                LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())
            itemView.apply {
                Glide.with(this)
                    .asBitmap()
                    .load(glideUrl)
                    .override(100,100)
                    .into(weatherIcon)
            }
        }

        companion object{
            fun create(parent:ViewGroup): ForecastViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weather_item, parent, false)
                return ForecastViewHolder(view)
            }
        }
    }



    class ForecastComparator : DiffUtil.ItemCallback<Period>() {
        override fun areItemsTheSame(oldItem: Period, newItem: Period): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Period, newItem: Period): Boolean {
            return oldItem.name == newItem.name
        }

    }

}