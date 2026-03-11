package com.productos.productos.dto;

public record AuthResponse(
    String token,
    String username,
    String rol
) {}
