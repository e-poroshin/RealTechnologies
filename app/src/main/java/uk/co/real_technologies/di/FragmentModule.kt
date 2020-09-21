package uk.co.real_technologies.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.co.real_technologies.accounts.AccountsViewModel
import uk.co.real_technologies.categories.CategoryViewModel
import uk.co.real_technologies.operations.OperationsViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun categoryViewModelProvider(fragment: Fragment): CategoryViewModel {
        return ViewModelProvider(fragment).get(CategoryViewModel::class.java)
    }

    @Provides
    fun accountViewModelProvider(fragment: Fragment): AccountsViewModel {
        return ViewModelProvider(fragment).get(AccountsViewModel::class.java)
    }

    @Provides
    fun operationViewModelProvider(fragment: Fragment): OperationsViewModel {
        return ViewModelProvider(fragment).get(OperationsViewModel::class.java)
    }
}