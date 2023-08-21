package com.weather.service;

import com.weather.exception.BusinessException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CsvWriterServiceTest {

    @Spy
    private CsvWriterService service;

    @Test
    void initializeCsvFile() {
        ReflectionTestUtils.setField(service, "csvFile", "test.csv");
        var file = service.initializeCsvFile();
        assertTrue(file.isPresent());
    }

    @Test
    @SneakyThrows
    void writeToCsv() {
        ReflectionTestUtils.setField(service, "csvFile", "test.csv");
        var file = Optional.of(new FileWriter("test.csv"));
        Assertions.assertDoesNotThrow(() -> service.writeToCsv(file, "line"));
    }

    @Test
    @SneakyThrows
    void writeToCsv_throwException_whenNoFile() {
        Assertions.assertThrows(BusinessException.class, () -> service.writeToCsv(Optional.empty(), "line"));
    }
}