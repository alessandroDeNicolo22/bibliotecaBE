package com.bibliotecaBE.data.controller.api;

import com.bibliotecaBE.data.dto.Request.GenereRequest;
import com.bibliotecaBE.data.dto.Response.CopiaResponse;
import com.bibliotecaBE.data.dto.Response.GenereResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping(path = "/genere")
@CrossOrigin(origins = "http://localhost:4200")
public interface GenereAPI {
    @GetMapping(path = "/list", produces = "application/json")
    ResponseEntity<ArrayList<GenereResponse>> list(HttpServletRequest request);

    @GetMapping(path = "/listpage", produces = "application/json")
    ResponseEntity<Page<GenereResponse>> getAllGenerePage(HttpServletRequest request, @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "3") int pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    ResponseEntity<GenereResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/save", produces = "application/json", consumes = "application/json")
    ResponseEntity<?> saveGenere(HttpServletRequest request, @RequestBody GenereRequest genereRequest);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path = "/check/{id}", produces = "application/json")
    ResponseEntity<Boolean> check(HttpServletRequest request, @PathVariable Integer id);
}
