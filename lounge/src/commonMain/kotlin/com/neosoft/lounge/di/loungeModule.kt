package neosoft.lounge.di

import org.koin.dsl.module
import com.neosoft.lounge.data.remote.LoungeApiService
import com.neosoft.lounge.data.repository.LoungeRepositoryImpl
import com.neosoft.lounge.domain.repository.LoungeRepository
import com.neosoft.lounge.domain.usecase.CreateLoungeUseCase
import com.neosoft.lounge.domain.usecase.GetLoungeDetailsUseCase
import com.neosoft.lounge.domain.usecase.GetLoungesUseCase
import neosoft.lounge.presentation.list.LoungeListViewModel
import neosoft.lounge.presentation.details.LoungeViewModel
import neosoft.lounge.presentation.create.CreateLoungeViewModel
import org.koin.core.module.dsl.viewModel

val loungeModule = module {
    single { LoungeApiService(getOrNull()) }
    single<LoungeRepository> { LoungeRepositoryImpl(get()) }

    factory { GetLoungesUseCase(get()) }
    factory { CreateLoungeUseCase(get()) }
    factory { GetLoungeDetailsUseCase(get()) }

    viewModel { LoungeListViewModel(get()) }
    viewModel { LoungeViewModel(get()) }
    viewModel { CreateLoungeViewModel(get()) }
}
