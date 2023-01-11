package com.codewithdivya.disneycharacters.business.usecase

import com.codewithdivya.disneycharacters.business.repositoryInterface.DisneyCharacterListRepoInterface
import javax.inject.Inject

class DisneyCharactersListUseCase @Inject constructor(
    private val repository: DisneyCharacterListRepoInterface
) {
    suspend operator fun invoke() = repository.getDisneyCharactersList()
}
