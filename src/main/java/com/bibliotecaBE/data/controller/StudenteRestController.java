package com.bibliotecaBE.data.controller;

import com.bibliotecaBE.data.controller.api.StudenteAPI;
import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import com.bibliotecaBE.data.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RestController
public class StudenteRestController implements StudenteAPI {
    @Autowired
    StudenteService service;

    @Override
    public ResponseEntity<ArrayList<StudenteResponse>> getAllStudenti(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAllStudenti());
    }

    @Override
    public Page<StudenteResponse> getAllStudentiPage(HttpServletRequest request, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<StudenteResponse> findbyid(HttpServletRequest request, Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, StudenteRequest studenteRequest) {
        service.save(studenteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request, Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
