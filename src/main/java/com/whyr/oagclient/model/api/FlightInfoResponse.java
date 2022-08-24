package com.whyr.oagclient.model.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightInfoResponse {

    private List<FlightInfo> data;
    private Paging paging;

}
