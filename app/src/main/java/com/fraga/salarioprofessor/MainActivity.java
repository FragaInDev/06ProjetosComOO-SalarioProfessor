/*
 *@author: Bruno Fraga
 */

package com.fraga.salarioprofessor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Bruno Fraga - RA: 1110482112016

    private EditText inputSalary, inputYears, inputTotalHours, inputHourlyRate;
    private RadioGroup radioGroupProfessorType;
    private Button buttonCalculate;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSalary = findViewById(R.id.inputSalary);
        inputYears = findViewById(R.id.inputYears);
        inputTotalHours = findViewById(R.id.inputTotalHours);
        inputHourlyRate = findViewById(R.id.inputHourlyRate);
        radioGroupProfessorType = findViewById(R.id.radioGroupProfessorType);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        resultText = findViewById(R.id.resultText);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSalary();
            }
        });
    }

    private void calculateSalary() {
        int selectedTypeId = radioGroupProfessorType.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedTypeId);
        if (selectedRadioButton == null) {
            Toast.makeText(this, "Por favor, selecione o tipo de professor", Toast.LENGTH_SHORT).show();
            return;
        }

        String professorType = selectedRadioButton.getText().toString();
        double salary = 0;

        switch (professorType) {
            case "Professor Titular":
                String salaryInput = inputSalary.getText().toString();
                String yearsInput = inputYears.getText().toString();
                if (salaryInput.isEmpty() || yearsInput.isEmpty()) {
                    Toast.makeText(this, "Por favor, insira o salário base e o número de anos", Toast.LENGTH_SHORT).show();
                    return;
                }
                ProfessorTitular professorTitular = new ProfessorTitular();
                professorTitular.setSalario(Double.parseDouble(salaryInput));
                professorTitular.setAnosInstituicao(Integer.parseInt(yearsInput));
                salary = professorTitular.calcSalario();
                break;
            case "Professor Horista":
                String totalHoursInput = inputTotalHours.getText().toString();
                String hourlyRateInput = inputHourlyRate.getText().toString();
                if (totalHoursInput.isEmpty() || hourlyRateInput.isEmpty()) {
                    Toast.makeText(this, "Por favor, insira o total de horas e o valor da hora aula", Toast.LENGTH_SHORT).show();
                    return;
                }
                ProfessorHorista professorHorista = new ProfessorHorista();
                professorHorista.setHorasAula(Integer.parseInt(totalHoursInput));
                professorHorista.setValorHoraAula(Double.parseDouble(hourlyRateInput));
                salary = professorHorista.calcSalario();
                break;
        }

        resultText.setText(String.format("Salário Calculado: R$ %.2f", salary));
    }
}
