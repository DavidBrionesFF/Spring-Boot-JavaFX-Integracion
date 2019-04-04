package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Cliente;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRep;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {
    @Autowired
    @Qualifier("lblTitulo")
    private String titulo;

    @Autowired
    private ClienteRep clienteRep;

    @FXML
    private Label lblTitulo;


    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<Cliente> comboClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitulo.setText(titulo);
        comboClientes.setItems(FXCollections.observableArrayList(clienteRep.findAll()));
    }

    @FXML
    public void onSave(){
        Cliente cliente = new Cliente();

        cliente.setApellido(txtApellido.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());

        clienteRep.save(cliente);
        comboClientes.setItems(FXCollections.observableArrayList(clienteRep.findAll()));
    }
}
