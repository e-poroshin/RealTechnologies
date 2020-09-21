package uk.co.real_technologies.di

import uk.co.real_technologies.accounts.AccountsViewModel
import uk.co.real_technologies.categories.CategoryViewModel
import uk.co.real_technologies.operations.OperationsViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun inject(categoryViewModel: CategoryViewModel)
    fun inject(accountViewModel: AccountsViewModel)
    fun inject(operationsViewModel: OperationsViewModel)
}