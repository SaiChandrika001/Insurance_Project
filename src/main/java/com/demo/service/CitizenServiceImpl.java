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

	 // Call SSN service
    SsnResponse ssnData = webClientBuilder.build()
            .get()
            .uri("http://localhost:9090/api/{ssn}", request.getSsn())
            .retrieve()
            .bodyToMono(SsnResponse.class)
            .block();

    if (ssnData == null) {
        throw new RuntimeException("SSN not found in SSN service");
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

    CitizenResponse response = new CitizenResponse();
    response.setCitizen(citizen);
    response.setMessage("Citizen registered successfully");

    return response;

}

public List<CitizenResponse> updateCitizen(CitizenRequest request) {
    // Find existing citizen by SSN
    Optional<Citizen> existingCitizenOpt = citizenRepo.findBySsn(request.getSsn());

    if (existingCitizenOpt.isEmpty()) {
        throw new RuntimeException("Citizen not found with SSN: " + request.getSsn());
    }

    Citizen citizen = existingCitizenOpt.get();

    // Update fields
    citizen.setFullName(request.getFullName());
    citizen.setMobileNumber(request.getMobileNumber());
    citizen.setEmail(request.getEmail());
    citizen.setGender(request.getGender());
    citizen.setStateName(request.getStateName());
    citizen.setDateofBirth(request.getDateofBirth());
    citizen.setUpdatedBy("Admin");

    citizenRepo.save(citizen);

    // Convert all citizens to response list
    return citizenRepo.findAll().stream().map(c -> {
        CitizenResponse res = new CitizenResponse();
        res.setCitizen(citizen);
        res.setMessage("Citizen updated successfully");
        return res;
    }).collect(Collectors.toList());
}


@Override
public boolean deleteCitizen(Long ssn) {
    Optional<Citizen> ct = citizenRepo.findBySsn(ssn);

    if (ct.isPresent()) {
        citizenRepo.delete(ct.get());
        return true;
    } else {
        throw new RuntimeException("Citizen not found with SSN: " + ssn);
    }
}

@Override
public List<CitizenResponse> getAllCitizens() {
    return citizenRepo.findAll().stream().map(c -> {
        CitizenResponse res = new CitizenResponse();
        res.setCitizen(c);
        res.setMessage("Citizen data fetched successfully");
        return res;
    }).collect(Collectors.toList());
}

@Override
public CitizenResponse getCitizenBySsn(Long ssn) {
    Optional<Citizen> citizenOpt = citizenRepo.findBySsn(ssn);
    if (citizenOpt.isEmpty()) {
        throw new RuntimeException("Citizen not found with SSN: " + ssn);
    }
    Citizen c = citizenOpt.get();
    CitizenResponse res = new CitizenResponse();
    res.setCitizen(c);
    res.setMessage("Citizen found successfully");
    return res;
}
@Override
public boolean deleteCitizenById(Integer id) {
    if (citizenRepo.existsById(id)) {
    	citizenRepo.deleteById(id);
        return true;
    }
    return false;
}
private CitizenResponse mapToResponse(Citizen citizen) {
    CitizenResponse response = new CitizenResponse();
    response.setCitizen(citizen); // assuming CitizenResponse has a `setCitizen` method
    response.setMessage("Citizen data fetched successfully");
    return response;
}

@Override
public CitizenResponse getCitizenById(Integer id) {
    Optional<Citizen> citizen = citizenRepo.findById(id);
    return citizen.map(this::mapToResponse).orElse(null);
}
}
