package de.byeshurun.bywood4you.model

data class User(
    val id: Int,
    val name: String,
    val department: String,
    val password: String,
) {
    companion object {
        const val MIN_LENGTH_PW = 8;
        const val DEFAULT_ID = -1
        private const val DEFAULT_STRING_VALUE = ""

        private const val CSV_DELIMITER = ";"

        private const val SPLIT_INDEX_ID = 0
        private const val SPLIT_INDEX_NAME = 1
        private const val SPLIT_INDEX_DEPARTMENT = 2
        private const val SPLIT_INDEX_PW = 3
    }

    constructor(name:String, department: String, password: String) : this(DEFAULT_ID, name, department, password)

    constructor(csvLine: String?) : this(
        csvLine?.split(CSV_DELIMITER)?.get(SPLIT_INDEX_ID)?.toInt() ?: DEFAULT_ID,
        csvLine?.split(CSV_DELIMITER)?.get(SPLIT_INDEX_NAME) ?: DEFAULT_STRING_VALUE,
        csvLine?.split(CSV_DELIMITER)?.get(SPLIT_INDEX_DEPARTMENT) ?: DEFAULT_STRING_VALUE,
        csvLine?.split(CSV_DELIMITER)?.get(SPLIT_INDEX_PW) ?: DEFAULT_STRING_VALUE
    )
    fun getAllAttributesAsCsvLine(): String {
        return "$id$CSV_DELIMITER$name$CSV_DELIMITER$department$CSV_DELIMITER$password\n"
    }
}