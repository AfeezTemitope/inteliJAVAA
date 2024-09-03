import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HugeIntegerTest {
    private HugeInteger hugeInt;
    @BeforeEach
    void setUp() {
        hugeInt = new HugeInteger("1234512345");
    }

    @Test
    public void testToString() {
        assertEquals("1234512345", hugeInt.toString());
        System.out.println(hugeInt.toString());
    }

    @Test
    public void testToStringWithLeadingZeros() {
        HugeInteger hugeIntWithLeadingZeros = new HugeInteger("000012345678901234567890");
        assertEquals("12345678901234567890", hugeIntWithLeadingZeros.toString());
    }
}