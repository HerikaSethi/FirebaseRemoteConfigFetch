package com.demoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demoproject.adapter.PicSumAdapter
import com.demoproject.constants.HelperConstant
import com.demoproject.model.PicSumResponseItem
import com.demoproject.model.ShapeList
import com.demoproject.repository.DataRepository
import com.demoproject.room.ImageDatabase
import com.demoproject.viewmodels.MyViewModel
import com.demoproject.viewmodels.ViewModelFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var remoteConfig: FirebaseRemoteConfig
    private lateinit var mViewModel: MyViewModel
    private var mainRecyclerView: RecyclerView?=null
    private var recyclerViewAdapter: PicSumAdapter? = null
    private val shapeListTitle: MutableList<ShapeList> = ArrayList()
    val eachImageItem: MutableList<PicSumResponseItem> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        fetchRemoteConfigEndUrl()

        //setUpUi()

        addObservers()

        addTitle()
      //  setUpRecyclerView(shapeListTitle)

        Log.d(TAG, "onCreate:shapeList $shapeListTitle")
    }

    private fun addTitle() {
       // val eachImageItem: MutableList<PicSumResponseItem> = ArrayList()

//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
//        eachImageItem.add(PicSumResponseItem("Alejandro Escamilla","https://picsum.photos/id/0/5000/3333",3333,"0","https://unsplash.com/photos/yC-Yzbqy7PY",5000))
          shapeListTitle.add(ShapeList("Case1", eachImageItem))
//        shapeListTitle.add(ShapeList("CAse2"))
//        shapeListTitle.add(ShapeList("CAse3"))
//        shapeListTitle.add(ShapeList("CAse4"))
    }


    private fun setUpRecyclerView(shapeListTitle: List<ShapeList>) {
        mainRecyclerView = findViewById(R.id.mainRecyclerView)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mainRecyclerView!!.layoutManager = layoutManager
        recyclerViewAdapter = PicSumAdapter(this,shapeListTitle)
        mainRecyclerView!!.adapter = recyclerViewAdapter
    }

    private fun addObservers() {
        mViewModel.PicSumLiveData.observe(this) {
            Log.d(TAG, "addObservers:pic sum live data $it")
            eachImageItem.addAll(it)
            setUpRecyclerView(shapeListTitle)
            for (i in 0 until it.size) {
                it.map { PicSumR ->
                    Log.d(TAG, "addObservers: newlist ${PicSumR}")
                }
            }

        }
    }

    private fun setUpUi(endUrl:String) {
        //mViewModel.getImageList("v2/list")
        mViewModel.getImageList(endUrl)
    }

    private fun setUpViewModel() {
        val database = ImageDatabase.getDatabase(this)
        val repository = DataRepository(database)
        val viewModelFactory = ViewModelFactory(repository)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
    }

    private fun fetchRemoteConfigEndUrl() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")

                    val fetchJsonFromKey =
                        remoteConfig.getString(resources.getString(R.string.shape_json))
                    val convertedObject = JSONObject(fetchJsonFromKey)
                    val arr = convertedObject.getJSONArray(resources.getString(R.string.type))

                    for (i in 0 until arr.length()) {
                        val ob = arr.getJSONObject(i)
                        if (ob.has(resources.getString(R.string.circle))) {
//                            Log.d(TAG, "onCreate: ${ob.getString("circle")}")
                            val circleEndPoint =
                                ob.getJSONObject(resources.getString(R.string.circle))
                                    .getString(resources.getString(R.string.endpoint))
                            Log.d(TAG, "onCreate: circleEndPoint:: $circleEndPoint")
                            setUpUi(circleEndPoint)
                            //shapeListTitle.add(ShapeList("circle"))
                            HelperConstant.CIRCLE_END_URL = circleEndPoint
                        }
                        if (ob.has(resources.getString(R.string.square))) {
                            val squareEndPoint =
                                ob.getJSONObject(resources.getString(R.string.square))
                                    .getString(resources.getString(R.string.endpoint))
                            Log.d(TAG, "onCreate: squareEndPoint:: $squareEndPoint")
                            //shapeListTitle.add(ShapeList("square"))
                            HelperConstant.SQUARE_END_URL = squareEndPoint
                        }

                    }
                    Toast.makeText(
                        this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}