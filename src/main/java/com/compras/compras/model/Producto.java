package com.compras.compras.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    private String id;
    @NotBlank
    private String name;
    private Integer inInventory;
    private Boolean enable;
    private Integer min;
    private  Integer max;
    private String url;
    private Boolean state;
    private Long price;

    public Producto() {

    }

    public Producto(String id, String name, Integer inInventory, Boolean enable, Integer min, Integer max, String url, Boolean state, Long price) {
        this.id = id;
        this.name = name;
        this.inInventory = inInventory;
        this.enable = enable;
        this.min = min;
        this.max = max;
        this.url = url;
        this.state = state;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInInventory() {
        return inInventory;
    }

    public void setInInventory(Integer inInventory) {
        this.inInventory = inInventory;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "AnswerDTO{" +
                "id='" + id + '\'' +
                '}';
    }
}
