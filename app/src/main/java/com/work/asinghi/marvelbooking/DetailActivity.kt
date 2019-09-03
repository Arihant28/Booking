package com.work.asinghi.marvelbooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        PicassoTrustAll.getInstance(this).load(intent.getStringExtra("url")).fit().into(findViewById<ImageView>(R.id.image))
        findViewById<TextView>(R.id.myImageViewText).text = intent.getStringExtra("place")
        findViewById<TextView>(R.id.date).text = intent.getStringExtra("date")
        findViewById<TextView>(R.id.description).text = intent.getStringExtra("description")
        findViewById<TextView>(R.id.rateText).text = intent.getStringExtra("rate")
    }
}
