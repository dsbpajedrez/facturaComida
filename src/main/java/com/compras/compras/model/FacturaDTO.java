package com.compras.compras.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FacturaDTO {
    @Id
    private String id;
    private Date date;
    private String idType;
    @NotBlank
    private String clientId;
    private String clientName;
    private List products;

    public FacturaDTO() {

    }

    public FacturaDTO(String id, Date date, String idType, String clientId, String clientName, List products) {
        this.id = id;
        this.date = date;
        this.idType = idType;
        this.clientId = clientId;
        this.clientName = clientName;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaDTO productoDTO = (FacturaDTO)  o;
        return Objects.equals(id, productoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "id='" + id + '\'' +
                '}';
    }
}
