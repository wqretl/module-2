package org.example.writer;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

public class FileOrderResultWriter implements OrderResultWriter {
    public FileOrderResultWriter(File file) throws IOException {
        this.FILE = file;
    }
    private final File FILE ;

    @Override
    public void write(Map<String, BigDecimal> map) throws IOException {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {

                for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                    String companyName = entry.getKey();
                    BigDecimal orderCost = entry.getValue();
                    bw.write(companyName);
                    bw.write(" - ");
                    bw.write(orderCost.toString());
                    bw.newLine();
                }

            }
        }
    }

