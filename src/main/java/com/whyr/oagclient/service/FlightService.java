package com.whyr.oagclient.service;

import com.whyr.oagclient.model.dto.FlightRsDto;
import com.whyr.oagclient.model.service.FlightInfoQueryParams;

import java.util.List;

public interface FlightService {

    List<FlightRsDto> getFlights(FlightInfoQueryParams flightInfoQueryParams);
}
