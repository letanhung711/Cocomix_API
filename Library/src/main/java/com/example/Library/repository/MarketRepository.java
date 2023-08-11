package com.example.Library.repository;

import com.example.Library.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    boolean existsByName(String name);
}
