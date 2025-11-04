package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;
import com.demo.binding.SsnResponse;
import com.demo.exception.ResourceNotFoundException;
import com.demo.modal.Citizen;
import com.demo.repository.CitizenRepository;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CitizenRepository citizenRepo;

    @Override
    public CitizenResponse addCitizen(CitizenRequest request) {

        // Call SSN service to validate SSN
        SsnResponse ssnData = webClientBuilder.build()
                .get()
                .uri("http://localhost:9090/api/{ssn}", request.getSsn())
                .retrieve()
                .bodyToMono(SsnResponse.class)
                .block();

        if (ssnData == null) {
            throw new ResourceNotFoundException("SSN not found in external service");
        }

        Citizen citizen = new Citizen();
        citizen.setFullName(request.getFullName());
        citizen.setMobileNumber(request.getMobileNumber());
        citizen.setEmail(request.getEmail());
        citizen.setGender(request.getGender());
        citizen.setSsn(request.getSsn());
        citizen.setDateofBirth(request.getDateofBirth());
        citizen.setStateName(request.getStateName());
        citizen.setCreatedBy("Admin");
        citizen.setUpdatedBy("Admin");
        citizen.setCreatedDate(LocalDate.now());
        citizen.setUpdatedDate(LocalDate.now());

        citizenRepo.save(citizen);

        return new CitizenResponse(citizen, "Citizen registered successfully");
    }

    @Override
    public List<CitizenResponse> updateCitizen(CitizenRequest request) {
        Optional<Citizen> existingCitizenOpt = citizenRepo.findBySsn(request.getSsn());
        if (existingCitizenOpt.isEmpty()) {
            throw new ResourceNotFoundException("Citizen not found with SSN: " + request.getSsn());
        }

        Citizen citizen = existingCitizenOpt.get();
        citizen.setFullName(request.getFullName());
        citizen.setMobileNumber(request.getMobileNumber());
        citizen.setEmail(request.getEmail());
        citizen.setGender(request.getGender());
        citizen.setStateName(request.getStateName());
        citizen.setDateofBirth(request.getDateofBirth());
        citizen.setUpdatedBy("Admin");

        citizenRepo.save(citizen);

        return citizenRepo.findAll().stream()
                .map(c -> new CitizenResponse(c, "Citizen updated successfully"))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCitizen(Long ssn) {
        Optional<Citizen> ct = citizenRepo.findBySsn(ssn);
        if (ct.isPresent()) {
            citizenRepo.delete(ct.get());
            return true;
        } else {
            throw new ResourceNotFoundException("Citizen not found with SSN: " + ssn);
        }
    }

    @Override
    public List<CitizenResponse> getAllCitizens() {
        return citizenRepo.findAll().stream()
                .map(c -> new CitizenResponse(c, "Citizen data fetched successfully"))
                .collect(Collectors.toList());
    }

    @Override
    public CitizenResponse getCitizenBySsn(Long ssn) {
        Citizen citizen = citizenRepo.findBySsn(ssn)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with SSN: " + ssn));
        return new CitizenResponse(citizen, "Citizen found successfully");
    }

    @Override
    public boolean deleteCitizenById(Integer id) {
        if (citizenRepo.existsById(id)) {
            citizenRepo.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Citizen not found with ID: " + id);
        }
    }

    @Override
    public CitizenResponse getCitizenById(Integer id) {
        Citizen citizen = citizenRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen not found with ID: " + id));
        return new CitizenResponse(citizen, "Citizen found successfully");
    }
}
