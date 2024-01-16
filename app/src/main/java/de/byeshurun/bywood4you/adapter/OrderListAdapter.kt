package de.byeshurun.bywood4you.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.model.Department
import de.byeshurun.bywood4you.model.Order
import de.byeshurun.bywood4you.ui.orders.OrderDetailsActivity

class OrderListAdapter(
    private val context: Context,
    private val orders: List<Order>
) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    inner class ViewHolder(orderItem: View) : RecyclerView.ViewHolder(orderItem) {
        val orderId: TextView = orderItem.findViewById(R.id.orderId)
        val orderName: TextView = orderItem.findViewById(R.id.orderName)
        val orderDepartment: TextView = orderItem.findViewById(R.id.orderDepartment)
    }

    override fun onCreateViewHolder(
        orderList: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val orderItem = LayoutInflater.from(context).inflate(R.layout.order_item_layout, orderList, false)
        return ViewHolder(orderItem)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentOrder = orders[position]

        viewHolder.orderId.text = currentOrder.id.toString()
        viewHolder.orderName.text = currentOrder.name

        var orderDepartmentText = ""

        if (currentOrder.isOrderFinished()) {
            orderDepartmentText = Department.STATUS_DONE
        }else if (currentOrder.isOrderStatusUnknown()) {
            orderDepartmentText = Department.STATUS_UNKNOWN
        } else {
            orderDepartmentText =
            currentOrder.departments.find { department -> department.getStatus() == Department.STATUS_IN_PROGRESS }?.name.toString()
        }
        viewHolder.orderDepartment.text = orderDepartmentText

        viewHolder.itemView.setOnClickListener {
            //            this.context.startActivity(Intent(this.context, OrderDetailsActivity::class.java))


            this.context.startActivity(
                Intent(this.context, OrderDetailsActivity::class.java)
                    .putExtra(this.context.getString(R.string.order_id), currentOrder.id)
            )
        }

    }

    override fun getItemCount(): Int {
        return orders.size
    }
}
