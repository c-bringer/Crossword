package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest
{
    private static Instant startedAt;
    private Grid gridUnderTest;

    @BeforeEach
    public void initGrid()
    {
        System.out.println("Initilisation de Grid");
        gridUnderTest = new Grid();
    }

    @AfterEach
    public void undefGrid()
    {
        System.out.println("Fin de test");
        gridUnderTest = null;
    }

    @BeforeAll
    static public void initStartingTime()
    {
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration()
    {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @ParameterizedTest(name = "{0} doit être entre 0 et 10")
    @CsvSource({"-1", "4", "10", "13"})
    public void gridSetHeightTest(int arg)
    {
        gridUnderTest.setHeight(arg);

        assertTrue(arg > 0 && arg <= 10);
    }

    @ParameterizedTest(name = "{0} doit être entre 0 et 10")
    @CsvSource({"-1", "4", "10", "13"})
    public void gridSetWidthTest(int arg)
    {
        gridUnderTest.setWidth(arg);

        assertTrue(arg > 0 && arg <= 10);
    }

    @ParameterizedTest(name = "Grille en {0}x{1}")
    @CsvSource({"6, 6", "0, 0", "-6, -6", "11, 11"})
    public void gridSizeTest(int arg1, int arg2)
    {
        gridUnderTest.setWidth(arg1);
        gridUnderTest.setHeight(arg2);

        assertTrue((gridUnderTest.getHeight() > 0 && gridUnderTest.getHeight() <= 10) &&
                (gridUnderTest.getWidth() > 0 && gridUnderTest.getWidth() <= 10));
    }
}
