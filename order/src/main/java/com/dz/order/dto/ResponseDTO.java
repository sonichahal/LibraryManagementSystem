package com.dz.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseDTO<T> implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    private List<T> data = new ArrayList();
    private String nextLink;
    private int count;

    public ResponseDTO(List<T> data, String nextLink, int count) {
        this.data = data;
        this.nextLink = nextLink;
        this.count = count;
    }

    public ResponseDTO() {
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getNextLink() {
        return this.nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return "{ data= " + this.data + " ,\n" + "nextLink = " + this.nextLink + " ,\n" + "count = " + this.count + "}";
    }
}
