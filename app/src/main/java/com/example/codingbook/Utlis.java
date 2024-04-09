package com.example.codingbook;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Utlis {

    public  static  boolean isValidEmail(CharSequence target)
    {
      return(target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
