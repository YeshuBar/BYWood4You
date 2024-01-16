package de.byeshurun.bywood4you.ui.orders

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.data.MockData
import de.byeshurun.bywood4you.model.Department
import de.byeshurun.bywood4you.model.Order
import de.byeshurun.bywood4you.ui.scanner.QrCodeScannerActivity

class OrderDetailsActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private val TAG: String? = OrderDetailsActivity::class.simpleName
        private const val REQUEST_SYSTEM_CAMERA_APP = 8711
        private const val DEFAULT_INT_VALUE = -1;
    }

    private var currentOrder: Order? = null

    private val statusScanCutDeck: ImageView by lazy { this.findViewById(R.id.statusScanCutDeck) }
    private val statusImageCutDeck: ImageView by lazy { this.findViewById(R.id.statusImageCutDeck) }
    private val infoTextCutDeck: TextView by lazy { this.findViewById(R.id.cutDeckInfo) }

    private val statusScanAssemblyDeck: ImageView by lazy { this.findViewById(R.id.statusScanAssemblyDeck) }
    private val statusImageAssemblyDeck: ImageView by lazy { this.findViewById(R.id.statusImageAssemblyDeck) }
    private val infoTextAssemblyDeck: TextView by lazy { this.findViewById(R.id.assemblyDeckInfo) }

    private val statusScanShippingDeck: ImageView by lazy { this.findViewById(R.id.statusScanShippingDeck) }
    private val statusImageShippingDeck: ImageView by lazy { this.findViewById(R.id.statusImageShippingDeck) }
    private val infoTextShippingDeck: TextView by lazy { this.findViewById(R.id.shippingDeckInfo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.order_details_activity_layout)

        this.statusScanCutDeck.setOnClickListener(this)
        this.statusScanAssemblyDeck.setOnClickListener(this)
        this.statusScanShippingDeck.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")

        intentExtraHandling()
    }

    private fun intentExtraHandling() {

        val currentOrderId = this.intent.getIntExtra(
            this.getString(R.string.order_id),
            DEFAULT_INT_VALUE
        )

        if (currentOrderId != DEFAULT_INT_VALUE) {
            currentOrder = MockData.orders.find { order -> order.id == currentOrderId }

            showCurrentOrderDetails()
        }
    }

    private fun showCurrentOrderDetails() {

        if (currentOrder != null) {
            for (currentDepartment in currentOrder!!.departments) {
                showDepartmentData(currentDepartment)
            }
        }
    }

    private fun showDepartmentData(currentDepartment: Department?) {
        if (currentDepartment != null) {

            var departmentStatusBgColor: Int = DEFAULT_INT_VALUE
            var departmentStatusDrawable: Int = DEFAULT_INT_VALUE

            val departmentInfoText = String.format(
                this.getString(R.string.department_info_text_portait),
                currentDepartment.name,
                currentDepartment.startTime,
                currentDepartment.endTime
            )

            if (currentDepartment.startTime == "" && currentDepartment.endTime == "") {
                departmentStatusBgColor = this.getColor(R.color.bg_department_status_unknown)
                departmentStatusDrawable = android.R.drawable.ic_delete
            }

            if (currentDepartment.startTime != "" && currentDepartment.endTime == "") {
                departmentStatusBgColor = this.getColor(R.color.bg_department_status_in_progress)
                departmentStatusDrawable = android.R.drawable.ic_popup_sync
            }

            if (currentDepartment.startTime != "" && currentDepartment.endTime != "") {
                departmentStatusBgColor = this.getColor(R.color.bg_department_status_done)
                departmentStatusDrawable = android.R.drawable.checkbox_on_background
            }

            if (currentDepartment.startTime != "" && currentDepartment.endTime != "") {
                departmentStatusBgColor = this.getColor(R.color.bg_department_status_done)
                departmentStatusDrawable = android.R.drawable.checkbox_on_background
            }

            when (currentDepartment.name) {
                Department.PRE_CUT -> {

                    this.statusImageCutDeck.apply {
                        setBackgroundColor(departmentStatusBgColor)
                        setImageResource(departmentStatusDrawable)
                    }

                    this.infoTextCutDeck.text = departmentInfoText
                }

                Department.ASSEMBLY -> {
                    this.statusImageAssemblyDeck.apply {
                        setBackgroundColor(departmentStatusBgColor)
                        setImageResource(departmentStatusDrawable)
                    }

                    this.infoTextAssemblyDeck.text = departmentInfoText
                }

                Department.SHIPPING -> {
                    this.statusImageShippingDeck.apply {
                        setBackgroundColor(departmentStatusBgColor)
                        setImageResource(departmentStatusDrawable)
                    }

                    this.infoTextShippingDeck.text = departmentInfoText
                }
            }

        }
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.order_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(clickedItem: MenuItem): Boolean {
        when (clickedItem.itemId) {
            R.id.saveOrder -> saveOrder()
            R.id.deleteOrder -> deleteOrder()
            R.id.shareOrder -> shareOrder()
        }
        return true

    }

    private fun saveOrder() {
        Log.d(TAG, "onOptionsItemSelected() - saveOrder")
    }

    private fun deleteOrder() {
        Log.d(TAG, "onOptionsItemSelected() - deleteOrder")
    }

    private fun shareOrder() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, currentOrder?.getOrderDataSharingText())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        this.startActivity(shareIntent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.statusScanCutDeck -> startQrCodeScanActivity()
            R.id.statusScanAssemblyDeck -> startQrCodeScanActivity()
            R.id.statusScanShippingDeck -> startQrCodeScanActivity()
        }
    }

    private fun startQrCodeScanActivity() {
        this.startActivity(Intent(this, QrCodeScannerActivity::class.java))
    }

    override fun onActivityResult(requestCode:Int,resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SYSTEM_CAMERA_APP && resultCode == RESULT_OK) {
            val qrCodeBitmap = data?.extras?.get("data") as Bitmap
            Log.d(TAG, "onActivityResult() - qrCodeBitmap: $qrCodeBitmap")
        }
    }

}