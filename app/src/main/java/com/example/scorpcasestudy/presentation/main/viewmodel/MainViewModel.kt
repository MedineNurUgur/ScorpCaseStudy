package com.example.scorpcasestudy.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scorpcasestudy.data.api.FetchCompletionHandler
import com.example.scorpcasestudy.data.entity.FetchError
import com.example.scorpcasestudy.data.entity.FetchResponse
import com.example.scorpcasestudy.data.entity.Person
import com.example.scorpcasestudy.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val personRepository: PersonRepository
) : ViewModel() {

    protected var _state: MutableStateFlow<ViewState<List<Person>?>> = MutableStateFlow(ViewState.Idle())
    val state: StateFlow<ViewState<List<Person>?>> = _state

    var page = "1"

    var stopPaging = false

    fun fetchUser() {
        if(!stopPaging){
            _state.value = ViewState.Loading()
            personRepository.fetchUsers(page, object : FetchCompletionHandler{
                override fun invoke(p1: FetchResponse?, p2: FetchError?) {
                    if(p1 != null){
                        Log.e("test",p1.people.toString())
                        page = p1.next.toString()
                        _state.value = ViewState.Success(p1.people)
                    }

                    else if(p2 != null){
                        stopPaging = true
                        _state.value = ViewState.Error(p2.errorDescription)
                    }
                }
            })
        }
    }

    fun refreshPageData() {
        stopPaging = false
        page = "1"
        fetchUser()
    }

}

sealed class ViewState<T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error<T>(val error: String? = null) : ViewState<T>()
    data class BusinessException<T>(val error: String?) : ViewState<T>()
    class Idle<T> : ViewState<T>()
    class Loading<T> : ViewState<T>()
}