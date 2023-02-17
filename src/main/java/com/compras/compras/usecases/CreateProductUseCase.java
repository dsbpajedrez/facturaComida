package com.compras.compras.usecases;

import com.compras.compras.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateProductUseCase implements CreateProduct{
  @Autowired
    WebClient webClient;


  public Mono<String> apply(Producto producto) {
    return webClient.post()
            .uri("/createProduct")
            .body(BodyInserters.fromValue(producto))
            .retrieve()
            .bodyToMono(String.class);
  }
}
