package com.example.Java.Backend.LLM.Assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractedData {
    private String name;
    private String email;
    private String openingBalance;
    private String closingBalance;
    private String password;  // Field for Brownie Point
}

