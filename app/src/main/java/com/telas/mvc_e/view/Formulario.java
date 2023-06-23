package com.telas.mvc_e.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.telas.mvc_e.R;
import com.telas.mvc_e.controller.ClienteDAO;
import com.telas.mvc_e.controller.ClienteDAOdb;
import com.telas.mvc_e.model.Cliente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formulario extends AppCompatActivity {

    private ClienteDAO clienteDao = new ClienteDAO();

    private ClienteDAOdb clienteDAOdb = new ClienteDAOdb(this);
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        
        Calendar inicio = Calendar.getInstance();
        adicionarDatePicker((EditText) findViewById(R.id.data), inicio);
    }

    private void adicionarDatePicker(final EditText edit, final Calendar date) {
        //edit.setText(df.format(date.getTime()));

        final DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        date.set(Calendar.YEAR, year);
                        date.set(Calendar.MONTH, monthOfYear);
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        edit.setText(df.format(date.getTime()));
                    }
                };
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Formulario.this, dpd, date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void salvardados() {

        Cliente cl = new Cliente(
                                ((EditText) findViewById(R.id.noame)).getText().toString(),
                                ((EditText) findViewById(R.id.telefone)).getText().toString(),
                ((EditText) findViewById(R.id.email)).getText().toString(),
                ((EditText) findViewById(R.id.data)).getText().toString()   );

        clienteDAOdb.salvar(cl);

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_salvar){
            salvardados();
        }
        return super.onOptionsItemSelected(item);
    }
}