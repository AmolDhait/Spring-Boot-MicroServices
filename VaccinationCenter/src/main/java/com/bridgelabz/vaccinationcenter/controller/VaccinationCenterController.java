package com.bridgelabz.vaccinationcenter.controller;


import com.bridgelabz.vaccinationcenter.entity.VaccinationCenter;
import com.bridgelabz.vaccinationcenter.model.Citizen;
import com.bridgelabz.vaccinationcenter.model.RequiredResponse;
import com.bridgelabz.vaccinationcenter.repo.CenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")

public class VaccinationCenterController {

    @Autowired
    private CenterRepo centerRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/add")
    public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter){
        VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
        return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
    }
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<RequiredResponse> getAllDatabasedoneCenterId(@PathVariable Integer id){
        RequiredResponse requiredResponse = new RequiredResponse();
       VaccinationCenter vaccinationCenter =centerRepo.findById(id).get();
       requiredResponse.setCenter(vaccinationCenter);
       List<Citizen> listCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
       requiredResponse.setCitizens(listCitizens);
       return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
    }
}
