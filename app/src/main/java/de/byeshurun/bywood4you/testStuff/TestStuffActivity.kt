package de.byeshurun.bywood4you.testStuff

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.logic.database.DbManager
import java.io.IOException

class TestStuffActivity : AppCompatActivity() {

    companion object {
        private val TAG: String? = TestStuffActivity::class.simpleName
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    private lateinit var surfaceView: SurfaceView
    private lateinit var resultTextView: TextView

    private lateinit var cameraSource: CameraSource

    private val testStuff: Button by lazy { findViewById(R.id.testStuff) }
    private val output: EditText by lazy { findViewById(R.id.output) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1. Layout setzen
        setContentView(R.layout.test_stuff_activity_layout)

        //2. Widgets initialisieren
        this.testStuff.setOnClickListener {

//           DbManager.getInstance(this).insertUsers(MockData.users)


//            val usersFromDbAfterInsert = DbManager.getInstance(this).getAllUsers()
//
//            for (user in usersFromDbAfterInsert){
//                output.append(user.toString())
//            }

//            val userFromDbWithCurrentData = DbManager.getInstance(this).getUserById(1)
//
//            if (userFromDbWithCurrentData != null) {
//                output.append(userFromDbWithCurrentData.toString()+"\n")
//            }
//
//            val userToUpdate = User(1,"hanspeter","Shipping","passwort123")
//            DbManager.getInstance(this).updateUser(userToUpdate)
//
//            val updatedUserFromDb = DbManager.getInstance(this).getUserById(1)
//
//            if (updatedUserFromDb != null) {
//                output.append(updatedUserFromDb.toString())
//            }

//            DbManager.getInstance(this).deleteUserById(1)
//
            val usersFromDbToExport = DbManager.getInstance(this).getAllUsers()
//
//            for (user in usersFromDbToExport) {
//                output.append(user.toString())
//            }
//
//            output.append("\nStart CSVExport\n")
//            output.append("Exported to: ${this.filesDir.absolutePath}\n")
//
//            CsvFileHandler.getInstance().exportUsersToCsvFile(this, usersFromDbToExport)
//
//            output.append("\nTest Import\n")
//
//            val usersFromCsvFile = CsvFileHandler.getInstance().importUsersFromCsvFile(this)
//
//            if (usersFromCsvFile.isNotEmpty()) {
//                for (user in usersFromCsvFile) {
//                    output.append(user.getAllAttributesAsCsvLine())
//                }
//            }


        }
    }




}