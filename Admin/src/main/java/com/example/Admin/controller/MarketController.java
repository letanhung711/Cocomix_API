package com.example.Admin.controller;

import com.example.Library.dto.MarketDto;
import com.example.Library.model.Market;
import com.example.Library.model.MarketProduct;
import com.example.Library.model.Product;
import com.example.Library.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @PostMapping("")
    public ResponseEntity<String> newMarket(@RequestBody MarketDto marketDto) {
        return ResponseEntity.ok(marketService.newMarket(marketDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMarket(@PathVariable("id") Long id,
                                               @RequestBody MarketDto marketDto) {
        return ResponseEntity.ok(marketService.updateMarket(id, marketDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarket(@PathVariable("id") Long id) {
        return ResponseEntity.ok(marketService.deleteMarket(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Market>> getMarket() {
        return ResponseEntity.ok(marketService.getListMarket());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMarket(@PathVariable("id") Long id) {
        Optional<Market> market = marketService.getMarketInformation(id);
        if(market.isEmpty()) {
            return ResponseEntity.badRequest().body("Not found market!");
        }
        return ResponseEntity.ok(market);
    }

    @PutMapping("/{marketId}/product/{productId}")
    public ResponseEntity<String> UpdateProductOnMarket(@PathVariable("marketId") Long marketId,
                                                        @PathVariable("productId") Long productId,
                                                        @RequestBody MarketDto marketDto) {
        return ResponseEntity.ok(marketService.UpdateProductInformationOnMarket(marketId, productId, marketDto));
    }

    @GetMapping("/{marketId}/product")
    public ResponseEntity<?> GetProductListOnMarket(@PathVariable("marketId") Long marketId) {
        List<Product> products = marketService.GetProductListOnMarket(marketId);
        if(products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{marketId}/product/{productId}")
    public ResponseEntity<String> DeleteProductFromMarket(@PathVariable("marketId") Long marketId,
                                                          @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(marketService.deleteProductFromMarket(marketId, productId));
    }
}
