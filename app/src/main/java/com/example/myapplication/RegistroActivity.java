package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombreApellidos, txtEmail, txtUsuario, txtContrasenia;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Asegúrate de que este sea el nombre correcto del layout

        // Referencias a los campos del formulario
        txtNombreApellidos = findViewById(R.id.txtnomapellidos);
        txtEmail = findViewById(R.id.txtemail);
        txtUsuario = findViewById(R.id.txtusuario);
        txtContrasenia = findViewById(R.id.txtcontrasenia); // Este ID debe corregirse en tu XML

        btnRegistrar = findViewById(R.id.btnregistrar);

        btnRegistrar.setOnClickListener(v -> {
            String nombreApellidos = txtNombreApellidos.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String usuario = txtUsuario.getText().toString().trim();
            String contrasenia = txtContrasenia.getText().toString().trim();

            if (nombreApellidos.isEmpty() || email.isEmpty() || usuario.isEmpty() || contrasenia.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí puedes implementar la lógica de guardar el registro (BD, API, etc.)
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                finish(); // Regresa a la pantalla anterior (por ejemplo, Login)
            }
        });
    }
}