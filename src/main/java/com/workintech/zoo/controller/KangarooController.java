package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KangarooController {
    Map<Integer, Kangaroo> kangaroos;

    public KangarooController() {
        kangaroos = new HashMap<>();
    }

    @GetMapping("/kangaroos")
    public List<Kangaroo> getKangarooList() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/kangaroos/{id}")
    public Kangaroo getKangarooById(@PathVariable int id) {
        if (!kangaroos.containsKey(id)) throw new ZooException("This id is not valid", HttpStatus.NOT_FOUND);
        return kangaroos.get(id);
    }

    @PostMapping("/kangaroos")
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if(kangaroo.getId() == null) throw new ZooException("Invalid entry",HttpStatus.BAD_REQUEST);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/kangaroos/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo updatedKangaroo) {
        Kangaroo kangaroo = kangaroos.get(id);
        BeanUtils.copyProperties(updatedKangaroo, kangaroo, "id");
        return kangaroo;
    }

    @DeleteMapping("kangaroos/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id) {
        return kangaroos.remove(id);
    }
}
