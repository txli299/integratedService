package com.gomobile.integratedService.rest;

import com.gomobile.integratedService.model.*;
import com.gomobile.integratedService.repo.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;




    public  List<CafeDto> getCafeByDistance(Coordinate coordinate) {
        List<CafeDto> nearestCafes = new ArrayList<>();

        for (Cafe cafe : cafeRepository.findAll()) {
            double distance = calculateDistance(coordinate, cafe.getCoordinate());
            cafe.setDistance(distance);
            List<MachineInfo> machInfo = cafeRepository.countActiveMachines(cafe.getName());
            CafeDto cafeDto = new CafeDto(cafe.getId(), cafe.getName(), "Street 123",cafe.getDistance(), machInfo);
            nearestCafes.add(cafeDto);
        }

        nearestCafes.sort((a,b)-> (int) (a.getDistance()-b.getDistance()));
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

    public MachineDto getCafeMachine(String name,String type) {
        return cafeRepository.getDetailMachine(name,type);
    }
}
