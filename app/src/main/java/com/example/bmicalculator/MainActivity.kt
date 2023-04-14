package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calcButton=findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener(){
            val weight=weightText.text.toString()
            val height=heightText.text.toString()
            if(validateInput(weight,height)){
            val bmi= weight.toFloat()/((height.toFloat())/100*(height.toFloat())/100)
            //for result with 2 decimal places
            val bmi2Digits = String.format("%.2f",bmi).toFloat()
            displayResult(bmi2Digits,weight.toFloat(),height.toFloat())
            }
        }
    }
    fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else->{
                return true
            }
        }
    }
    private fun displayResult(bmi:Float,weight:Float,height:Float){
        val resultIndex=findViewById<TextView>(R.id.tvIndex)
        val resultDescription=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)
        resultIndex.text=bmi.toString()
        info.text=""

        var resultText=""
        var color= 0

        when{
            bmi<18.50->{
                resultText="Underweight"
                color=R.color.underweight
                info.text="Eat food skinny bitch"
            }
            bmi in 18.50..24.99->{
                resultText="Normal"
                color=R.color.normal
                info.text="Good going"
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.over_weight
                info.text="Lose weight you phat cock"
            }
            bmi>29.99->{
                resultText="Obese"
                color=R.color.obese
                info.text="You are a burden on earth"
            }
            }
            if(weight==69f || height==69f){
                info.text= info.text as String + "..Also you got some nice numbers"
            }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText
    }
}