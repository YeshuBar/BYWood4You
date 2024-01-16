package de.byeshurun.bywood4you.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.ui.orders.OrderAdministrationActivity
import de.byeshurun.bywood4you.ui.settings.SettingsActivity
import de.byeshurun.bywood4you.users.UserAdministrationActivity

class DashboardActivity : AppCompatActivity(), OnClickListener {

    //region 1. Decl. and Init Attribute / Widgets
    private val userAdmin by lazy { this.findViewById<Button>(R.id.userAdmin) }
    private val orderAdmin by lazy { this.findViewById<Button>(R.id.orderAdmin) }
    //endregion

    //region 2. Lifecycles
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//region 2a. Set layout
        setContentView(R.layout.dashboard_activity_layout)
   //region 2b. set listener
    this.userAdmin.setOnClickListener(this)
    this.orderAdmin.setOnClickListener(this)
    }
//endregion

    //region 3. Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)

        return true;
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.showInformation -> showInformationDialog()
            R.id.showSettings -> showSettings()
        }
        return true;
    }

    private fun showInformationDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.information)
            .setMessage(R.string.information_text)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    private fun showSettings() {
        this.startActivity(Intent(this, SettingsActivity::class.java))
    }
    //endregion

    //region 4. User interaction
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.userAdmin -> showUserAdministration()
            R.id.orderAdmin -> showOrderAdministration()
        }
    }
    private fun showUserAdministration() {
        this.startActivity(Intent(this, UserAdministrationActivity::class.java))
    }
    private fun showOrderAdministration() {
        this.startActivity(Intent(this, OrderAdministrationActivity::class.java))
    }
    //endregion
}