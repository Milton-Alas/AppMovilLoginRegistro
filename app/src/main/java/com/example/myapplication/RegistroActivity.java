package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtuser, txtEmail, txtcontraseniaConfirm, txtContrasenia;
    private Button btnRegistrar, btnRegresar;

    // Constantes para SharedPreferences
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    // Patrón para validar contraseña alfanumérica (letras y números)
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$");
    /**
     * Guarda los datos del usuario en SharedPreferences
     */
    private void guardarDatosUsuario() {
        String nombreUsuario = txtuser.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String contrasenia = txtContrasenia.getText().toString().trim();

        // Obtener instancia de SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Guardar los datos como pares clave/valor
        editor.putString(KEY_USERNAME, nombreUsuario);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, contrasenia); // Nota: en producción deberías encriptar la contraseña

        // Guardar los cambios
        editor.apply();
    }

    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        txtuser.setText("");
        txtEmail.setText("");
        txtContrasenia.setText("");
        txtcontraseniaConfirm.setText("");

        // También limpiamos los errores si hubiera alguno
        txtuser.setError(null);
        txtEmail.setError(null);
        txtContrasenia.setError(null);
        txtcontraseniaConfirm.setError(null);

        // Colocar el foco en el primer campo
        txtuser.requestFocus();
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Referencias a los campos del formulario
        txtuser = findViewById(R.id.txtuser);
        txtEmail = findViewById(R.id.txtemail);
        txtContrasenia = findViewById(R.id.txtcontrasenia);
        txtcontraseniaConfirm = findViewById(R.id.txtcontraseniaConfirm);

        btnRegistrar = findViewById(R.id.btnregistrar);
        btnRegresar = findViewById(R.id.btnregresar);

        // Configurar el botón regresar
        btnRegresar.setOnClickListener(v -> finish());

        btnRegistrar.setOnClickListener(v -> {
            if (validarFormulario()) {
                // Guardar los datos del usuario utilizando SharedPreferences
                guardarDatosUsuario();

                // Mostrar mensaje de éxito
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                // Limpiar los campos del formulario
                limpiarCampos();

                // No llamamos a finish() para que el usuario pueda ver que los campos fueron limpiados
            }
        });
    }

    /**
     * Valida todos los campos del formulario según los requerimientos
     * @return true si todos los campos son válidos, false en caso contrario
     */
    private boolean validarFormulario() {
        boolean isValid = true;

        // Validar nombre de usuario (mínimo 3 caracteres)
        String nombreUsuario = txtuser.getText().toString().trim();
        if (nombreUsuario.isEmpty()) {
            txtuser.setError("El nombre de usuario es obligatorio");
            isValid = false;
        } else if (nombreUsuario.length() < 3) {
            txtuser.setError("El nombre de usuario debe tener al menos 3 caracteres");
            isValid = false;
        }

        // Validar formato de correo electrónico
        String email = txtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            txtEmail.setError("El correo electrónico es obligatorio");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Ingrese un correo electrónico válido");
            isValid = false;
        }

        // Validar contraseña (mínimo 5 caracteres y alfanumérica)
        String contrasenia = txtContrasenia.getText().toString().trim();
        if (contrasenia.isEmpty()) {
            txtContrasenia.setError("La contraseña es obligatoria");
            isValid = false;
        } else if (contrasenia.length() < 5) {
            txtContrasenia.setError("La contraseña debe tener al menos 5 caracteres");
            isValid = false;
        } else if (!PASSWORD_PATTERN.matcher(contrasenia).matches()) {
            txtContrasenia.setError("La contraseña debe contener letras y números");
            isValid = false;
        }

        // Validar confirmación de contraseña
        String confirmacion = txtcontraseniaConfirm.getText().toString().trim();
        if (confirmacion.isEmpty()) {
            txtcontraseniaConfirm.setError("Debe confirmar la contraseña");
            isValid = false;
        } else if (!confirmacion.equals(contrasenia)) {
            txtcontraseniaConfirm.setError("Las contraseñas no coinciden");
            isValid = false;
        }

        return isValid;
    }
}