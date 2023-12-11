package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.dto.Request.UtenteRequest;
import com.bibliotecaBE.data.dto.Response.UtenteResponse;
import com.bibliotecaBE.data.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface UtenteService {

    public UtenteResponse getUtenteByUsername(String email);

    public UtenteResponse getUtenteById(Integer id);

    public ArrayList<UtenteResponse> getAllUtenti();

    public void saveUser(UtenteRequest oUtenteRequest);

    public void addRoleToUser(String email, Role role);

    public void deleteById(Integer id);

    public Page<UtenteResponse> getPaginatedUtenti(int page, int pageSize);

    public Boolean emailEsistente (UtenteRequest oUtenteRequest);

    public Boolean verifyPassword(Integer id, String password);

    public void modifyPassword(Integer id,String password);
}
