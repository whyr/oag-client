package com.whyr.oagclient.model.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightInfo {

    private CarrierCode carrierCode;
    private Integer flightNumber;
    private Direction departure;
    private Direction arrival;

}
