package com.example.assignment_2_cloudanalytics.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2_cloudanalytics.Details
import com.example.assignment_2_cloudanalytics.Model.Home_Item
import com.example.assignment_2_cloudanalytics.Model.Note_Item
import com.example.assignment_2_cloudanalytics.Notes
import com.example.assignment_2_cloudanalytics.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_item.view.*
import kotlinx.android.synthetic.main.note_item.view.*

class Note_Adapter(var activity: Activity, var data: ArrayList<Note_Item>) :
    RecyclerView.Adapter<Note_Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val n_Image = itemView.nImage
        val n_Name = itemView.nName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Note_Adapter.MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.note_item, parent, false)
        return MyViewHolder(root)
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(data[position].noteImage).into(holder.n_Image)
        holder.n_Name.text = data[position].noteName
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, Details::class.java)
            intent.putExtra("id", data[position].id)
            intent.putExtra("content", data[position].content)
            intent.putExtra("charecter", data[position].charecter)
            intent.putExtra("name", data[position].noteName)
            intent.putExtra("image", data[position].noteImage)
            activity.startActivity(intent)
        }
    }


}