package model;

import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseTest
{
    private static Instant startedAt;
    private Case caseUnderTest;

    @BeforeEach
    public void initCase()
    {
        System.out.println("Initilisation de Case");
        caseUnderTest = new Case();
    }

    @AfterEach
    public void undefCase()
    {
        System.out.println("Fin de test");
        caseUnderTest = null;
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
    void caseSetLabelTest()
    {
        String label = "D";

        caseUnderTest.setLabel(label);

        assertEquals("D", caseUnderTest.getLabel());
    }
}
