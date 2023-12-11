package com.europCarBE.data.controller.api;

import com.europCarBE.data.dto.Request.PreventivoRequest;
import com.europCarBE.data.dto.Response.PreventivoResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/preventivo")
public interface PreventivoAPI {

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<ArrayList<PreventivoResponse>> getAllPreventivi(HttpServletRequest request);

    @GetMapping(path = "/page/{id}", produces = "application/json")
    public Page<PreventivoResponse> page(HttpServletRequest request, @PathVariable Integer id, @RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "3") Integer pageSize);

    @GetMapping(path = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<PreventivoResponse> findById(HttpServletRequest request, @PathVariable Integer id);

    @PostMapping(path = "/nuovoModifica", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> insertUpdate(HttpServletRequest request, @RequestBody PreventivoRequest oPreventivo);

    @GetMapping(path = "/checkDelete/{id}", produces = "application/json")
    public ResponseEntity<Boolean> verificaElimina(HttpServletRequest request, @PathVariable Integer id);

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> elimina(HttpServletRequest request, @PathVariable Integer id);
}
