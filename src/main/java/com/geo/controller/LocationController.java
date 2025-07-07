package com.geo.controller;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.geo.entity.LocationEntity;
import com.geo.service.LocationService;
@CrossOrigin
@RestController
@RequestMapping("/locations")
public class LocationController {
	
	@Autowired
	private LocationService locService;


    

    @PostMapping
    public LocationEntity addLocation(@RequestParam String name,
                                      @RequestParam double lat,
                                      @RequestParam double lon) {
        return locService.saveLocation(name, lat, lon);
    }

    @GetMapping
    public List<LocationEntity> getAllLocations() {
        return locService.getAll();
    }
    

    
//    @GetMapping("/{district}")
//    public ResponseEntity<?> getDistrictBoundary(@PathVariable String district) {
//        try {
//            String query = "[out:json];relation[\"name\"=\"" + district + "\"];out geom;";
//
//            String overpassUrl = "https://overpass.kumi.systems/api/interpreter";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // required for Overpass POST
//            HttpEntity<String> request = new HttpEntity<>("data=" + query, headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> response = restTemplate.postForEntity(overpassUrl, request, String.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(response.getBody());
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No district found.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Something went wrong: " + e.getMessage());
//        }
//    }

    
    
    @GetMapping("/{district}")
    public ResponseEntity<?> getDistrictBoundary(@PathVariable String district) {
        try {
            String query = "[out:json];relation[\"name\"=\"" + district + "\"];out geom;";
            String overpassUrl = "https://overpass.kumi.systems/api/interpreter";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> request = new HttpEntity<>("data=" + query, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(overpassUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No district found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }

//    @GetMapping("/states")
//    public ResponseEntity<?> getAllStateBoundaries() {
//        try {
//            String query = """
//                [out:json][timeout:60];
//                area["name"="India"]["admin_level"="2"]->.a;
//                (
//                  relation["boundary"="administrative"]["admin_level"="4"](area.a);
//                );
//                out geom;
//            """;
//
//            String overpassUrl = "https://overpass.kumi.systems/api/interpreter";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            HttpEntity<String> request = new HttpEntity<>("data=" + query, headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> response = restTemplate.postForEntity(overpassUrl, request, String.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(response.getBody());
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State boundaries not found.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error while fetching state boundaries: " + e.getMessage());
//        }
//    }
//    
    


    	
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
}

