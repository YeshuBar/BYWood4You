package de.byeshurun.bywood4you.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import de.byeshurun.bywood4you.R
import kotlin.ClassCastException

class QrCodeScanHelpDialog: DialogFragment() {

    interface  QrCodeScanHelpDialogListener {
        fun onQrCodeScanHelpDialogPositiveClick(dialog: DialogFragment)
        fun onQrCodeScanHelpDialogNegativeClick(dialog: DialogFragment)
    }

    internal lateinit var qrCodeScanHelpDialogListener: QrCodeScanHelpDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            qrCodeScanHelpDialogListener = context as QrCodeScanHelpDialogListener
        } catch (e: ClassCastException) {

            throw ClassCastException("$context must implement QrCodeScanFragmentListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val dialogLayoutInflater = requireActivity().layoutInflater


            builder.apply {
                setView(
                    dialogLayoutInflater.inflate(
                        R.layout.qr_code_scan_help_dialog_layout,
                        null
                    )
                )
                setTitle(R.string.qr_code_scan_dialog_title)
                setMessage(R.string.qr_code_scan_dialog_message)
                setPositiveButton(android.R.string.ok) { dialog, id ->
                    qrCodeScanHelpDialogListener.onQrCodeScanHelpDialogPositiveClick(this@QrCodeScanHelpDialog)
                }
                setNegativeButton(android.R.string.cancel) { dialog, id ->
                    qrCodeScanHelpDialogListener.onQrCodeScanHelpDialogNegativeClick(this@QrCodeScanHelpDialog)
                }
            }.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}