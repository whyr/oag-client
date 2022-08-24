package com.whyr.oagclient.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRsDto {

    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String carrierCode;

}
