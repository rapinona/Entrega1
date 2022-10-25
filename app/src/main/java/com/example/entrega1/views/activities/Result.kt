package com.example.entrega1.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.entrega1.R
import com.example.entrega1.databinding.ActivityLandingBinding
import com.example.entrega1.databinding.ActivityResultadoBinding

class Result : AppCompatActivity() {

    private lateinit var binding : ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val res = bundle?.getDoubleArray("res")
        val formula = bundle?.getString("formula")
        val spinner = resources.getStringArray(R.array.formulas_array)
        var arrayInputs = arrayOf(binding.val1,binding.val2)

        when(formula){
            spinner[0].toString() ->{
                binding.lbl2.visibility = View.VISIBLE
                binding.val2.visibility = View.VISIBLE
            }
            spinner[1].toString() ->{
                binding.lbl2.visibility = View.GONE
                binding.val2.visibility = View.GONE
            }
            spinner[2].toString() ->{
                binding.lbl2.visibility = View.GONE
                binding.val2.visibility = View.GONE
            }
            else ->{
                binding.lbl1.visibility = View.VISIBLE
                binding.val1.visibility = View.VISIBLE
            }
        }
        if (res != null) {
            for (i in 0 .. res.size-1){
                arrayInputs[i].text = res[i].toString()
            }
        }

    }
}