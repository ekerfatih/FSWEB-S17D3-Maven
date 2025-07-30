package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KoalaController {
    Map<Integer, Koala> koalas;

    public KoalaController() {
        koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public List<Koala> getKoalaList() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/koalas/{id}")
    public Koala getKoalaById(@PathVariable int id) {
        if (!koalas.containsKey(id)) throw new ZooException("This id is not valid", HttpStatus.NOT_FOUND);
        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public Koala addKoala(@RequestBody Koala kangaroo) {
        koalas.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/koalas/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala updatedKangaroo) {
        Koala kangaroo = koalas.get(id);
        BeanUtils.copyProperties(updatedKangaroo, kangaroo, "id");
        return kangaroo;
    }

    @DeleteMapping("koalas/{id}")
    public Koala deleteKoala(@PathVariable int id) {
        return koalas.remove(id);
    }
}
