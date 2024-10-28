package com.example.appventas.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appventas.R;
import com.example.appventas.ServiceConnect.ApiService;
import com.example.appventas.ServiceConnect.RetrofitClient;
import com.example.appventas.adapters.CategoriaAdapter;
import com.example.appventas.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategorias;
    private CategoriaAdapter categoriaAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categoria);

        recyclerViewCategorias = findViewById(R.id.recyclerViewCategorias);
        recyclerViewCategorias.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        obtenerCategorias();

    }

    private void obtenerCategorias() {
        Call<List<Categoria>> call = apiService.obtenerCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Categoria> categorias = response.body();
                    categoriaAdapter = new CategoriaAdapter(CategoriaActivity.this, categorias);
                    recyclerViewCategorias.setAdapter(categoriaAdapter);
                } else {
                    Toast.makeText(CategoriaActivity.this, "Error al cargar las categorías", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("CategoriaActivity", "Error: " + t.getMessage());
                Toast.makeText(CategoriaActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}