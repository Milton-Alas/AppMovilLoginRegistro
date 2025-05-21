package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private MaterialButton btnIngresar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        }

        // Inicializar vistas
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir = findViewById(R.id.btnSalir);

        // Configurar listeners
        btnIngresar.setOnClickListener(v -> validarUsuario());
        btnSalir.setOnClickListener(v -> salirAplicacion());
    }

    private void validarUsuario() {
        String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarMensaje("Por favor ingrese usuario y contraseña");
            return;
        }

        // Validación simple (cambia esto por tu lógica real)
        if (usuario.equals("admin") && password.equals("admin123")) {
            mostrarMensaje("Inicio de sesión exitoso");
            // Redirigir a HomeActivity (asegúrate de que existe)
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            mostrarMensaje("Usuario o contraseña incorrectos");
        }
    }

    private void salirAplicacion() {
        finishAffinity();
        System.exit(0);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_registrar) {
            // Abrir actividad de registro
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}