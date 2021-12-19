package com.aandp.aandpspring.Service.impl;

import com.aandp.aandpspring.Service.FloorService;
import com.aandp.aandpspring.exeption.DublicateResourceExeption;
import com.aandp.aandpspring.exeption.ResourceNotFoundExeption;
import com.aandp.aandpspring.model.Floor;
import com.aandp.aandpspring.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Floor save(Floor floor) {
        try {
            return floorRepository.save(floor);
        } catch (DataIntegrityViolationException exception) {
            throw new DublicateResourceExeption(String.format("Floor with number &d already exists.", floor) );
        }
    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundExeption(String.format("Floor with %d does not exist.", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption(String.format("Floor with id %d does not exist", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updatedFloor = Floor.builder().id(foundFloor.getId())
                .number(floor.getNumber())
                .build();
        return save(updatedFloor);
    }

    @Override
    public void delete(Long id) {
        floorRepository.findById(id);
    }

    @Override
    public Set<Floor> findAll() {
        return new TreeSet<>(floorRepository.findAll());
    }
}
