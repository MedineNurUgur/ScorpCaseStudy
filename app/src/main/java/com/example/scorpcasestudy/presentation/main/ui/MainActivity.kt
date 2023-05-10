package com.example.scorpcasestudy.presentation.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.scorpcasestudy.R
import com.example.scorpcasestudy.data.entity.Person
import com.example.scorpcasestudy.databinding.ActivityMainBinding
import com.example.scorpcasestudy.presentation.common.customUI.listadapter.HeterogeneousRecyclerViewAdapter
import com.example.scorpcasestudy.presentation.common.listadapter.BaseItemModel
import com.example.scorpcasestudy.presentation.common.listadapter.itemdecoration.PagePaddingDecoration
import com.example.scorpcasestudy.presentation.main.ui.listadapter.itemmodel.PersonItemModel
import com.example.scorpcasestudy.presentation.main.viewmodel.MainViewModel
import com.example.scorpcasestudy.presentation.main.viewmodel.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    protected lateinit var binding: ActivityMainBinding

    @LayoutRes
    fun getLayoutId() = R.layout.activity_main

    var isRefreshing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchUser()
        binding = DataBindingUtil.setContentView(this, getLayoutId())

        binding.rcyList.addItemDecoration(PagePaddingDecoration(this))

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            viewModel.refreshPageData()
            isRefreshing = true
        }

        var adapterModelList = arrayListOf<BaseItemModel>()

        binding.listAdapter = HeterogeneousRecyclerViewAdapter(adapterModelList, null)

        binding.rcyList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) { //1 for down
                    viewModel.fetchUser()
                }
            }
        })

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is ViewState.Success<List<Person>?> -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        if(isRefreshing) {
                            adapterModelList = arrayListOf()
                            isRefreshing = false
                        }
                        var startPosition = adapterModelList.size
                        it.data?.forEach {
                            adapterModelList.add(PersonItemModel(person = it))
                        }
                        binding.rcyList.post {
                            binding.rcyList.adapter?.notifyItemRangeInserted(
                                startPosition,
                                adapterModelList.size
                            )
                        }

                    }
                    is ViewState.Error<*> -> binding.swipeRefreshLayout.isRefreshing = false
                    is ViewState.BusinessException<*> -> binding.swipeRefreshLayout.isRefreshing = false
//                    is ViewState.Idle<U> -> stopLoading()
                    is ViewState.Loading<*> -> binding.swipeRefreshLayout.isRefreshing = true
                    is ViewState.Idle -> {}
                }
            }
        }

    }

}