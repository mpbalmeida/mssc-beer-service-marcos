package dev.marcosalmeida.msscbeerservice.repository;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
