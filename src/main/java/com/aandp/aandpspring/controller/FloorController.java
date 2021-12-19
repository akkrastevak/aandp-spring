package com.aandp.aandpspring.controller;

import com.aandp.aandpspring.Service.FloorService;
import com.aandp.aandpspring.converter.FloorConverter;
import com.aandp.aandpspring.dto.FloorDto;
import com.aandp.aandpspring.model.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "floors")
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService, FloorController floorController, FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll(){
        return ResponseEntity.ok(floorService.findAll().stream().map(floorConverter::tofloorDto).collect(Collectors.toSet()));
    }

    @PostMapping
    public ResponseEntity<FloorDto> save(@RequestBody FloorDto floorDto) {
        Floor floor = floorConverter.tofloor(floorDto);
        Floor savedFloor = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.tofloorDto(savedFloor));
    }

    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        floorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
