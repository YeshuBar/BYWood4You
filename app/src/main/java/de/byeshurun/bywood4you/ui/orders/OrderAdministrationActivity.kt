package de.byeshurun.bywood4you.ui.orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.adapter.OrderListAdapter
import de.byeshurun.bywood4you.data.MockData

class OrderAdministrationActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG: String? = OrderAdministrationActivity::class.simpleName
    //region 1. Decl. and Init Attribute / Widgets
    private val orderList: RecyclerView by lazy {this .findViewById(R.id.orderList)}   //endregion

    //region 2. Lifecycles
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //region 2a. Set layout
        setContentView(R.layout.order_administration_activity_layout)

        orderList.layoutManager = LinearLayoutManager(this)
        orderList.adapter = OrderListAdapter(this, MockData.orders)
        Log.i(TAG,"onCreate(...)")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"onRestart()")
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy()")
    }
    //endregion

    //region 2. Userinteraktionen
    override fun onClick(v: View?) {
        this.startActivity(Intent(this, OrderDetailsActivity::class.java))
    }
    //endegion
}