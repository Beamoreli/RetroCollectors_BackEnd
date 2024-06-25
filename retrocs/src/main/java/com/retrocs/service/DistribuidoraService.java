package com.retrocs.service;

import com.retrocs.model.Distribuidora;
import com.retrocs.repository.DistribuidoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DistribuidoraService {

    @Autowired
    private DistribuidoraRepository distribuidoraRepository;

    public List
            <Distribuidora> getAllDistribuidoras() {
        return distribuidoraRepository.findAll();
    }

    public Distribuidora saveDistribuidora(Distribuidora distribuidora) {
        return distribuidoraRepository.save(distribuidora);
    }

    public Distribuidora updateDistribuidora(Integer id, Distribuidora distribuidoraDetails) {
        Distribuidora distribuidora = distribuidoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distribuidora não encontrada"));
        distribuidora.setNome(distribuidoraDetails.getNome());
        return distribuidoraRepository.save(distribuidora);
    }

    public void deleteDistribuidora(Integer id) {
        distribuidoraRepository.deleteById(id);
    }
}
