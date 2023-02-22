package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.repositories.FacturaRepository;
import com.compras.compras.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAllBillsUseCaseTest {

    FacturaRepository repository;

    GetAllBillsUseCase getAllBillsUseCase;
    UserRepository userRepository;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();

        repository = mock(FacturaRepository.class);
        getAllBillsUseCase = new GetAllBillsUseCase( repository, mapperUtils, userRepository);
    }

    @Test
    void getValidationTest(){
        Factura factura = new Factura();
        LocalTime fecha = LocalTime.now();
        List listProducts = new ArrayList();
        FacturaDTO facturaDTO =new FacturaDTO("1", fecha, "cc", "123", "carlos", listProducts, "username_2");
        factura.setId(facturaDTO.getId());
        factura.setIdType(facturaDTO.getIdType());
        factura.setClientId(facturaDTO.getClientId());
        factura.setClientName(facturaDTO.getClientName());
        factura.setProducts(facturaDTO.getProducts());

        when(repository.findAll()).thenReturn(Flux.just(factura));

        StepVerifier.create(getAllBillsUseCase.get())
                .expectNextMatches(factDTO -> {
                    assert factDTO.getId().equals("1");
                    assert factDTO.getIdType().equals("cc");
                    assert factDTO.getClientId().equals("123");
                    assert factDTO.getClientName().equals("carlos");
                    return true;
                })
                .verifyComplete();
        verify(repository).findAll();
    }
}