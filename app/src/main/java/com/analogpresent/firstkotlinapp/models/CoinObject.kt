package com.analogpresent.myapplication

import com.google.gson.annotations.Expose

class CoinObject(
    @Expose
    val price_usd: String,
    @Expose
    val name: String,
    @Expose
    val symbol: String,
    @Expose
    val percent_change_24h: String,

    val csupply: String,
    val id: String,
    val market_cap_usd: String,
    val msupply: String,
    val nameid: String,
    val percent_change_1h: String,
    val percent_change_7d: String,
    val price_btc: String,
    val rank: Int,
    val tsupply: String,
    val volume24: Double,
    val volume24a: Double
)