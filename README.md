# PO_ProjektySemestralne1
Odczyty plików z svg

z Files.readString

import java.nio.file.*;

public class SvgReader {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("path/to/file.svg");
        String content = Files.readString(path);
        System.out.println(content);
    }
}
z bufferedreader
import java.io.*;

public class SvgReader {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("path/to/file.svg"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




Napisz klasę DeathCauseStatistic, posiadającą jako prywatne pola kod ICD-10 choroby oraz tablicę liczb zgonów w kolejnych grupach wiekowych.


Napisz publiczną, statyczną metodę fromCsvLine zwracającą obiekt DeathCauseStatistic na podstawie pojedynczej linii pliku CSV. Uwzględnij, że w pliku po nazwie kodu znajduje się tabulator.


Napisz akcesor do kodu ICD-10 przyczyny zgonu.




public class DeathCauseStatistic {

    private String icd10Code;
    private int[] deathsByAgeGroup;

    // Konstruktor prywatny
    private DeathCauseStatistic(String icd10Code, int[] deathsByAgeGroup) {
        this.icd10Code = icd10Code;
        this.deathsByAgeGroup = deathsByAgeGroup;
    }

    // Statyczna metoda tworząca obiekt z linii CSV
    public static DeathCauseStatistic fromCsvLine(String csvLine) {
        String[] parts = csvLine.split("\t");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Nieprawidłowa linia CSV: " + csvLine);
        }

        String code = parts[0];
        int[] deaths = new int[parts.length - 1];

        for (int i = 1; i < parts.length; i++) {
            deaths[i - 1] = Integer.parseInt(parts[i]);
        }

        return new DeathCauseStatistic(code, deaths);
    }

    // Akcesor (getter) do kodu ICD-10
    public String getIcd10Code() {
        return icd10Code;
    }

    // (Opcjonalnie) Getter do tablicy zgonów
    public int[] getDeathsByAgeGroup() {
        return deathsByAgeGroup;
    }

    // (Opcjonalnie) Metoda toString() do łatwego wypisania
    @Override
    public String toString() {
        return "ICD-10: " + icd10Code + ", zgony: " + java.util.Arrays.toString(deathsByAgeGroup);
    }
}



