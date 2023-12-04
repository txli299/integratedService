package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.Cafe;
import com.gomobile.integratedService.model.CafeDto;
import com.gomobile.integratedService.model.Coordinate;
import com.gomobile.integratedService.repo.CafeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class CafeController {
    @Autowired
    CafeRepository cafeRepository;

    private final  CafeService cafeService;

    public CafeController(CafeRepository cafeRepository, CafeService cafeService) {
        this.cafeRepository = cafeRepository;
        this.cafeService = cafeService;
    }

    @GetMapping("/cafe/allCafes")
    public List<Cafe> getAllMachine(){
        return cafeRepository.findAll();
    }

    @PostMapping("/cafe/getCafe")
    public List<Cafe> getNearCafe(@RequestBody Coordinate coordinate){
        int numberOfCafesToRetrieve = 2;
        List<Cafe> nearestCafes = new ArrayList<>();
        for (Cafe cafe : cafeRepository.findAll()) {
            double distance = calculateDistance(coordinate, cafe.getCoordinate());
            cafe.setDistance(distance);

            // Add the cafe to the nearestCafes list if it's one of the nearest cafes
            if (nearestCafes.size() < numberOfCafesToRetrieve) {
                nearestCafes.add(cafe);
                // Sort the nearest cafes list based on distance
                nearestCafes.sort(Comparator.comparingDouble(Cafe::getDistance));
            } else {
                // If the cafe is closer than the farthest cafe in the list, replace the farthest cafe
                if (distance < nearestCafes.get(nearestCafes.size() - 1).getDistance()) {
                    nearestCafes.remove(nearestCafes.size() - 1);
                    nearestCafes.add(cafe);
                    // Re-sort the nearest cafes list based on distance
                    nearestCafes.sort(Comparator.comparingDouble(Cafe::getDistance));
                }
            }
        }

        return nearestCafes;
    }

    private static double calculateDistance(Coordinate coordinate1, Coordinate coordinate2) {
        // Radius of the Earth in kilometers
        double earthRadius = 6371;

        double lat1 = Math.toRadians(coordinate1.getLatitude());
        double lon1 = Math.toRadians(coordinate1.getLongitude());
        double lat2 = Math.toRadians(coordinate2.getLatitude());
        double lon2 = Math.toRadians(coordinate2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance in kilometers
        double distance = earthRadius * c;

        return distance;
    }

    @PostMapping("/cafe/getCafeList")
    public ResponseEntity<?> getCafeByDistance(@RequestBody Coordinate coordinate){
        try{
            return ResponseEntity.ok(cafeService.getCafeByDistance(coordinate));
        }catch(Exception e) {
            return ResponseEntity.status(500).body("Error Empty order: " + e.getMessage());
        }

    }

    @PostMapping("cafe/getMachineDetail")
    public ResponseEntity<?> getCafeMachine(@RequestBody CafeDto cafe, @RequestParam String type){
        try{
            return ResponseEntity.ok(cafeService.getCafeMachine(cafe.getId(),type));
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error Empty order: " + e.getMessage());
        }
    }
}
