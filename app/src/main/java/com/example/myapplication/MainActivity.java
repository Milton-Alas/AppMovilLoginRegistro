package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private MaterialButton btnIngresar, btnSalir;

    // Constantes para SharedPreferences (deben coincidir con las de RegistroActivity)
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

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
        String usuarioIngresado = etUsuario.getText().toString().trim();
        String passwordIngresado = etPassword.getText().toString().trim();

        if (usuarioIngresado.isEmpty() || passwordIngresado.isEmpty()) {
            mostrarMensaje("Por favor ingrese usuario y contraseña");
            return;
        }

        // Obtener datos de SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String usuarioGuardado = preferences.getString(KEY_USERNAME, "");
        String passwordGuardado = preferences.getString(KEY_PASSWORD, "");

        // Validar si el usuario existe y la contraseña coincide
        if (!usuarioGuardado.isEmpty() && usuarioIngresado.equals(usuarioGuardado) &&
                passwordIngresado.equals(passwordGuardado)) {
            // Credenciales correctas
            mostrarMensaje("Inicio de sesión exitoso");
            // Redirigir a HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("usuario", usuarioIngresado); // Pasar nombre de usuario
            startActivity(intent);
            finish();
        } else {
            // Credenciales incorrectas
            mostrarMensaje("Error de usuario y clave inválidos");
            etPassword.setText(""); // Limpiar el campo de contraseña por seguridad
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

        if (id == R.id.menu_salir) {
            salirAplicacion();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
