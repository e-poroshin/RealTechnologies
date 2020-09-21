package uk.co.real_technologies.repo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import uk.co.real_technologies.operations.OperationType
import uk.co.real_technologies.operations.OperationTypeConverter

@Entity(tableName = "operations")
class OperationEntity(
    @field:ColumnInfo(name = "operation_category_id") val categoryId: Int,
    @field:ColumnInfo(name = "operation_account_id") val accountId: Int,
    @field:ColumnInfo(name = "operation_date") val date: Long,
    @field:ColumnInfo(name = "operation_sum") val sum: Double,
    @field:ColumnInfo(name = "operation_description") val description: String,
    @field:TypeConverters(OperationTypeConverter::class)
    @field:ColumnInfo(name = "operation_type") val type: OperationType,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "operation_id") var id: Int = 0
)