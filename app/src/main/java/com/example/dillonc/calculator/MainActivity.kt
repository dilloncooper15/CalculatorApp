package com.example.dillonc.calculator

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_OPERAND1 = "Operand1"
private const val STATE_OPERAND1_STORED = "Operand1_Stored"

class MainActivity : AppCompatActivity() {

    // Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="
    private fun Context.toast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // listener reads caption of the button that is clicked and appends it to any text already existing in newEntryNumber field.
        // When a button is tapped and Android calls the onClick method, it passes in a reference to the button that was tapped.
        // Since all widgets are views, so any widget that is tapped can be passed as a parameter to click as an instance of its base class (View).
        // Since not all views have text, so before referring to the text property, we have to cast b as a widget that does have a text property.

        // Number and Decimal Buttons
        val buttonArray = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDecimal)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newEntryNumber.append(b.text)
        }

        for (i in buttonArray) {
            i.setOnClickListener(listener)
        }

        // Operation Buttons
        val operationArray = arrayOf(buttonEquals, buttonDivide, buttonMultiply, buttonMinus, buttonAdd)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = newEntryNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newEntryNumber.setText("")
            }
            pendingOperation = op
            operation.text = pendingOperation
        }

        for (i in operationArray) {
            i.setOnClickListener(opListener)
        }

        // Negative Button
        buttonNeg.setOnClickListener({ view ->
            val value = newEntryNumber.text.toString()
            if (value.isEmpty()) {
                newEntryNumber.setText("-")
            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    newEntryNumber.setText(doubleValue.toString())

                } catch (e: NumberFormatException) {
                    // newEntryNumber was "-" or ".", so clear it
                    newEntryNumber.setText("")
                }
            }
        })

        // Clear Button
        buttonClear.setOnClickListener({ view ->
            val clearValue = newEntryNumber.text.toString()
            if (clearValue.isEmpty()) {
                result.setText("")
                operation.text = ""
                operand1 = null
                pendingOperation = ""

                toast("All fields cleared")
            } else {
                newEntryNumber.setText("")
                operand2 = 0.0
                toast("bll")
            }
        })

        // Clear Operation Only Button
//        buttonClearOp.setOnClickListener({ view ->
//            //            val clearOperationField = pendingOperation
//            when {
//                pendingOperation.isNotEmpty() -> {
//                    pendingOperation = ""
//                    operation.setText("")
//                    toast("aa")
//                }
//            }
//        })

        // Cos Button

        // Sine Button
//        buttonSin.setOnClickListener({ view ->
//            val sinValue = newEntryNumber.text.toString()
//        })

        // Tan Button

    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            operand2 = value

            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" ->
                    operand1 = if (value == 0.0) {
                        toast("Cannot divide by zero!")  // Display a message stating the user cannot divide by zero.
                        operand1
                    } else {
                        operand1!! / value
                    }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }

        result.setText(operand1.toString())
        newEntryNumber.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operand1 = if (savedInstanceState.getBoolean(STATE_OPERAND1_STORED, false)) {
            savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            null
        }

        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION)
        operation.text = pendingOperation
    }
}
