package com.example.scorpcasestudy.presentation.main.ui.listadapter.itemmodel

import com.example.scorpcasestudy.common.ModelType
import com.example.scorpcasestudy.data.entity.Person
import com.example.scorpcasestudy.presentation.common.listadapter.BaseItemModel

data class PersonItemModel(override val modelType: ModelType = ModelType.PersonItem, val person: Person) : BaseItemModel()