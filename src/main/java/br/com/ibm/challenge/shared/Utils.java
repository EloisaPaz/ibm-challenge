package br.com.ibm.challenge.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Utils {
    public String generateAccountId() {
        return UUID.randomUUID().toString();
    }
}