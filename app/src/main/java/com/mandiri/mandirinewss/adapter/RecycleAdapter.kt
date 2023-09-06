package com.mandiri.mandirinewss.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.mandirinewss.api.ModelClass
import com.mandiri.mandirinewss.R
import com.mandiri.mandirinewss.activity.webView


class RecycleAdapter(var context: Context?, var modelClassArrayList: ArrayList<ModelClass>): RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        Click News
        holder.cardView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intentUrl = Intent(context, webView::class.java)
                intentUrl.putExtra("url",modelClassArrayList.get(holder.adapterPosition).url)
                context!!.startActivity(intentUrl)
            }

        })

//        News List UI
        holder.mdate.setText("Published at:" + modelClassArrayList.get(holder.adapterPosition).publishedAt)
        holder.mauthor.setText(modelClassArrayList.get(holder.adapterPosition).author)
        holder.mheading.setText(modelClassArrayList.get(holder.adapterPosition).title)
        Glide.with(context!!).load(modelClassArrayList.get(holder.adapterPosition).urlToImage).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return modelClassArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var mheading: TextView = itemView.findViewById(R.id.judulNews)
        var mauthor: TextView = itemView.findViewById(R.id.author)
        var mdate: TextView = itemView.findViewById(R.id.tanggalPublish)
        var imageView: ImageView = itemView.findViewById(R.id.gambar)
        var cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}

