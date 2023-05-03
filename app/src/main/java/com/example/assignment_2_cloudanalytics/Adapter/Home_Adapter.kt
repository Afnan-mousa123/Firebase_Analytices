package com.example.assignment_2_cloudanalytics.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2_cloudanalytics.Model.Home_Item
import com.example.assignment_2_cloudanalytics.Notes
import com.example.assignment_2_cloudanalytics.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_item.view.*


class Home_Adapter(var activity: Activity, var data: ArrayList<Home_Item>) :
    RecyclerView.Adapter<Home_Adapter.MyViewHolder>() {
    lateinit var db:FirebaseFirestore

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val h_Image = itemView.hImage
        val h_Name = itemView.hName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.home_item, parent, false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(data[position].homeImage).into(holder.h_Image)
        holder.h_Name.text= data[position].homeName
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, Notes::class.java)
            intent.putExtra("id", data[position].id)
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}