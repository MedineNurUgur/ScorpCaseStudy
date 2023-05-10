package com.example.scorpcasestudy.presentation.common.customUI.listadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.scorpcasestudy.R
import com.example.scorpcasestudy.common.ModelType
import com.example.scorpcasestudy.presentation.common.listadapter.BaseItemModel
import com.example.scorpcasestudy.presentation.common.listadapter.BaseRecyclerViewAdapter
import com.example.scorpcasestudy.presentation.common.listadapter.CallBackListener
import com.example.scorpcasestudy.presentation.main.ui.listadapter.itemviewholder.PersonItemViewHolder


class HeterogeneousRecyclerViewAdapter(dataSet: List<BaseItemModel>?, callBackListener: CallBackListener?) :
    BaseRecyclerViewAdapter<BaseItemModel, BaseRecyclerViewAdapter.BaseViewHolder<BaseItemModel>>(dataSet, callBackListener) {

    var itemTouchHelper: ItemTouchHelper? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseItemModel> {

        var viewHolder: BaseViewHolder<BaseItemModel>? = null

        when (viewType) {
            ModelType.PersonItem.typeCode ->  viewHolder = PersonItemViewHolder(getViewFromLayout(viewGroup, R.layout.item_person), callBackListener)
        }
        if (viewHolder == null){
            viewHolder = DefaultItemViewHolder(getViewFromLayout(viewGroup, R.layout.item_default), callBackListener)
        }

        return viewHolder
    }


    override fun getItemViewType(position: Int): Int {
        if (position < 0 || position >= dataSet?.size!!)
            return -1
        return dataSet?.get(position)?.modelType?.typeCode ?: -1
    }
}