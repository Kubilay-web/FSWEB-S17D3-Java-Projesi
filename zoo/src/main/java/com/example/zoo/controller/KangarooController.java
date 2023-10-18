package com.example.zoo.controller;

import com.example.zoo.entity.Kangaroo;
import com.example.zoo.entity.Koala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KangarooController {

    private Map<Long, Kangaroo> kangaroos;

    @Autowired
    public KangarooController() {
        kangaroos = new HashMap<>();
    }

    private long kangarooIdCounter=1;

    @RequestMapping(value = "/kangaroos",method = RequestMethod.GET)
    public Map<Long, Kangaroo> getAll(){
        return kangaroos;
    }

    @RequestMapping(value = "/kangaroos/{id}",method = RequestMethod.GET)
    public Kangaroo getById(@PathVariable Long id){

        return kangaroos.get(id);
    }

    @RequestMapping(value = "/kangaroos",method = RequestMethod.POST)
    public Kangaroo PostKangaroo(@RequestBody Kangaroo kangaroo){
        kangaroo.setId(kangarooIdCounter++);
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @RequestMapping(value = "/kangaroos/{id}", method = RequestMethod.PUT)
    public String putKangaroo(@PathVariable Long id, @RequestBody Kangaroo updatedKangaroo) {

        Kangaroo existingKangarooAnimal = kangaroos.get(id);

        if (existingKangarooAnimal != null) {
            existingKangarooAnimal.setName(updatedKangaroo.getName());
            existingKangarooAnimal.setHeight(updatedKangaroo.getHeight());
            existingKangarooAnimal.setWeight(updatedKangaroo.getWeight());
            existingKangarooAnimal.setGender(updatedKangaroo.getGender());
            existingKangarooAnimal.setAggresive(updatedKangaroo.isAggresive());
            return "Succesful changed";
        } else {
            return "Id not found";
        }
    }

    @RequestMapping(value = "/kangaroos/{id}", method = RequestMethod.DELETE)
    public String deleteKangaroo(@PathVariable Long id) {
        Kangaroo existingKangarooAnimal = kangaroos.get(id);

        if (existingKangarooAnimal != null) {
            kangaroos.remove(id);
            return "Başarıyla silindi.";
        } else {
            return "Id not found";
        }
    }

}
