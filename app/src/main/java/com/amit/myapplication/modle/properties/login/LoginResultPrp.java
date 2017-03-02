package com.amit.myapplication.modle.properties.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResultPrp {

@SerializedName("result")
@Expose
private Result result;

public Result getResult() {
return result;
}

public void setResult(Result result) {
this.result = result;
}

}