//package com.example.client;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.example.binding.CitizenRequest;
//import com.example.binding.CitizenResponse;
//
//import lombok.Data;
//import reactor.core.publisher.Mono;
//
//@Component
//public class SsnValidationClient {
//
//
//    private final WebClient webClient;
//
//    public SsnValidationClient(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://localhost:9090").build(); // Project B base URL
//    }
//
//    public boolean validateCitizen(CitizenRequest citizen) {
//    	CitizenResponse  response = webClient.post()
//                .uri("/ssn-api/ssnu") // Path on Project B
//                .bodyValue(citizen)
//                .retrieve()
//                .bodyToMono(CitizenResponse.class)
//                .block(); // blocking call for simplicity
//		return false;
//        
//     // Return only the status boolean
//      
//    }
//}
