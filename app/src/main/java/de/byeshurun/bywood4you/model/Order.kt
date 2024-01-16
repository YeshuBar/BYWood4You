package de.byeshurun.bywood4you.model

import java.lang.StringBuilder

data class Order(
    val id: Int,
    val name: String,
    val departments: List<Department>,
) {
    fun isOrderFinished(): Boolean {
        for (department in this.departments) {
            if (department.getStatus() != Department.STATUS_DONE) {
                return false
            }
        }
        return true
    }

    fun isOrderStatusUnknown(): Boolean {
        for (department in this.departments) {
            if (department.getStatus() != Department.STATUS_UNKNOWN) {
                return false
            }
        }
        return true
    }

    fun getOrderDataSharingText(): String {
        val simpleName = Order::class.simpleName
        val departmentSharingTexts = StringBuilder()

        for (department in this.departments) {
            departmentSharingTexts.append(department.getDepartmentDataSharingText())
        }
        return String.format(
            "\n$simpleName - Id: %s - Name: %s\n%s",
            this.id,
            this.name,
            departmentSharingTexts.toString()
        )
    }
}
