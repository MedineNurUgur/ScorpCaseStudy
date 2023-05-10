package com.example.scorpcasestudy.data.repository

import com.example.scorpcasestudy.data.api.FetchCompletionHandler

interface PersonRepository {

    fun fetchUsers(next: String?, fetchCompletionHandler: FetchCompletionHandler)
}