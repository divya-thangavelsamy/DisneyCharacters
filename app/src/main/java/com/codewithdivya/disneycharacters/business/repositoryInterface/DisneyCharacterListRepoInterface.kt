package com.codewithdivya.disneycharacters.business.repositoryInterface

import com.codewithdivya.disneycharacters.business.domainModel.DisneyCharacterListDomainModel
import com.codewithdivya.disneycharacters.utils.Result
import kotlinx.coroutines.flow.Flow

interface DisneyCharacterListRepoInterface {
    suspend fun getDisneyCharactersList() : Flow<Result<List<DisneyCharacterListDomainModel>>>
}
