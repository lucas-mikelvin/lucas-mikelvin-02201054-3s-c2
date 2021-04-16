package br.com.bandtec.ac2springboot.repositorio;

import br.com.bandtec.ac2springboot.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {
    List<Lutador> findByVivoTrue();
    List<Lutador> findByVida(Double vida);
}
