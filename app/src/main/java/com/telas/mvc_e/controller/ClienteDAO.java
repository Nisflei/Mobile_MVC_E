package com.telas.mvc_e.controller;

import com.telas.mvc_e.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //similar o banco de dado
    public static  List<Cliente> clientes = new ArrayList<>();
    private static int ids = 1;
    // fim da simul

    public static void salvar(Cliente cliente){
        cliente.setId(ids);
        ids++;
        clientes.add(cliente);
    }

    public static List<Cliente> todos(){
        return clientes;
    }

    public void remover(Cliente cliente){
        clientes.remove(cliente);
    }
}
