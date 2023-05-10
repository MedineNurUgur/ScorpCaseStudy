package com.example.scorpcasestudy.presentation.common.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.scorpcasestudy.common.CallBackType

interface CallBackListener {
    fun onCallBack(item: Any, position: Int, callbackType: CallBackType?)
}

abstract class BaseRecyclerViewAdapter<T, VH : BaseRecyclerViewAdapter.BaseViewHolder<T>> :
    RecyclerView.Adapter<VH> {

    var dataSet: List<T>? = null
    var callBackListener: CallBackListener? = null

    constructor(dataSet: List<T>?, callBackListener: CallBackListener?) {
        this.dataSet = dataSet
        this.callBackListener = callBackListener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.onBind(dataSet!![position], callBackListener)
    }

    override fun getItemCount(): Int {
        return if (dataSet == null) 0 else dataSet!!.size
    }

    fun getViewFromLayout(viewGroup: ViewGroup, @LayoutRes layoutResId: Int): View {
        return LayoutInflater.from(viewGroup.context).inflate(layoutResId, viewGroup, false)
    }

    abstract class BaseViewHolder<T>(itemView: View?, callBackListener: CallBackListener?) : RecyclerView.ViewHolder(itemView!!) {

        abstract fun onBind(model: T, callBackListener: CallBackListener?)
    }
}