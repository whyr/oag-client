package com.whyr.oagclient.service;

import com.whyr.oagclient.model.api.FlightInfoResponse;
import com.whyr.oagclient.model.dto.FlightRsDto;
import com.whyr.oagclient.model.service.FlightInfoQueryParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Value("${oag.server.uri}")
    private String oagServerUri;

    @Value("${oag.server.subscription-key}")
    private String subscriptionKey;

    public List<FlightRsDto> getFlights(FlightInfoQueryParams flightInfoQueryParams) {

        FlightInfoResponse flightInfoResponse = getFlightInformation(flightInfoQueryParams);
        List<FlightRsDto> flights = new ArrayList<>(getFlightsData(flightInfoResponse));
        while (flightInfoResponse.getPaging().getNext() == null || !flightInfoResponse.getPaging().getNext().equals("")) {
            flightInfoResponse = getFlightInformation(flightInfoResponse.getPaging().getNext());
            flights.addAll(getFlightsData(flightInfoResponse));
        }

        return flights;

    }

    private static List<FlightRsDto> getFlightsData(FlightInfoResponse flightInfoResponse) {
        return flightInfoResponse.getData().stream()
                .map(data -> FlightRsDto.builder()
                        .flightNumber(data.getCarrierCode().getIata() + data.getFlightNumber())
                        .departureTime(data.getDeparture().getPassengerLocalTime())
                        .arrivalTime(data.getArrival().getPassengerLocalTime())
                        .carrierCode(data.getCarrierCode().getIata())
                        .build())
                .collect(Collectors.toList());
    }

    private FlightInfoResponse getFlightInformation(FlightInfoQueryParams flightInfoQueryParams) {

        return WebClient.create(oagServerUri)
                .get()
                .uri(uriBuilder -> uriBuilder.path("/")
                        .queryParam("DepartureDate", flightInfoQueryParams.getDepartureDate())
                        .queryParam("ArrivalDate", flightInfoQueryParams.getArrivalDate())
                        .queryParam("DepartureAirport", flightInfoQueryParams.getDepartureAirport())
                        .queryParam("ArrivalAirport", flightInfoQueryParams.getArrivalAirport())
                        .queryParam("IataCarrierCode", flightInfoQueryParams.getIataCarrierCode())
                        .queryParam("version", "1.0")
                        .build()
                )
                .headers(createHeaders())
                .retrieve()
                .bodyToMono(FlightInfoResponse.class)
                .block();
    }

    private FlightInfoResponse getFlightInformation(String uri) {

        return WebClient.create(oagServerUri)
                .get()
                .uri(uri)
                .headers(createHeaders())
                .retrieve()
                .bodyToMono(FlightInfoResponse.class)
                .block();
    }

    private Consumer<HttpHeaders> createHeaders() {
        return headers -> {
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
            headers.add("Subscription-Key", subscriptionKey);
        };
    }


}
