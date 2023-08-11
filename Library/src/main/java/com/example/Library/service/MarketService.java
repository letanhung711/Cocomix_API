package com.example.Library.service;

import com.example.Library.dto.MarketDto;
import com.example.Library.model.Market;
import com.example.Library.model.MarketProduct;
import com.example.Library.model.Product;
import com.example.Library.model.ProductMarketId;

import java.util.List;
import java.util.Optional;

public interface MarketService {
    List<Market> getListMarket();
    Optional<Market> getMarketInformation(Long id);
    String newMarket(MarketDto marketDto);
    String updateMarket(Long id, MarketDto marketDto);
    String deleteMarket(Long id);
    String UpdateProductInformationOnMarket(Long marketId, Long productId, MarketDto marketDto);
    List<Product> GetProductListOnMarket(Long id);
    String deleteProductFromMarket(Long marketId, Long productId);
}
