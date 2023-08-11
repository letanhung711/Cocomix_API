package com.example.Library.repository;

import com.example.Library.model.Market;
import com.example.Library.model.MarketProduct;
import com.example.Library.model.ProductMarketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketProductRepository extends JpaRepository<MarketProduct, Long> {
    List<MarketProduct> findAllByMarket(Market market);
    Optional<MarketProduct> findByProductMarketId(ProductMarketId productMarketId);
}
