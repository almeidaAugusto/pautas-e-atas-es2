package com.es2.pautas_e_atas.service;

import com.es2.pautas_e_atas.domain.Reuniao.Pauta;
import com.es2.pautas_e_atas.domain.Reuniao.PautaRequestDTO;
import com.es2.pautas_e_atas.repositories.PautaRepository;
import com.es2.pautas_e_atas.repositories.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public Pauta criarPauta(PautaRequestDTO data){
        Pauta pauta = new Pauta();
        pauta.setReuniao(reuniaoRepository.findById(data.reuniaoId()).orElseThrow(() -> new RuntimeException("Reunião não encontrada")));
        pauta.setTitulo(data.titulo());
        pauta.setDescricao(data.descricao());
        return pautaRepository.save(pauta);
    }
}
