package com.example.assignment_2_cloudanalytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailse.*

class Details : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    lateinit var analytics:FirebaseAnalytics
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailse)
        analytics = Firebase.analytics
        trak_Details("Details","details")
        val charecter = intent.getIntExtra("charecter",0)
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val content = intent.getStringExtra("content")
         dDetailse.text = content.toString()
        dName.text = name.toString()
        Picasso.get().load(image).into(dImage)
        dChar.text = charecter.toString()
        db = Firebase.firestore

        startTime = System.currentTimeMillis()

    }

    override fun onPause() {

        val duration = System.currentTimeMillis() - startTime

        db.collection("Time_screen3").add(
            "Details screen" to duration
        )
            .addOnSuccessListener {
                Log.e("Afn","add time success" )
            }
            .addOnFailureListener {
                Log.e("Afn","Error add time success" )

            }

        super.onPause()
    }
    fun trak_Details (className:String, screenName:String){

        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
            param(FirebaseAnalytics.Param.SCREEN_CLASS,className)
            param(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        }
    }
}