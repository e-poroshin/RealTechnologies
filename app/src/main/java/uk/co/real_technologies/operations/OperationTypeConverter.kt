package uk.co.real_technologies.operations

import androidx.room.TypeConverter

object OperationTypeConverter {
    @JvmStatic
    @TypeConverter
    fun fromTypeToString(type: OperationType?): String? {
        return type?.toString()
    }

    @JvmStatic
    @TypeConverter
    fun fromStringToType(type: String): OperationType {
        return when (type) {
            OperationType.CONSUMPTION.toString() -> OperationType.CONSUMPTION
            OperationType.INCOME.toString() -> OperationType.INCOME
            else -> throw IllegalArgumentException("Could not recognize type")
        }
    }
}