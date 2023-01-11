package com.codewithdivya.disneycharacters.data.repository

import com.codewithdivya.disneycharacters.business.domainModel.DisneyCharacterListDomainModel
import com.codewithdivya.disneycharacters.business.repositoryInterface.DisneyCharacterListRepoInterface
import com.codewithdivya.disneycharacters.data.mappers.DisneyCharacterListMapper
import com.codewithdivya.disneycharacters.data.service.DisneyCharactersService
import com.codewithdivya.disneycharacters.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DisneyCharactersRepositoryImpl @Inject constructor(
    private val mapper: DisneyCharacterListMapper,
    private val disneyCharactersService: DisneyCharactersService,

    ) : DisneyCharacterListRepoInterface {

    override suspend fun getDisneyCharactersList(): Flow<Result<List<DisneyCharacterListDomainModel>>> =
        disneyCharactersService.getDisneyCharacterList().map { result ->
            when (result) {
                is Result.Success -> {
                    Result.Success(mapper.map(result.data))
                }
                is Result.Failure -> {
                    result
                }
            }
        }
}
