package com.example.appventas.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appventas.R;
import com.example.appventas.activities.ProductoActivity;
import com.example.appventas.model.Categoria;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CategoriaAdapter  extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>{

    private final List<Categoria> categorias;
    private final Context context;

    public CategoriaAdapter(Context context, List<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.nombreButton.setText(categoria.getNombre());

        // Cargar la imagen desde assets
        try {
            InputStream inputStream = holder.itemView.getContext().getAssets().open(categoria.getNombre().toLowerCase() + ".png");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.nombreButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductoActivity.class);
            intent.putExtra("categoriaId", categoria.getId()); // Pasamos el ID de la categoría
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        Button nombreButton;
        ImageView imageView;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreButton = itemView.findViewById(R.id.nombreButton);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
