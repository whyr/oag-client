package com.whyr.oagclient.controller;

import com.whyr.oagclient.model.dto.FlightRsDto;
import com.whyr.oagclient.model.service.FlightInfoQueryParams;
import com.whyr.oagclient.service.exception.BadRequestException;
import com.whyr.oagclient.service.FlightService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@Tag(name = "Flight information")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/")
    public ResponseEntity<List<FlightRsDto>> getFlights(
            @Parameter(required = true) @RequestParam String departureAirport,
            @Parameter(required = true) @RequestParam String arrivalAirport,
            @Parameter(description = "format YYYY-MM-DD") @RequestParam(required = false) @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
            @Parameter(description = "format YYYY-MM-DD") @RequestParam(required = false) @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
            @Parameter @RequestParam(required = false) String iataCarrierCode
    ) {

        if (departureDate == null && arrivalDate == null) {
            throw new BadRequestException("departureDate or arrivalDate is mandatory.");
        }

        FlightInfoQueryParams flightInfoQueryParams = FlightInfoQueryParams.builder()
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .iataCarrierCode(iataCarrierCode)
                .build();

        return new ResponseEntity<>(flightService.getFlights(flightInfoQueryParams), HttpStatus.OK);
    }

}
