package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefinitionTest
{
    private static Instant startedAt;
    private Definition definitionUnderTest;

    @BeforeEach
    public void initDefinition()
    {
        System.out.println("Initilisation de Definition");
        definitionUnderTest = new Definition(1, 1, 1);
    }

    @AfterEach
    public void undefGrid()
    {
        System.out.println("Fin de test");
        definitionUnderTest = null;
    }

    @BeforeAll
    static public void initStartingTime() {
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration() {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @Test
    public void defSetX()
    {
        int x = 4;

        definitionUnderTest.setX(x);

        assertEquals(4, definitionUnderTest.getX());
    }

    @Test
    public void defSetY()
    {
        int y = 6;

        definitionUnderTest.setY(y);

        assertEquals(6, definitionUnderTest.getY());
    }

    @ParameterizedTest(name = "Test de la definition {0}")
    @CsvSource({"-1", "1", "2", "3", "4", "5"})
    public void defSetDefinition(int arg)
    {
        definitionUnderTest.setDirection(arg);

        assertTrue(definitionUnderTest.getDirection() >= 0 && definitionUnderTest.getDirection() <= 3);
    }
}
