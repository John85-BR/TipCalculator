package org.hyperskill.calculator.tip
import java.math.BigDecimal
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val valueText = findViewById<TextView>(R.id.bill_value_tv)

        val tipAmount = findViewById<TextView>(R.id.tip_amount_tv)
        val tipPercent = findViewById<TextView>(R.id.tip_percent_tv)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)

        editText.doAfterTextChanged { text ->


            if(text!!.isNotEmpty() && text.toString().toDouble()>0.0){
                val btext = BigDecimal(text.toString()).setScale(2,RoundingMode.FLOOR)
                valueText.setText("Bill Value: \$${btext}")
                tipPercent.setText("Tip: ${seekBar.progress}%")
                val result = (btext * seekBar.progress.toBigDecimal())/ BigDecimal(100)
                tipAmount.setText("Tip Amount: \$$result")


            } else{
                valueText.setText("")
                tipPercent.text = ""
                tipAmount.setText("")
            }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(editText.text!!.isNotEmpty() && editText.text.toString().toDouble()>0.0) {
                    tipPercent.setText("Tip: ${p1}%")
                    val btext = BigDecimal(editText.text.toString()).setScale(2,RoundingMode.FLOOR)
                    val result = (btext * p1.toBigDecimal())/ BigDecimal(100)
                    tipAmount.setText("Tip Amount: \$$result")
                }else{
                    tipPercent.text = ""
                    tipAmount.setText("")
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                println("Tracking started!")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                println("Tracking stopped!")
            }
        })
    }
}