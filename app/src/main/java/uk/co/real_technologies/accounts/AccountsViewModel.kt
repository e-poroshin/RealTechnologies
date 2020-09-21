package uk.co.real_technologies.accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import uk.co.real_technologies.repo.Repository
import uk.co.real_technologies.repo.database.AccountEntity
import uk.co.real_technologies.repo.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsViewModel(application: Application) : AndroidViewModel(application) {

    private val accountDao = AppDatabase.getDatabase(application, viewModelScope).accountDao()
    private val repository = Repository.AccountRepository(accountDao)

    val liveDataAccounts: LiveData<List<AccountEntity>> = repository.allAccounts
    val liveDataAccountNames: LiveData<List<String>> = repository.accountNames

    fun insert(accounts: AccountEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(accounts)
    }

    fun update(accounts: AccountEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(accounts)
    }

    fun delete(accounts: AccountEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(accounts)
    }
}