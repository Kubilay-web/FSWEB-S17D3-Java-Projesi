package com.example.zoo.controller;

import com.example.zoo.entity.Kangaroo;
import com.example.zoo.entity.Koala;
import com.example.zoo.exceptions.KoalaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Long, Koala> koalas;

    @Autowired
    public KoalaController() {
        koalas = new HashMap<>();
    }

    private long koalasIdCounter =1;

    @RequestMapping(value = "/koalas",method = RequestMethod.GET)
    public Map<Long, Koala> getAll(){
        return koalas;
    }

    @RequestMapping(value = "/koalas/{id}", method = RequestMethod.GET)
    public Koala getById(@PathVariable Long id) {
        Koala koala = koalas.get(id);
        if (koala == null) {
            throw new KoalaNotFoundException("Koala not found with ID: " + id);
        }
        return koala;
    }

    @RequestMapping(value = "/koalas",method = RequestMethod.POST)
    public Koala PostKoala(@RequestBody Koala koala){
        koala.setId(koalasIdCounter++);
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @RequestMapping(value = "/koalas/{id}", method = RequestMethod.PUT)
    public String putKoala (@PathVariable Long id, @RequestBody Koala updatedkoala) {

        Koala existingKoalaAnimal  = koalas.get(id);

        if (existingKoalaAnimal != null) {
            existingKoalaAnimal.setName(updatedkoala.getName());
            existingKoalaAnimal.setWeight(updatedkoala.getWeight());
            existingKoalaAnimal.setGender(updatedkoala.getGender());
            return "Succesful changed";
        } else {
            return "Id not found";
        }
    }

    @RequestMapping(value = "/koalas/{id}", method = RequestMethod.DELETE)
    public String deleteKoalas (@PathVariable Long id) {
        Koala existingKoalaAnimal = koalas.get(id);

        if (existingKoalaAnimal != null) {
            koalas.remove(id);
            return "Başarıyla silindi.";
        } else {
            return "Id not found";
        }
    }



}
