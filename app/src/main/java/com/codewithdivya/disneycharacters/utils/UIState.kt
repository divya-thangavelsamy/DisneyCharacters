package com.codewithdivya.disneycharacters.utils

import com.codewithdivya.disneycharacters.business.domainModel.DisneyCharacterListDomainModel

sealed class UIState {
    object Loading : UIState()
    data class Success(val news: List<DisneyCharacterListDomainModel>): UIState()
    data class Error(val exception: Throwable): UIState()
}
