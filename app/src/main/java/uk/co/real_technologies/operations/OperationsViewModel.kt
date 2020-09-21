package uk.co.real_technologies.operations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import uk.co.real_technologies.repo.Repository
import uk.co.real_technologies.repo.database.AppDatabase
import uk.co.real_technologies.repo.database.Operation
import uk.co.real_technologies.repo.database.OperationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OperationsViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: Repository.OperationRepository
    val liveData: LiveData<List<Operation>>

    init {
        val operationDao = AppDatabase.getDatabase(application, viewModelScope).operationDao()
        repository = Repository.OperationRepository(operationDao)
        liveData = repository.allOperations
    }

    fun insert(operations: OperationEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(operations)
    }

    fun update(operations: OperationEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(operations)
    }

    fun delete(operations: OperationEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(operations)
    }
}