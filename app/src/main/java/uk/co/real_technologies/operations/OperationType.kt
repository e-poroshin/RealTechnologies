package uk.co.real_technologies.operations

enum class OperationType(private val text: String) {
    INCOME("INCOME"),
    CONSUMPTION("CONSUMPTION");

    override fun toString(): String {
        return text
    }

}