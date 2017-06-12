package com.seta.setautilitybelt

import android.os.Bundle
import com.seta.swipebackutility.app.SwipeBackActivity

class MainActivity : SwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSwipeBackEnable(true)
    }
}
