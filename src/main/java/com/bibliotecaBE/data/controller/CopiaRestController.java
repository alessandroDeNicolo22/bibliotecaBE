package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.CopiaAPI;
import com.bibliotecaBE.data.dto.Request.CopiaRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.service.CopiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class CopiaRestController implements CopiaAPI {

    @Autowired
    CopiaService service;


    @Override
    public ResponseEntity<ArrayList<CopiaResponse>> getAllCopie(HttpServletRequest request) {
        return null;
    }

    @Override
    public Page<CopiaResponse> getAllCopiePage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<CopiaResponse> findById(HttpServletRequest request, Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, CopiaRequest copiaRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> checkDelete(HttpServletRequest request, Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        return null;
    }
}
