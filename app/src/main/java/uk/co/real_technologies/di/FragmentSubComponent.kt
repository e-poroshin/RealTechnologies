package uk.co.real_technologies.di

import androidx.fragment.app.Fragment
import uk.co.real_technologies.accounts.AccountsFragment
import uk.co.real_technologies.categories.CategoriesFragment
import uk.co.real_technologies.operations.OperationsFragment
import uk.co.real_technologies.statistics.StatisticsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentSubComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun with(fragment: Fragment): Builder
        fun build(): FragmentSubComponent
    }

    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(statisticsFragment: StatisticsFragment)
    fun inject(accountsFragment: AccountsFragment)
    fun inject(operationsFragment: OperationsFragment)
}