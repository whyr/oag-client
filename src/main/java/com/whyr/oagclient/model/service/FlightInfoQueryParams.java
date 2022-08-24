package com.whyr.oagclient.model.service;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInfoQueryParams {

    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String departureAirport;
    private String arrivalAirport;
    private String iataCarrierCode;

}
