package com.example.carro_api.controllers;


import com.example.carro_api.dtos.CarroRequestDTO;
import com.example.carro_api.entidade.Carro;
import com.example.carro_api.repositorios.CarroRepository;
import com.example.carro_api.services.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosController {

    @GetMapping("/listar/{id}")
    public ResponseEntity<Carro> listarById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(carroService.listarById(id));
        } catch (RuntimeException e ) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    private CarroService carroService;

    public CarrosController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listar(){


        return ResponseEntity.ok(carroService.listar());
    }



    @PostMapping("/criar")
    public ResponseEntity<Carro> criar(@RequestBody CarroRequestDTO carro){
        try{
            return ResponseEntity.ok(carroService.criar(carro));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizar(@RequestBody CarroRequestDTO carro, @PathVariable Long id){
       try {
           return ResponseEntity.ok(carroService.atualizar(id,carro));
       } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(null);
       } catch (Exception e) {
           return ResponseEntity.internalServerError().body(null);
       }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
       try{
       carroService.deletar(id);
       return ResponseEntity.ok(null);
   } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(null);
       } catch (Exception e) {
           return ResponseEntity.internalServerError().body(null);
       }
    }
}
