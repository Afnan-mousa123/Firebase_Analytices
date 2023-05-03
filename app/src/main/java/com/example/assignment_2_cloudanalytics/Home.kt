package com.example.assignment_2_cloudanalytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2_cloudanalytics.Adapter.Home_Adapter
import com.example.assignment_2_cloudanalytics.Model.Home_Item
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var rvHome: RecyclerView
    lateinit var list: ArrayList<Home_Item>
    lateinit var progressBar :ProgressBar
    lateinit var analytics:FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        db = Firebase.firestore
        rvHome = findViewById(R.id.rvHome)
        list = ArrayList()
        analytics = Firebase.analytics
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = android.view.View.VISIBLE
        trak_Home("Home","home")
        db.collection("Home").get()
            .addOnSuccessListener {
                for (document in it){
                    list.add(
                        Home_Item(
                            document.getLong("id")!!.toInt(),
                            document.getString("image")!!,
                            document.getString("name")!!

                        )
                    )
                    Log.e("Afn", "${document.id} => ${document.data}}")
                }
                val H = Home_Adapter(this , list)
                rvHome.adapter = H
                rvHome.layoutManager = LinearLayoutManager(this)
                progressBar.visibility = GONE
            }
            .addOnFailureListener {
                Log.e("Afn", "Failed get")
            }




    }
    fun trak_Home (className:String, screenName:String){

        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
            param(FirebaseAnalytics.Param.SCREEN_CLASS,className)
            param(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        }
    }

}