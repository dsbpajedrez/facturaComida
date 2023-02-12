package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.repositories.FacturaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateFacturaUseCaseTest {
    @Autowired
    MapperUtils mapperUtils;
    @Mock
    FacturaRepository facturaRepository;
    @SpyBean
    CreateFacturaUseCase createFacturaUseCase;
    @Test
    void apply() {
        Factura factura = new Factura();
        LocalTime fecha = LocalTime.now();
        List listProducts = new ArrayList();
        FacturaDTO facturaDTO =new FacturaDTO("1", fecha, "cc", "123", "carlos", listProducts);
        factura.setId(facturaDTO.getId());
        factura.setIdType(facturaDTO.getIdType());
        factura.setClientId(facturaDTO.getClientId());
        factura.setClientName(facturaDTO.getClientName());
        factura.setProducts(facturaDTO.getProducts());

        Mockito.when(facturaRepository.save(factura))
                .thenReturn(
                        Mono.just(
                                mapperUtils.mapperToFactura(
                                                facturaDTO.getId()
                                        )
                                        .apply(facturaDTO)
                        )
                );
        StepVerifier.create(createFacturaUseCase.apply(facturaDTO))
                .expectNextMatches(pDto->{
                    assert facturaDTO.getId().equals("1");
                    return true;
                }).verifyComplete();

    }
}