package com.autipre.simplecalculator;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup operators;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operators = (RadioGroup) findViewById(R.id.operator_group);
        TextView result = (TextView) findViewById(R.id.result);
        EditText firstNumber = (EditText) findViewById(R.id.number1);
        EditText secondNumber = (EditText) findViewById(R.id.number2);
        Button equals = (Button) findViewById(R.id.equals);

        RadioButton add = (RadioButton) findViewById(R.id.add);
        RadioButton subtract = (RadioButton) findViewById(R.id.subtract);
        RadioButton multiply = (RadioButton) findViewById(R.id.multiply);
        RadioButton divide = (RadioButton) findViewById(R.id.divide);

        equals.setOnClickListener(v -> {
            int firstNumberValue = Integer.parseInt(firstNumber.getText().toString());
            int secondNumberValue = Integer.parseInt(secondNumber.getText().toString());

            boolean isAddChecked = add.isChecked();
            boolean isSubtractChecked = subtract.isChecked();
            boolean isMultiplyChecked = multiply.isChecked();
            boolean isDivideChecked = divide.isChecked();
            int answer;

            if (isAddChecked) {
                answer = firstNumberValue + secondNumberValue;
            } else if (isSubtractChecked) {
                answer = firstNumberValue - secondNumberValue;
            } else if (isMultiplyChecked){
                answer = firstNumberValue * secondNumberValue;
            } else if (isDivideChecked) {
                if (secondNumberValue == 0) {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                answer = firstNumberValue / secondNumberValue;
            } else {
                answer = 0;
            }
            add.setChecked(false);
            subtract.setChecked(false);
            multiply.setChecked(false);
            divide.setChecked(false);
            result.setText(String.valueOf(answer));

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }
}