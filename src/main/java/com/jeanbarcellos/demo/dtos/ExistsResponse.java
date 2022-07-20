package com.jeanbarcellos.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ExistsResponse {

    private boolean exists;

    public static ExistsResponse of(boolean exists) {
        return ExistsResponse
                .builder()
                .exists(exists)
                .build();
    }

}
