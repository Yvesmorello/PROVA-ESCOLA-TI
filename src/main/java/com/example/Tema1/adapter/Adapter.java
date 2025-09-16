package com.example.Tema1.adapter;

public interface Adapter<T, J>{

    T toEntity(J dto);
    J toDto(T dto);
}
