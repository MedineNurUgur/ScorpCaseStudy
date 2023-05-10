package com.example.scorpcasestudy.data.repository.impl


import com.example.scorpcasestudy.data.api.DataSourceService
import com.example.scorpcasestudy.data.api.FetchCompletionHandler
import com.example.scorpcasestudy.data.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    val dataSourceService: DataSourceService
): PersonRepository {
    override fun fetchUsers(
        next: String?,
        completionHandler: FetchCompletionHandler
    ) = dataSourceService.fetch(next, completionHandler)

}