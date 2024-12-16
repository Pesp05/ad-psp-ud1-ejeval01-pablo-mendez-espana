package com.salesianostriana.dam.resteval;

import java.util.List;

public record PlaceDto(
        String name,
        String address,
        String cords,
        String desc,
        List<String> tags,
        String image
) {

    public static PlaceDto of (Place place) {
        return new PlaceDto(
                place.getName(),
                place.getAddress(),
                place.getCoords(),
                place.getDesc(),
                place.getTags(),
                place.getImage()
        );
    }

}
