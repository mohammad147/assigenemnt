package com.example.assigenemnt1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.google.android.material.navigation.NavigationBarView
import kotlin.math.log10


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button2:Button = findViewById(R.id.BMI_BTN)
        val button:Button = findViewById(R.id.calulate_btn)
        val agetxt:EditText = findViewById(R.id.age_num)
        val wieghtTxt:EditText = findViewById(R.id.weight_num)
        val hieghtTxt:EditText = findViewById(R.id.height_num)
        val neckTxt:EditText = findViewById(R.id.neck_num)
        val waistTxt:EditText = findViewById(R.id.Waist_num)
        val FResult:TextView = findViewById(R.id.result_txt)
        var gender : String = "male"
        val Gender:Spinner = findViewById(R.id.gender_spinner)
        var options = arrayOf("male","female")
        val hip_num :EditText = findViewById(R.id.hip_num)
        val hip_txt:TextView = findViewById(R.id.hip_txt)

        var isHiddenNumberVisible = true
        var isHiddenTextVisible = true
        Gender.adapter = ArrayAdapter <String>(this,android.R.layout.simple_list_item_1,options)
        button2.setOnClickListener { view ->
            var Wieght: Double = wieghtTxt.text.toString().toDouble();
            var Hieght: Double = hieghtTxt.text.toString().toDouble();
            var result: Double = calBMI(Hieght, Wieght);
            if (gender == "male") {
                if (result < 18.50)
                    FResult.text = "Underweight so the BMI IS " + result.toString();
                else if (result <= 25)
                    FResult.text = "Normal weight so the BMI is" + result.toString();
                else if (result <= 30)
                    FResult.text = "Overweight so the BMI is " + result.toString();
                else
                    FResult.text = "Obese so the BMI is" + result.toString();
            } else if (gender == "female") {
                if (result < 18.5)
                    FResult.text = "Underweight so the BMI IS " + result.toString();
                else if (result <= 25)
                    FResult.text = "Normal weight so the BMI is" + result.toString();
                else if (result <= 30)
                    FResult.text = "Overweight so the BMI is " + result.toString();
                else
                    FResult.text = "Obese so the BMI is" + result.toString();
            }


        }
        button.setOnClickListener{ view ->
            var Age: Double = agetxt.text.toString().toDouble();
            var Wieght: Double = wieghtTxt.text.toString().toDouble();
            var hieght: Double = hieghtTxt.text.toString().toDouble();
            var Neck : Double = neckTxt.text.toString().toDouble();
            var Waist :Double= waistTxt.text.toString().toDouble();

            if(gender =="male") {
             var bff:Double =clcMale(Age, hieght, Wieght, Neck, Waist).toDouble();
                if(bff<=0){
                    FResult.text="something is wrong in your input"
                }
                else if(bff<=15) {
                    var note: String = "your a fitness person your body fat percentage is ";
                    FResult.text =note + bff.toString();
                }
                else if(bff<=30) {
                    var note: String = "your a fit person with some fat your body fat percentage is ";
                    FResult.text =note + bff.toString();
                }
                else if(bff>30) {
                    var note: String = "you should care about your health your body fat percentage is ";
                    FResult.text =note + bff.toString();
                }
            }else{
                var Hip:Double = hip_num.text.toString().toDouble();
                var bf:Double =clcFemale(Age, hieght, Wieght,Neck,Waist,Hip).toDouble();
               if(bf<=0){
                   FResult.text="something is wrong in your input"
               }
                else if(bf<=15) {
                    var note: String = "your a fitness person your body fat percentage is ";
                    FResult.text =note + bf.toString();
                }
                else if(bf<=30) {
                    var note: String = "your a fit person with some fat your body fat percentage is ";
                    FResult.text =note + bf.toString();
                }
                else if(bf>30) {
                    var note: String =
                        "you should care about your health your body fat percentage is ";
                    FResult.text = note + bf.toString()
                }
        }}
        Gender.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender=options.get(p2)
                if(gender == "male"){
                    isHiddenNumberVisible= false;
                }
                else
                {
                    isHiddenNumberVisible= true;
                }
                if (isHiddenNumberVisible) {
                    hip_num.visibility = View.VISIBLE
                    hip_txt.visibility = View.VISIBLE
                } else {
                    hip_num.visibility = View.GONE
                    hip_txt.visibility = View.GONE
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }



    }
}
public fun log(x : Double):Double{
    return log10(x)
}
public fun clcMale(age : Double , hieght: Double, wieght:Double,neck:Double,waist:Double):Double{
    var bfp:Double  = 495 / (1.0324 - 0.19077 * log10(waist - neck) + 0.15456 * log10(hieght)) - 450



    return bfp;
}
public fun clcFemale(age : Double , hieght: Double, wieght:Double,neck:Double,waist:Double,hip:Double):Double{
    var BFP:Double = 495 / (1.29579 - 0.35004 * log10(waist + hip - neck) + 0.22100 * log10(hieght)) - 450

    return BFP;
}
public fun calBMI(hieght:Double,wieght:Double):Double
{
    return (wieght)/(hieght*hieght);
}




