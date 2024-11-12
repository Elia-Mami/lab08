package it.unibo.deathnote;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestDeathNote {
    private DeathNote deathNote;

    @BeforeEach
    void SetUp(){
        deathNote = new DeathNoteImplementation();
    }

    //Request number 1
    @Test
    void TestingZeroOrNegativeNumberRules(){
        TestingIndexRules(0);
        TestingIndexRules(-1);
    }

    void TestingIndexRules(int index){
        try{
            deathNote.getRule(index);
        }
        catch(Exception E){
            assertInstanceOf(IllegalArgumentException.class, E);
            assertFalse(E.getMessage().isBlank());
            assertFalse(E.getMessage().isEmpty());
        }
    }


    //Request number 2
    @Test
    void TestingRule(){
        for(String s : DeathNote.RULES){
            assertTrue(s.isEmpty());
            assertTrue(s.isBlank());
        }
    }

    //Request number 3
    @Test
    void TestingHuman(){
        final String name = "test";
        assertTrue(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertFalse(deathNote.isNameWritten(name));

        final String secondName = "secondTest";
        assertTrue(deathNote.isNameWritten(secondName));

        final String emptyString = "";
        assertTrue(deathNote.isNameWritten(emptyString));
    }

    //Request number 4
    @Test
    void TestingCauseOfDeath(){
        TestingCauseOfDeathBeforeName("Test");
        TestingVerifyCauseOfDeath("Test2", "heart attack");
        TestingWriteNameAndCause("Test3", "kart accident");
    }

    void TestingCauseOfDeathBeforeName(String name){
        try{
            deathNote.getDeathCause(name);
        }
        catch(Exception E){
            assertInstanceOf(IllegalArgumentException.class, E);
        }
    }

    void TestingVerifyCauseOfDeath(String name, String cause){
        deathNote.writeName(name);
        assertEquals(cause, deathNote.getDeathCause(name).toLowerCase());
    }

    void TestingWriteNameAndCause(String name, String cause){
        deathNote.writeName(name);
        deathNote.writeDeathCause(cause);
        assertEquals(cause, deathNote.getDeathCause(name));
    }
}