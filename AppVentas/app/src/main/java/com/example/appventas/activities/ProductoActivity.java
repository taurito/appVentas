package com.example.appventas.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appventas.R;
import com.example.appventas.ServiceConnect.ApiService;
import com.example.appventas.ServiceConnect.RetrofitClient;
import com.example.appventas.adapters.ProductosAdapter;
import com.example.appventas.model.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;
    private List<Producto> productoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto);

        recyclerView = findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener el ID de la categoría de los extras del Intent
        Long categoriaId = getIntent().getLongExtra("categoriaId", -1);

        // Inicializar la lista de productos y adaptador
        productoList = new ArrayList<>();
        productosAdapter = new ProductosAdapter(productoList);
        recyclerView.setAdapter(productosAdapter);

        // Llamada a la API para obtener productos de la categoría
        obtenerProductosPorCategoria(categoriaId);


    }

    private void obtenerProductosPorCategoria(Long categoriaId) {

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Producto>> call = apiService.obtenerProductosPorCategoria(categoriaId);

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productoList.clear();
                    productoList.addAll(response.body());
                    productosAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ProductoActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}