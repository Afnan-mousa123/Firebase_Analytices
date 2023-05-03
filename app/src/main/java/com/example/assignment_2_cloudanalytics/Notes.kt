package com.example.assignment_2_cloudanalytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2_cloudanalytics.Adapter.Note_Adapter
import com.example.assignment_2_cloudanalytics.Model.Note_Item
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Notes : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var rvNote: RecyclerView
    private lateinit var list: ArrayList<Note_Item>
    lateinit var progressBar:ProgressBar
    lateinit var analytics:FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
         val itemId = intent.getIntExtra("id", 0)
        db = Firebase.firestore
        rvNote = findViewById(R.id.rvNote)
        list = ArrayList()
        analytics = Firebase.analytics
        progressBar = findViewById<ProgressBar>(R.id.progressBar2)
        progressBar.visibility = android.view.View.VISIBLE
        if(itemId == 1) {
            getData(1)
            trak_Nots("Notes","home")

        }
        else if(itemId ==2){
            getData(2)
            trak_Nots("Notes","family")

        }else if(itemId == 3){
            getData(3)
            trak_Nots("Notes","university")

        }
    }
        fun getData( itemId:Int){
            db.collection("Note").get()
                .addOnSuccessListener {
                    for (document in it) {
                        if (itemId == document.getLong("id")?.toInt()) {
                            list.add(
                                Note_Item(
                                    document.getLong("id")!!.toInt(),
                                    document.getString("content")!!,
                                    document.getLong("charecter")!!.toInt(),
                                    document.getString("image")!!,
                                    document.getString("name")!!
                                    )
                            )
                            Log.e("Afn", "${document.id} => ${document.data}}")
                        }
                    }
                    val n = Note_Adapter(this, list)
                    rvNote.adapter = n
                    rvNote.layoutManager = LinearLayoutManager(this)
                    progressBar.visibility = View.GONE

                }
                .addOnFailureListener {
                    Log.e("Afn", "Failed get")
                }
        }

    fun trak_Nots (className:String, screenName:String){

        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
           param(FirebaseAnalytics.Param.SCREEN_CLASS,className)
           param(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        }
    }


}