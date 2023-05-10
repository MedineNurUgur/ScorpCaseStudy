package com.example.scorpcasestudy.presentation.common.customUI.listadapter

import android.view.View
import com.example.scorpcasestudy.databinding.ItemDefaultBinding
import com.example.scorpcasestudy.presentation.common.listadapter.BaseRecyclerViewAdapter
import com.example.scorpcasestudy.presentation.common.listadapter.CallBackListener

class DefaultItemViewHolder<BaseModel>(itemView: View, callBackListener: CallBackListener?) :
    BaseRecyclerViewAdapter.BaseViewHolder<BaseModel>(itemView, callBackListener) {
    var binding: ItemDefaultBinding = ItemDefaultBinding.bind(itemView)

    init {

    }

    override fun onBind(model: BaseModel, callBackListener:CallBackListener?) {
        model as com.example.scorpcasestudy.presentation.common.listadapter.BaseItemModel

    }
}