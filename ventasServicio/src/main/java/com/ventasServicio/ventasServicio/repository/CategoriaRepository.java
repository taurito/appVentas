package com.ventasServicio.ventasServicio.repository;

import com.ventasServicio.ventasServicio.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
