package com.example.appventas.ServiceConnect;

import com.example.appventas.model.Categoria;
import com.example.appventas.model.Producto;
import com.example.appventas.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;
public interface ApiService {

    // Login del usuario
    @POST("api/usuarios/login")
    Call<Usuario> login(@Body Usuario usuario);

    // Obtener todas las categorías
    @GET("api/categorias")
    Call<List<Categoria>> obtenerCategorias();

    // Obtener productos por categoría
    @GET("api/productos/categoria/{categoriaId}")
    Call<List<Producto>> obtenerProductosPorCategoria(@Path("categoriaId") Long categoriaId);
}
