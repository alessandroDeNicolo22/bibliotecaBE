package com.europCarBE.data.controller.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.europCarBE.data.dto.Request.UtenteRequest;
import com.europCarBE.data.dto.Response.UtenteResponse;
import com.europCarBE.data.enums.Role;

@RequestMapping(path="/utente")
@CrossOrigin(origins = "http://localhost:4200")
public interface UtenteAPI {

    @GetMapping(path="/list", produces="application/json")
    public ResponseEntity<ArrayList<UtenteResponse>> getAllUtenti(HttpServletRequest request);

    @GetMapping(path="/findbyid/{id}", produces="application/json")
    public ResponseEntity<UtenteResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path="/nuovoModifica", produces="application/json", consumes="application/json")
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody UtenteRequest oUtenteRequest);

    @PutMapping(path="/addroletouser/{email}/{role}", produces= "application/json")
    public ResponseEntity<?> addRoleToUser(HttpServletRequest request, @PathVariable String email, @PathVariable Role role);

    @DeleteMapping(path="/elimina/{id}", produces="application/json")
    public ResponseEntity<?> deleteById(HttpServletRequest request, @PathVariable Integer id);

    @GetMapping(path="/findbyemail/{email}", produces="application/json")
    public ResponseEntity<UtenteResponse> findByEmail(HttpServletRequest request, @PathVariable String email);

    @GetMapping(path="/paginatedlist", produces="application/json")
    public Page<UtenteResponse> getAllUtentiPaginated(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "5") Integer pageSize);

    @PostMapping(path="/checkEmail", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> checkEmail(HttpServletRequest request, @RequestBody UtenteRequest oUtenteRequest);

    @GetMapping(path="/verifyPassword", produces = "application/json")
    public ResponseEntity<Boolean> verifyPassword(HttpServletRequest request, @RequestParam Integer id, @RequestParam String password);

    @PostMapping(path="/modifyPassword", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> modifyPassword(HttpServletRequest request, @RequestBody Integer id, @RequestParam String password);
}
