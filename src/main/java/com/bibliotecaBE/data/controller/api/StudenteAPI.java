package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.StudenteRequest;
import com.bibliotecaBE.data.dto.Response.RichiestaDiAcquistoResponse;
import com.bibliotecaBE.data.dto.Response.StudenteResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
@RequestMapping(path = "/studente")
@CrossOrigin(origins = "http://localhost:4200")

public interface StudenteAPI {

    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<StudenteResponse>> getAllStudenti(HttpServletRequest request);

    @GetMapping(path = "/listPage", produces = "application/json")
    ResponseEntity<Page<StudenteResponse>> getAllStudentiPage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path ="/findbyid/{id}", produces = "application/json")
    ResponseEntity<StudenteResponse> findbyid(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> save(HttpServletRequest request, @RequestBody StudenteRequest studenteRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/check/{id}", produces = "application/json")
    ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);

}
