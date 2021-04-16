package br.com.bandtec.ac2springboot.controle;

import br.com.bandtec.ac2springboot.dominio.Lutador;
import br.com.bandtec.ac2springboot.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {
    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutador(@RequestBody Lutador novoLutador) {
        if (novoLutador.getForcaGolpe() < 0.0) {
            return ResponseEntity.status(400).body("A forçaGolpe deve ser um numero real positivo");
        }
        novoLutador.setVida(100.0);
        novoLutador.setConcentracoesRealizadas(0);
        novoLutador.setVivo(true);
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getLutadores(){
        List<Lutador> lutadores = repository.findAll();

        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lutadores);
        }
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getLutadoresVivos(){
        return ResponseEntity.status(200).body("Lutadores vivos: "+repository.findByVivoTrue().size());
    }

    @GetMapping("/mortos")
    public ResponseEntity getLutadoresMortos(){
        return ResponseEntity.status(200).body(repository.findByVida(0.0));
    }

    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody Integer idLutador1, @RequestBody Integer idLutador2 ){
        return ResponseEntity.status(200).body(repository.findByVida(0.0));
    }
}
