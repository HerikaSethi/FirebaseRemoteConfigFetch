package com.demoproject.repository

import android.util.Log
import com.demoproject.constants.HelperConstant
import com.demoproject.model.PicSumResponse
import com.demoproject.model.PicSumResponseItem
import com.demoproject.network.RetrofitObject
import com.demoproject.room.ImageDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DataRepository(private val imageDatabase: ImageDatabase) {

    companion object{
        const val TAG = "DataRepository"
    }

    suspend fun getImageList(endUrl: String): Flow<PicSumResponse>{
       return flow {
           emit(RetrofitObject.api.getImageList(endUrl).let {
               Log.d(TAG, "getImageList: getImageList: $it")
               if (it.isSuccessful){
                   imageDatabase.roomDao().insertImageData(it.body() as List<PicSumResponseItem>)
                   it.body() as PicSumResponse
               }else {
                   throw Exception(it.errorBody().toString())
               }
           })
       }
    }

}