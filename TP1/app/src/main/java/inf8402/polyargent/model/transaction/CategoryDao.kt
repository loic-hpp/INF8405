package inf8402.polyargent.model.transaction

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(category: Category)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Int): Category?

    @Query("SELECT * FROM categories WHERE categoryName = :categoryName")
    suspend fun getCategoryByName(categoryName: String): Category?

    @Query("SELECT * FROM categories WHERE categoryName = :categoryName")
    suspend fun getDefaultCategoryByName(categoryName: String): Category?

    @Query("SELECT * FROM categories WHERE type = :type")
    fun getCategoriesByType(type: TransactionType): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE colorHex = :color")
    fun getCategoriesByColor(color: String): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE icon = :icon")
    fun getCategoriesByIcon(icon: String): LiveData<List<Category>>

    @Query("SELECT icon FROM categories WHERE id = :categoryId")
    suspend fun getCategoryIconById(categoryId: Int): String?

    @Query("SELECT colorHex FROM categories WHERE id = :categoryId")
    suspend fun getCategoryColorById(categoryId: Int): String?

    @Query("SELECT categoryName FROM categories WHERE id = :categoryId")
    suspend fun getCategoryNameById(categoryId: Int): String?
}