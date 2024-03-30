package com.miniproject.skybook.services.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.miniproject.skybook.model.dto.FlightDTO;
import com.miniproject.skybook.model.entity.Flight;
import com.miniproject.skybook.repository.FlightRepository;
import com.miniproject.skybook.services.FlightServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightServices {
    private final FlightRepository flightRepository;
    @Override
    public Flight saveFlight(FlightDTO flightDTO) {
        //Only implement flights from:
        // Jakarta to Tokyo (vice versa)
        // Jakarta to London (vice versa)
        // Jakarta to Los Angeles (vice versa)
        String flightId = "flight_"+ NanoIdUtils.randomNanoId();
        String ori = flightDTO.getOrigin();
        String desti = flightDTO.getDestination();
        List<List<String>> timzone= new ArrayList<>();
        Set<String> zids = ZoneId.getAvailableZoneIds();
        String[] cityNames = { ori, desti };
        for (String cityName : cityNames) {
            String tzCityName = Normalizer.normalize(cityName, Normalizer.Form.NFKD)
                    .replaceAll("[^\\p{ASCII}-_ ]", "")
                    .replace(' ', '_');
            List<String> possibleTimeZones = zids.stream()
                    .filter(zid -> zid.endsWith("/" + tzCityName))
                    .collect(Collectors.toList());
            timzone.add(possibleTimeZones);
        }
        ZoneId leavingZone = ZoneId.of(timzone.get(0).get(0));
        ZoneId arrivingZone = ZoneId.of(timzone.get(1).get(0));
        LocalDateTime leaving = null;
        ZonedDateTime departure = null;
        ZonedDateTime arrival = null;
        if((String.valueOf(leavingZone).contains("Jakarta") && String.valueOf(arrivingZone).contains("Tokyo")) || ((String.valueOf(leavingZone).contains("Tokyo") && String.valueOf(arrivingZone).contains("Jakarta")))){
            leaving = LocalDateTime.now().plusDays(1).withHour(21).withMinute(0).withSecond(0);
            departure = ZonedDateTime.of(leaving, leavingZone);
            arrival = departure.withZoneSameInstant(arrivingZone)
                    .plusHours(7).plusMinutes(25);
        }else if((String.valueOf(leavingZone).contains("Jakarta") && String.valueOf(arrivingZone).contains("London")) || ((String.valueOf(leavingZone).contains("London") && String.valueOf(arrivingZone).contains("Jakarta")))){
            leaving = LocalDateTime.now().plusDays(1).withHour(18).withMinute(30).withSecond(0);
            departure = ZonedDateTime.of(leaving, leavingZone);
            arrival = departure.withZoneSameInstant(arrivingZone)
                    .plusHours(16).plusMinutes(45);
        }else if((String.valueOf(leavingZone).contains("Jakarta") && String.valueOf(arrivingZone).contains("Los Angeles")) || ((String.valueOf(leavingZone).contains("Los Angeles") && String.valueOf(arrivingZone).contains("Jakarta")))){
            leaving = LocalDateTime.now().plusDays(1).withHour(16).withMinute(55).withSecond(0);
            departure = ZonedDateTime.of(leaving, leavingZone);
            arrival = departure.withZoneSameInstant(arrivingZone)
                    .plusHours(21).plusMinutes(30);
        }


        return flightRepository.save(Flight.builder()
                        .id(flightId)
                        .arrivalTime(arrival.toLocalDateTime())
                        .availableSeats(flightDTO.getAvailableSeats())
                        .departureTime(departure.toLocalDateTime())
                        .destination(flightDTO.getDestination())
                        .origin(flightDTO.getOrigin())
                        .price(flightDTO.getPrice())
                        .totalSeats(flightDTO.getTotalSeats())
                .build());
    }

    @Override
    public Flight getFlightbyId(String id) {
        return flightRepository.getFlightbyId(id);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlightbyId(Flight flight) {
        System.out.println(flight);
        if(flightRepository.findById(flight.getId()).isPresent()){
            return flightRepository.save(flight);
        }else{
            throw new RuntimeException("Flight with id: "+flight.getId()+" not found!");
        }
    }

    @Override
    public String deleteFlightbyId(String id) {
        if(flightRepository.existsById(id)){
            flightRepository.delete(flightRepository.getReferenceById(id));
            return "Flight with id("+id+") has been successfully deleted";
        }else{
            throw new RuntimeException("Flight with id: "+id+" not found!");
        }
    }
}
