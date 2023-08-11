package com.example.Library.service.Impl;

import com.example.Library.dto.MarketDto;
import com.example.Library.model.Market;
import com.example.Library.model.MarketProduct;
import com.example.Library.model.Product;
import com.example.Library.model.ProductMarketId;
import com.example.Library.repository.MarketProductRepository;
import com.example.Library.repository.MarketRepository;
import com.example.Library.repository.ProductRepository;
import com.example.Library.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MarketProductRepository marketProductRepository;

    @Override
    public List<Market> getListMarket() {
        return marketRepository.findAll();
    }

    @Override
    public Optional<Market> getMarketInformation(Long id) {
        return marketRepository.findById(id);
    }

    @Override
    public String newMarket(MarketDto marketDto) {
        Market market = new Market();
        market.setName(marketDto.getName());
        market.setPrice_increase(marketDto.getPrice_increase());
        marketRepository.save(market);
        return "Create a new market!";
    }

    @Override
    public String updateMarket(Long id, MarketDto marketDto) {
        Optional<Market> market = marketRepository.findById(id);
        if(market.isEmpty()) {
            return "Not found product market!";
        }
        Market market1 = market.get();
        market1.setName(marketDto.getName());
        market1.setPrice_increase(marketDto.getPrice_increase());
        marketRepository.save(market1);
        return "Update market information!";
    }

    @Override
    public String deleteMarket(Long id) {
        Optional<Market> market = marketRepository.findById(id);
        if(market.isEmpty()) {
            return "Not found product market!";
        }
        marketRepository.deleteById(market.get().getId());
        return "Delete product market!";
    }

    @Override
    public String UpdateProductInformationOnMarket(Long marketId, Long productId, MarketDto marketDto) {
        Optional<Market> market = marketRepository.findById(marketId);
        Optional<Product> product = productRepository.findById(productId);
        if(market.isEmpty()) {
            return "Not found market!";
        }
        if(product.isEmpty()) {
            return "Not found product!";
        }
        ProductMarketId productMarketId = new ProductMarketId();
        productMarketId.setMarketId(market.get().getId());
        productMarketId.setProductId(product.get().getId());

        MarketProduct marketProduct = new MarketProduct();
        marketProduct.setProductMarketId(productMarketId);
        marketProduct.setMarket(market.get());
        marketProduct.setProduct(product.get());
        marketProduct.setOld_price(marketDto.getOld_price());
        marketProduct.setPrice_increase(marketDto.getPrice_increase());
        marketProductRepository.save(marketProduct);
        return "Update product information on the market";
    }

    @Override
    public List<Product> GetProductListOnMarket(Long id) {
        Optional<Market> market = marketRepository.findById(id);
        if(market.isEmpty()) {
            return null;
        }

        List<MarketProduct> marketProducts = marketProductRepository.findAllByMarket(market.get());
        List<Product> products = new ArrayList<>();
        for (MarketProduct marketProduct : marketProducts) {
            Product product = marketProduct.getProduct();
            products.add(product);
        }
        return products;
    }

    @Override
    public String deleteProductFromMarket(Long marketId, Long productId) {
        Optional<Market> market = marketRepository.findById(marketId);
        Optional<Product> product = productRepository.findById(productId);
        if(market.isEmpty()) {
            return "Not found market!";
        }
        if(product.isEmpty()) {
            return "Not found product!";
        }

        ProductMarketId productMarketId = new ProductMarketId();
        productMarketId.setMarketId(market.get().getId());
        productMarketId.setProductId(product.get().getId());

        Optional<MarketProduct> marketProducts = marketProductRepository.findByProductMarketId(productMarketId);
        if(marketProducts.isEmpty()) {
            return "Not found product market!";
        }
        MarketProduct marketProduct = marketProducts.get();
        marketProductRepository.delete(marketProduct);
        return "Delete product from market successful!";
    }
}
