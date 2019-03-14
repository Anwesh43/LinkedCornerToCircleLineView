package com.anwesh.uiprojects.linkedcornertocirclineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.cornertocirclineview.CornerToCircLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CornerToCircLineView.create(this)
    }
}
