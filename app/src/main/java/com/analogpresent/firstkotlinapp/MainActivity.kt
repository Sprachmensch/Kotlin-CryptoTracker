package com.analogpresent.firstkotlinapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.analogpresent.firstkotlinapp.adapter.CoinRecyclerAdapter
import com.analogpresent.myapplication.CoinObject
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var coinRecyclerAdapter: CoinRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCoins()

    }

    private fun addDataToAdapter(coins: ArrayList<CoinObject>) {
        coinRecyclerAdapter.submitList(coins)
    }

    private fun initRecyclerView() {
        recycler_View.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            coinRecyclerAdapter = CoinRecyclerAdapter()
            adapter = coinRecyclerAdapter
        }
    }

    fun getCoins() {
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://api.coinlore.com/api/tickers/"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj = JSONObject(strResp)
                val jsonArray = jsonObj.getJSONArray("data")

                val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                val coinDataType = object : TypeToken<ArrayList<CoinObject>>() {}.type

                val coins: ArrayList<CoinObject> = gson.fromJson(jsonArray.toString(), coinDataType)

                initRecyclerView()
                addDataToAdapter(coins)

            },
            Response.ErrorListener { Log.e("ERROR", "crash :(") })
        queue.add(stringReq)
    }
}
