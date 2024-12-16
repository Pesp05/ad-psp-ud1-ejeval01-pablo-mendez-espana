package com.salesianostriana.dam.resteval;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceRepository placeRepository;

    @GetMapping
    public ResponseEntity<List<Place>> getAll(){
        if(placeRepository.getAll().isEmpty()){
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok(placeRepository.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getOne(@PathVariable Long id){
        Optional<Place> optPlace = placeRepository.get(id);
        if(optPlace.isPresent()){
            return ResponseEntity.ok(optPlace.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Place> createPlace(@RequestBody Place place){
        placeRepository.add(place);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPlace(@PathVariable Long id, @RequestBody Place place){
        Optional<Place> optPlace = placeRepository.get(id);
        if(optPlace.isPresent()){
            placeRepository.edit(id, place);
            return ResponseEntity.ok(place);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable Long id) {
        Optional<Place> optPlace = placeRepository.get(id);
        if (optPlace.isPresent()) {
            placeRepository.delete(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @PutMapping("/{id}/tag/add/{nuevo_tag}")
    public ResponseEntity<?> addTag(@PathVariable Long id, @PathVariable String nuevo_tag){
        Optional<Place> optPlace = placeRepository.get(id);
        if(optPlace.isPresent()){
            Place place = optPlace.get();
            place.addTag(nuevo_tag);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}/tag/remove/{tag}")
    public ResponseEntity<?> removeTag(@PathVariable Long id, @PathVariable String tag){
        Optional<Place> optPlace = placeRepository.get(id);
        if(optPlace.isPresent()){
            Place place = optPlace.get();
            place.removeTag(tag);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
