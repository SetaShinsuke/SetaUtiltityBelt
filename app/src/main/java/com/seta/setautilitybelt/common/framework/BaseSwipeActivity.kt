package com.seta.setautilitybelt.common.framework

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import com.seta.setautilitybelt.R
import com.seta.swipebackutility.SwipeBackLayout
import com.seta.swipebackutility.Utils
import com.seta.swipebackutility.app.SwipeBackActivityBase
import com.seta.swipebackutility.app.SwipeBackActivityHelper

/**
 * Created by SETA_WORK on 2017/6/9.
 */

open class BaseSwipeActivity : AppCompatActivity(), SwipeBackActivityBase {
    private var mHelper: SwipeBackActivityHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHelper = SwipeBackActivityHelper(this)
        mHelper!!.onActivityCreate()
    }

    val context: Context
        get() = this

    protected fun initToolBar(toolbar: Toolbar?) {
        if (toolbar == null) {
            return
        }
        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(colorPrimary)
        toolbar.title = getString(R.string.app_name)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private val colorPrimary: Int
        get() {
            val typedValue = TypedValue()
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            return typedValue.data
        }

    fun enableActionbarBack() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper!!.onPostCreate()
    }

    override fun findViewById(id: Int): View {
        val v = super.findViewById(id)
        if (v == null && mHelper != null)
            return mHelper!!.findViewById(id)
        return v
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper!!.swipeBackLayout
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        swipeBackLayout.scrollToFinishActivity()
    }
}
