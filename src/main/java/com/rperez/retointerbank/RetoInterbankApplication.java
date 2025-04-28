package com.rperez.retointerbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class RetoInterbankApplication {

    private static GenerateReport generateReport;

    @Autowired
    public RetoInterbankApplication(GenerateReport generateReport) {
        RetoInterbankApplication.generateReport = generateReport;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RetoInterbankApplication.class, args);
        generateReport.generateReport();
    }

}

