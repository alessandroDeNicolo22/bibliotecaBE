package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.LibroRequest;
import com.bibliotecaBE.data.dto.Response.LibroResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface LibroService {
    public ArrayList<LibroResponse> getAll();
//    public Page<LibroResponse> getAllOrdini(int id, int pageIndex, int pageSize);
    public LibroResponse getLibroById(Integer id);
    public void save (LibroRequest libroRequest);
   public void deleteById(Integer id);
}
