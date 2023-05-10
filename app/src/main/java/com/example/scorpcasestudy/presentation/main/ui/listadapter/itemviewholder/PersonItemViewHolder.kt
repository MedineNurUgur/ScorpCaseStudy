package com.example.scorpcasestudy.presentation.main.ui.listadapter.itemviewholder

import android.view.View
import com.example.scorpcasestudy.databinding.ItemPersonBinding
import com.example.scorpcasestudy.presentation.common.listadapter.BaseRecyclerViewAdapter
import com.example.scorpcasestudy.presentation.common.listadapter.CallBackListener

class PersonItemViewHolder<PersonItemModel>(itemView: View, callBackListener: CallBackListener?) :
    BaseRecyclerViewAdapter.BaseViewHolder<PersonItemModel>(itemView, callBackListener) {
    var binding: ItemPersonBinding = ItemPersonBinding.bind(itemView)

    init {

    }

    override fun onBind(model: PersonItemModel, callBackListener: CallBackListener?) {
        model as com.example.scorpcasestudy.presentation.main.ui.listadapter.itemmodel.PersonItemModel

        binding.person = model.person
    }
}