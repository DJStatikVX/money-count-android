package xyz.djstatikvx.moneycount.domain.usecase

import xyz.djstatikvx.moneycount.domain.model.CountOption
import javax.inject.Inject

class GetSelectedCountOptionsUseCase @Inject constructor() {
    operator fun invoke(): List<CountOption> {
        // TODO
        return emptyList()
    }
}