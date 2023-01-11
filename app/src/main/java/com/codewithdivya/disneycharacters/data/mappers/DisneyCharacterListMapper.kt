package com.codewithdivya.disneycharacters.data.mappers

import com.codewithdivya.disneycharacters.business.domainModel.DisneyCharacterListDomainModel
import com.codewithdivya.disneycharacters.data.dtos.DisneyCharacterDetails
import com.codewithdivya.disneycharacters.data.dtos.DisneyListResponse
import javax.inject.Inject

class DisneyCharacterListMapper @Inject constructor() {

    fun map(list: DisneyListResponse): List<DisneyCharacterListDomainModel> {
        return list.data.map { disneyDetails ->
            with(disneyDetails) {
                DisneyCharacterListDomainModel(
                    id = this._id,
                    name = this.name,
                    imageUrl = this.imageUrl
                )
            }
        }
    }
}
