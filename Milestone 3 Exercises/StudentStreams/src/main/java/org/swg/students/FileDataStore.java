package org.swg.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public abstract class FileDataStore {

    private String filePath;
    private int columnCount;
    private boolean hasHeaders;

    public FileDataStore(String filePath, int columnCount, boolean hasHeaders) {
        this.filePath = filePath;
        this.columnCount = columnCount;
        this.hasHeaders = hasHeaders;
    }

    public void runPerLine(Consumer<String[]> lineFunc) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            if (hasHeaders) { // throw out header...
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                if (tokens.length == columnCount) {
                    lineFunc.accept(tokens);
                }
            }
        }
    }

}
