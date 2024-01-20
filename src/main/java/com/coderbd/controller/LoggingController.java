package com.coderbd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    private static final String LOG_FILE_PATH = "D:\\Spring_book\\app.log";
    private static final int NUMBER_OF_LINES = 1000000;

    @GetMapping("/error")
    public List<String> getErrorLogs() throws IOException {
        return getLogsForLevel("ERROR");
    }

    @GetMapping("/warn")
    public List<String> getWarnLogs() throws IOException {
        return getLogsForLevel("WARN");
    }

    @GetMapping("/info")
    public List<String> getInfoLogs() throws IOException {
        return getLogsForLevel("INFO");
    }

    private List<String> getLogsForLevel(String logLevel) throws IOException {
        List<String> logs = Files.lines(Paths.get(LOG_FILE_PATH))
                .filter(line -> line.contains("[" + logLevel + "]"))
                .skip(Math.max(0, Files.lines(Paths.get(LOG_FILE_PATH)).count() - NUMBER_OF_LINES))
                .collect(Collectors.toList());

        return logs;
    }
}
