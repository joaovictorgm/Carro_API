package com.example.carro_api.services;

import com.example.carro_api.dtos.CarroRequestDTO;
import com.example.carro_api.entidade.Carro;
import com.example.carro_api.repositorios.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    private CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    public List<Carro> listar(){
        return carroRepository.findAll();
    }

    public Carro criar(CarroRequestDTO carro) {
        Carro carroPersist = this.carroRequestDtoParaCarros(carro);

        return carroRepository.save(carroPersist);
    }

    public Carro atualizar(Long id, CarroRequestDTO carro) {
        if(carroRepository.existsById(id)){
            Carro carroPersist = this.carroRequestDtoParaCarros(carro);
            carroPersist.setId(id);

            return carroRepository.save(carroPersist);
        }

        throw new RuntimeException("Carro não encontrado");
    }

    public void deletar(Long id) {
        if (carroRepository.existsById(id)) {
            throw new RuntimeException("Carro não encontrado com o id: " + id);
        }

        carroRepository.deleteById(id);
    }

    public Carro listarById(Long id){
        if(carroRepository.existsById(id)){
            return carroRepository.findById(id).get();

        }
        throw new RuntimeException("Carro não existe!");

    }
    private Carro carroRequestDtoParaCarros(CarroRequestDTO entrada){
        Carro saida = new Carro();
        saida.setModelo(entrada.getModelo());
        saida.setMarca(entrada.getMarca());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;

    }
}
