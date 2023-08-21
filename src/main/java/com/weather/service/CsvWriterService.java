package com.weather.service;

import com.weather.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class CsvWriterService {

    @Value("${csv.file.path}")
    private String csvFile;

    private final static String CSV_HEADERS = "City, Temperature, Wind \n";

    public Optional<FileWriter> initializeCsvFile() {
        try {
            log.info(String.format("Initialize CSV File: %s", csvFile));
            var file = new FileWriter(csvFile, false);
            file.write(CSV_HEADERS);
            file.flush();
            file.close();
            return Optional.of(file);
        } catch (IOException e) {
            log.error(String.format("Error on opening the file %s", e.getMessage()));
            return Optional.empty();
        }
    }

    public void writeToCsv(Optional<FileWriter> optFile, String cvsLine) {
        try {
            if (optFile.isEmpty()) {
                throw new BusinessException("No file provided");
            }
            var file = optFile.get();
            file = new FileWriter(csvFile, true);
            log.info(String.format("Writing CSV line %s", cvsLine));
            file.write(cvsLine);
            file.close();
        } catch (IOException e) {
            log.error(String.format("Error on file handling %s", e.getMessage()));
        }
    }
}
