package uk.co.real_technologies.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import uk.co.real_technologies.repo.Repository
import uk.co.real_technologies.repo.database.AppDatabase
import uk.co.real_technologies.repo.database.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryDao = AppDatabase.getDatabase(application, viewModelScope).categoryDao()
    private val repository = Repository.CategoryRepository(categoryDao)

    val liveDataCategories: LiveData<List<CategoryEntity>> = repository.allCategories
    val liveDataCategoryNames: LiveData<List<String>> = repository.categoryNames

    fun insert(category: CategoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(category)
    }

    fun update(category: CategoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(category)
    }

    fun delete(category: CategoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(category)
    }
}