package com.bibliotecaBE.data.service;

import com.bibliotecaBE.data.config.ServiceJWT;
import com.bibliotecaBE.data.dto.Request.UtenteRequest;
import com.bibliotecaBE.data.dto.Response.UtenteResponse;
import com.bibliotecaBE.data.entity.Utente;
import com.bibliotecaBE.data.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    UtenteRepository utenteRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ServiceJWT serviceJWT;

    @Override
    public UtenteResponse getUtenteByUsername(String email) {
        Utente utente = utenteRepo.findByEmail(email).get();
        return new UtenteResponse(utente.getId(), utente.getNome(), utente.getCognome(), utente.getEmail(),
                utente.getPassword(), utente.getRole());
    }

    @Override
    public UtenteResponse getUtenteById(Integer id) {
        Utente utente = utenteRepo.findById(id).get();

        return new UtenteResponse(utente.getId(), utente.getNome(), utente.getCognome(), utente.getEmail(),
                utente.getPassword(), utente.getRole());
    }

    @Override
    public ArrayList<UtenteResponse> getAllUtenti() {

        return this.entitiesToDTO((ArrayList<Utente>) utenteRepo.findAll());
    }

    private ArrayList<UtenteResponse> entitiesToDTO(ArrayList<Utente> elencoUtenti) {
        // TODO Auto-generated method stub
        ArrayList<UtenteResponse> elenco = new ArrayList<>();
        for(Utente oUtente: elencoUtenti) {
            elenco.add(new UtenteResponse(oUtente.getId(), oUtente.getNome(), oUtente.getCognome(), oUtente.getEmail(),
                    oUtente.getPassword(), oUtente.getRole()));
        }
        return elenco;
    }

    @Override
    public void saveUser(UtenteRequest oUtenteRequest) {
        if(oUtenteRequest.getId()== 0) {
            Utente oUtente = new Utente(0,oUtenteRequest.getNome(), oUtenteRequest.getCognome(),
                    oUtenteRequest.getEmail(), oUtenteRequest.getPassword(), oUtenteRequest.getRole());
            oUtente.setPassword(passwordEncoder.encode(oUtenteRequest.getPassword()));
            utenteRepo.save(oUtente);
        }else {
            utenteRepo.save(this.DTOUpdateEntity(oUtenteRequest));
        }
    }

    private Utente DTOUpdateEntity(UtenteRequest oUtenteRequest) {
        // TODO Auto-generated method stub
        Utente oUtente = utenteRepo.findById(oUtenteRequest.getId()).get();

        if(oUtenteRequest.getNome()!=null) {
            oUtente.setNome(oUtenteRequest.getNome());
        }

        if(oUtenteRequest.getCognome()!= null) {
            oUtente.setCognome(oUtenteRequest.getCognome());
        }

        if(oUtenteRequest.getEmail()!= null) {
            oUtente.setEmail(oUtenteRequest.getEmail());
        }

//        if(oUtenteRequest.getPassword()!= null) {
//            oUtente.setPassword(passwordEncoder.encode(oUtenteRequest.getPassword()));
//        }

        if(oUtenteRequest.getRole()!= null) {
            oUtente.setRole(oUtenteRequest.getRole());
        }

        return oUtente;
    }

    @Override
    public void addRoleToUser(String email, Role role) {
        Utente oUtente = utenteRepo.findByEmail(email).get();
        oUtente.setRole(role);
        utenteRepo.save(oUtente);
    }

    @Override
    public void deleteById(Integer id) {
        utenteRepo.deleteById(id);
    }

    @Override
    public Page<UtenteResponse> getPaginatedUtenti(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Utente> pageUtente = utenteRepo.findAll(pageRequest);


        return pageUtente.map(this::entityToDTO);

    }

    private UtenteResponse entityToDTO(Utente utente) {
        return new UtenteResponse(utente.getId(), utente.getNome(), utente.getCognome(), utente.getEmail(),
                utente.getPassword(), utente.getRole());
    }

    @Override
    public Boolean emailEsistente(UtenteRequest oUtenteRequest){
        return this.utenteRepo.findByEmail(oUtenteRequest.getEmail()).isPresent();

    }

    @Override
    public Boolean verifyPassword(Integer id, String password) {
        Utente oUtente = utenteRepo.getReferenceById(id);
        if(passwordEncoder.matches(password, oUtente.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void modifyPassword(Integer id,String password) {
        System.out.println(id);
        Utente oUtente = utenteRepo.getReferenceById(id);
        oUtente.setPassword(passwordEncoder.encode(password));
        utenteRepo.save(oUtente);
    }
}
