package com.telas.mvc_e.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.telas.mvc_e.R;
import com.telas.mvc_e.controller.ClienteDAO;
import com.telas.mvc_e.model.Cliente;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ClienteDAO clienteDAO = new ClienteDAO();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaClientes = findViewById(R.id.lista_cliente);

        registerForContextMenu(listaClientes);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clienteDAO.todos());
        listaClientes.setAdapter(adapter);

        //remover depois
        //clienteDAO.salvar(new Cliente("Miguel","119988877766","Miguel@jbs.com", "12/12/2022"));
       // clienteDAO.salvar(new Cliente("Lauro","119988877766","Lauro@jbs.com", "12/12/2022"));
       // clienteDAO.salvar(new Cliente("Anna","119988877766","Anna@jbs.com", "12/12/2022"));
        //

        FloatingActionButton bt = findViewById(R.id.floatingActionButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Formulario.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,clienteDAO.todos().toString());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if ( itemId == R.id.apaga_item){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Cliente cliente = (Cliente)  adapter.getItem(info.position);

            clienteDAO.remover(cliente);
            adapter.notifyDataSetChanged();
        }

        if ( itemId == R.id.altera_item){
            Toast.makeText(this,"Fazer alteraçãp",Toast.LENGTH_LONG).show();
        }


        return super.onContextItemSelected(item);

    }
}