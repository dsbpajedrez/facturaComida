package com.compras.compras.usecases;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    public Function<FacturaDTO, Factura> mapperToFactura(String id) {
        return updateFactura -> {
            var facturaDTO = new Factura();
            facturaDTO.setClientId(updateFactura.getClientId());
            facturaDTO.setId(id);
            facturaDTO.setDate(updateFactura.getDate());
            facturaDTO.setClientName(updateFactura.getClientName());
            facturaDTO.setIdType(updateFactura.getIdType());
            facturaDTO.setProducts(updateFactura.getProducts());
            facturaDTO.setUsername(updateFactura.getUsername());
            return facturaDTO;
        };
    }

    public Function<Factura, FacturaDTO> mapEntityToProducto() {
        return entity -> new FacturaDTO(
                entity.getId(),
                entity.getDate(),
                entity.getIdType(),
                entity.getClientId(),
                entity.getClientName(),
                entity.getProducts(),
                entity.getUsername()
        );
    }
}

