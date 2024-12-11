package com.share.assesment.Main

import androidx.core.text.trimmedLength
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val amount = MutableLiveData<String>()
    val counter: LiveData<String> = amount

    fun setAmount(inputVal:String,amount_val: String){
        if(amount_val == "."){
                amount.value = inputVal+amount_val;
        }else if(amount_val == "Remove"){

//            if(amount.value.toString().contains(".")){
//                amount.value = (inputVal.toDouble().toInt()).toDouble();
//            }else{
//                amount.value = (inputVal.toDouble().toInt()).toDouble();
//            }

            if(inputVal.trimmedLength() != 0){
                val temp = inputVal.substring(0,inputVal.length-1)
                if(temp.trimmedLength() == 0){
                    amount.value = "";
                }else{
                    amount.value=temp;
                }
            }
        }else{
            if(!amount.value.toString().contains(".")){
                amount.value = inputVal+amount_val.toString();
            }else{
//                amount.value = ((inputVal.toDouble().toInt()).toString()+amount_val).toDouble();
                amount.value = (inputVal.toDouble().toInt()).toString()+"."+amount_val;
            }
        }
    }
}