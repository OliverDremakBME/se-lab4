package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_Rate100() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedos_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeTorpedos_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_MoreTorpedosThanAvailable_ThrowsException() {
        TorpedoStore store = new TorpedoStore(3);

        assertThrows(IllegalArgumentException.class, () -> store.fire(4));
    }

    @Test
    void isEmpty_InitialEmpty_ReturnsTrue() {
        TorpedoStore store = new TorpedoStore(0);

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void isEmpty_NonEmpty_ReturnsFalse() {
        TorpedoStore store = new TorpedoStore(3);

        assertFalse(store.isEmpty());
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void isEmpty_AfterFiringAll_ReturnsTrue() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void constructor_SingleArgument_SetsCount() {
        TorpedoStore store = new TorpedoStore(10);

        assertEquals(10, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }
}


