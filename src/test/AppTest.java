

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.ExceededMaxDbEntries;

public class AppTest {
    @Test
    public void testExceedsMaxAllowedEntries() throws ExceededMaxDbEntries{
        PetDB db = new PetDB(5);

        for (int i = 0; i < 5; i++) {
            Pet pet = new Pet("Pet" + i, i);
            db.addPet(pet, -1);
        }

        assertEquals(5, db.getPets().size());

        try {
            Pet pet = new Pet("Pet6", 6);
            db.addPet(pet, -1);
        } catch (Exception e) {
            assert(e.getClass() == ExceededMaxDbEntries.class);
        }
    }
}
