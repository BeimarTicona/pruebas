package com.velacuss.backend.dao;

import com.velacuss.backend.domain.Dominio;
import com.velacuss.backend.domain.dto.DominioDTO;

import java.util.List;

public interface DominioDao {
    List<DominioDTO> obtenerDominiosPorDominio(String dominio);
    String obtenerNombrePorDominioPorCodigo(String dominio, String codigo);
    List<Dominio> buscaTodosDominiosSegunLista(String listaDominios);
}
