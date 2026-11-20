package org.example.writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface OrderResultWriter {
    void write(Map<String, BigDecimal> map) throws IOException;
}
