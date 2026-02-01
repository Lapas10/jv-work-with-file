package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        long supplySum = 0;
        long buySum = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                String operation = parts[0].trim();
                long amount = Long.parseLong(parts[1].trim());

                if (operation.equals("supply")) {
                    supplySum += amount;
                }
                if (operation.equals("buy")) {
                    buySum += amount;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fromFileName, e);
        }

        long result = supplySum - buySum;

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write("supply," + supplySum);
            writer.newLine();

            writer.write("buy," + buySum);
            writer.newLine();

            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can`t write file " + toFileName, e);
        }
    }
}
