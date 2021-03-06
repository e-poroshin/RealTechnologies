package uk.co.real_technologies.repo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: CategoryEntity)

    @Delete
    suspend fun delete(category: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun allCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT category_name FROM categories")
    fun categoryNames(): LiveData<List<String>>
}