package com.example.appventas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appventas.ServiceConnect.ApiService;
import com.example.appventas.ServiceConnect.RetrofitClient;
import com.example.appventas.activities.CategoriaActivity;
import com.example.appventas.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);

            login(usuario);
        });
    }

    private void login(Usuario usuario) {
        Call<Usuario> call = apiService.login(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // Login exitoso: Redirigir a la siguiente pantalla
                    Usuario usuarioResponse = response.body();
                    Intent intent = new Intent(LoginActivity.this, CategoriaActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Error en el login
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                // Error de red o servidor
                Log.e("LoginActivity", "Error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}