package com.jetpack.bitmapimage.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BitmapImageViewModel: ViewModel() {
    private var _onBitmapCreated = MutableLiveData<Bitmap?>(null)
    var onBitmapGenerated: LiveData<Bitmap?> = _onBitmapCreated

    fun bitmapCreated(bitmap: Bitmap?) {
        _onBitmapCreated.value = bitmap
    }
}