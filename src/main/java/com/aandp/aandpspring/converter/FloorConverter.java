package com.aandp.aandpspring.converter;

import com.aandp.aandpspring.dto.FloorDto;
import com.aandp.aandpspring.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDto tofloorDto(Floor floor){
        return FloorDto.builder().number(floor.getNumber()).build();
    }

    public Floor tofloor(FloorDto floorDto){
        return Floor.builder().number(floorDto.getNumber()).build();
    }
}
