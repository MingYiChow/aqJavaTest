package com.example.cryptoSystem.response;

import com.example.cryptoSystem.model.Data;

import java.util.List;

public class HuobiResponse {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
