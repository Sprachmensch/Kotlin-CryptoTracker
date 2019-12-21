package com.analogpresent.firstkotlinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.analogpresent.firstkotlinapp.R
import com.analogpresent.myapplication.CoinObject
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.post.view.*

class CoinRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<CoinObject> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(coinList: ArrayList<CoinObject>) {
        items = coinList
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val CoinTitle = itemView.title
        val CoinValue = itemView.value
        val CoinImage = itemView.image
        val CoinChange24H = itemView.change_24h
        val CoinSymbol = itemView.symbol

        fun bind(coin: CoinObject) {
            CoinTitle.text = coin.name
            CoinValue.text = "$" + coin.price_usd
            CoinSymbol.text = coin.symbol

            if (coin.percent_change_24h.toFloat() > 0) {
                CoinChange24H.setTextColor(itemView.resources.getColor(R.color.colorPrimary))
                CoinChange24H.text = "+" + coin.percent_change_24h + "%"

            } else {
                CoinChange24H.setTextColor(itemView.resources.getColor(R.color.colorAccent))
                CoinChange24H.text = "-" + coin.percent_change_24h + "%"
            }

            var symbol = coin.symbol.toLowerCase()
            var url =
                "https://raw.githubusercontent.com/atomiclabs/cryptocurrency-icons/master/128/color/" + symbol + ".png"
            Glide.with(itemView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_monetization_on_black_24dp)
                .into(CoinImage)
        }
    }

}