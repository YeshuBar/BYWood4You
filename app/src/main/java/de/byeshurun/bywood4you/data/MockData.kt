package de.byeshurun.bywood4you.data

import de.byeshurun.bywood4you.model.Department
import de.byeshurun.bywood4you.model.Order
import de.byeshurun.bywood4you.model.User

class MockData {
    companion object {
        val departmentsOrder1 = listOf(
            Department(
                1,
                Department.PRE_CUT,
                "08:00",
                "13:00"
            ),
            Department(
                2,
                Department.ASSEMBLY,
                "13:00",
                "15:00",
            ),
            Department(
                3,
                Department.SHIPPING,
                "15:00",
                ""
            )
        )

        val departmentsOrder2 = listOf(
            Department(
                1,
                Department.PRE_CUT,
                "08:30",
                "12:00"
            ),
            Department(
                2,
                Department.ASSEMBLY,
                "12:10",
                "",
            ),
            Department(
                3,
                Department.SHIPPING,
                "",
                ""
            )
        )

        val departmentsOrder3 = listOf(
            Department(
                1,
                Department.PRE_CUT,
                "09:30",
                "13:50"
            ),
            Department(
                2,
                Department.ASSEMBLY,
                "14:00",
                "15:42",
            ),
            Department(
                3,
                Department.SHIPPING,
                "16:00",
                "16:20"
            )
        )

        val departmentsOrder4 = listOf(
            Department(
                1,
                Department.PRE_CUT,
                "",
                ""
            ),
            Department(
                2,
                Department.ASSEMBLY,
                "",
                "",
            ),
            Department(
                3,
                Department.SHIPPING,
                "",
                ""
            )
        )


        val orders = listOf(
            Order(
                1,
                "Stool",
                departmentsOrder1
            ),
            Order(
                2,
                "Table",
                departmentsOrder2
            ),
            Order(
                3,
                "Closet",
                departmentsOrder3
            ),
            Order(
                4,
                "Shelf",
                departmentsOrder4
            )
        )

        val users = listOf<User>(
            User(1,
                "John",
                "CutDeck",
                "Lion"
            ),
            User(2,
                "David",
                "AssemblyDeck",
                "Batman"
            ),
            User(3,
                "Brian",
                "ShippingDeck",
                "ThunderCats",
            ),
            User(4,
                "Karen",
                "CutDeck",
                "WonderWoman",
            ),
            User(5,
                "Jonathan",
                "AssemblyDeck",
                "Houdini",
            ),
            User(6,
                "Samantha",
                "ShippingDeck",
                "Megazord",
            )
        )
    }
}