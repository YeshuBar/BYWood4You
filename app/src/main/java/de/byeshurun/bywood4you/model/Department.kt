package de.byeshurun.bywood4you.model

data class Department(
    val id: Int,
    val name: String,
    val startTime: String?,
    val endTime: String?,
) {
    companion object Names {
        const val PRE_CUT = "Cutting"
        const val ASSEMBLY = "Assembly"
        const val SHIPPING = "Shipping"

        const val STATUS_UNKNOWN = "Unknown"
        const val STATUS_IN_PROGRESS = "In progress"
        const val STATUS_DONE = "done"
    }

    fun getStatus(): String {
        if (this.startTime == "" && this.endTime == "") {
            return STATUS_UNKNOWN
        }
        if (this.startTime != "" && this.endTime == "") {
            return STATUS_IN_PROGRESS
        }
        if (this.startTime != "" && this.endTime != "") {
            return STATUS_DONE
        }
        return STATUS_UNKNOWN
    }

    fun getDepartmentDataSharingText() : String {
        val simpleName = Department::class.simpleName

        return String.format(
            "\n$simpleName - Id: %s - Name: %s\nStart: %s - End: %s\nInProgress: %s\n",
            this.id,
            this.name,
            this.startTime,
            this.endTime,
            this.getStatus()

        )
    }
}
