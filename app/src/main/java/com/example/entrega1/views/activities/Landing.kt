package com.example.entrega1.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.entrega1.R
import com.example.entrega1.databinding.ActivityLandingBinding

class Landing : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityLandingBinding
    private var formula = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.planetsSpinner
        spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.formulas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        formula = parent.getItemAtPosition(pos).toString()

        when(formula){
            parent.getItemAtPosition(0).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.segundo_grado)
                binding.c.visibility = View.VISIBLE
                binding.input3.visibility = View.VISIBLE
                binding.a.setText(R.string.a)
                binding.b.setText(R.string.b)
            }
            parent.getItemAtPosition(1).toString() ->{
                binding.ivFormula.setImageResource(R.drawable.triangulo)
                binding.c.visibility = View.GONE
                binding.input3.visibility = View.GONE
                binding.a.setText(R.string.base)
                binding.b.setText(R.string.altura)
            }
            parent.getItemAtPosition(2).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.pentagono)
                binding.c.visibility = View.GONE
                binding.input3.visibility = View.GONE
                binding.a.setText(R.string.lado)
                binding.b.setText(R.string.apotema)
            }
            else ->{
                binding.a.visibility = View.GONE
                binding.input1.visibility = View.GONE

                binding.b.visibility = View.GONE
                binding.input2.visibility = View.GONE

                binding.c.visibility = View.GONE
                binding.input3.visibility = View.GONE
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    fun solucionarEc2dogrado(av: EditText, bv:EditText, cv:EditText){

        val array = arrayOf(av,bv,cv)
        val inputs = arrayOf(binding.input1,binding.input2,binding.input3)
        var error = false

        for(i in 0..array.size-1){
            if(array[i].text.isEmpty()){
                inputs[i].error = getString(R.string.campoVacio)
                error = true
            }
        }

        if (error){
            return
        }
        val a= av.text.toString().toDouble()
        val b=bv.text.toString().toDouble()
        val c=cv.text.toString().toDouble()

        var x1:Double =0.0
        var x2:Double =0.0
        //se calcula el discriminante del polinomio
        var discriminante:Double = (b*b)-(4*a*c)

        if(a==0.0){
            binding.input1.error= getString(R.string.campoCero)
            return
        }else if (discriminante < 0) {
            Toast.makeText(this, "> Solución\n     Sin soluciones reales", Toast.LENGTH_SHORT).show()
            return
        }else if (discriminante == 0.0) {
            x1 = (-b)/(2*a)
        }else if (discriminante > 0) {
            x1 = (-b+Math.sqrt(discriminante))/(2*a)
            x2 = (-b-Math.sqrt(discriminante))/(2*a)
        }

        val res = arrayOf(x1,x2).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)

    }

    fun triangulo(av: EditText, bv:EditText) {

        val array = arrayOf(av, bv)
        val inputs = arrayOf(binding.input1, binding.input2)
        var error = false

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.campoVacio)
                error = true
            }
        }

        if (error) {
            return
        }
        val a = av.text.toString().toDouble()
        val b = bv.text.toString().toDouble()

        val x1 =(a*b)/2
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

    fun pentagono(av: EditText, bv:EditText) {

        val array = arrayOf(av, bv)
        val inputs = arrayOf(binding.input1, binding.input2)
        var error = false

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.campoVacio)
                error = true
            }
        }

        if (error) {
            return
        }
        val a = av.text.toString().toDouble()
        val b = bv.text.toString().toDouble()

        val x1 =(5*a*b)/2
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

    fun calcular(view: View): Double {
        var resultado = 0.0
        val a=binding.input1
        val b=binding.input2
        val c=binding.input3

        when(formula){
            binding.planetsSpinner.getItemAtPosition(0).toString() -> {
                solucionarEc2dogrado(a,b,c)
                return resultado
            }
            binding.planetsSpinner.getItemAtPosition(1).toString() ->{
                triangulo(a,b)
                return resultado
            }
            binding.planetsSpinner.getItemAtPosition(2).toString() -> {
                pentagono(a,b)
                return resultado
            }
            else ->{
                return resultado
            }
        }
    }
}
