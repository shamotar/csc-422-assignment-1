

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.AgeError;
import exceptions.ExceededMaxDbEntries;
import exceptions.InvalidInputError;

public class AppTest {
    @Test
    public void testExceedsMaxAllowedEntries() throws ExceededMaxDbEntries, AgeError{
        PetDB db = new PetDB(5);

        for (int i = 0; i < 5; i++) {
            Pet pet = new Pet("Pet" + i, 3);
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

    @Test
    public void testAddPetWithBadAge() throws ExceededMaxDbEntries, AgeError {
        PetDB db = new PetDB(5);

        try {
            Pet pet = new Pet("Pet", 0);
            db.addPet(pet, -1);
        } catch (Exception e) {
            assert(e.getClass() == AgeError.class);
        }
    }

    @Test
    public void testInvalidUserInput() throws InvalidInputError {
        try {
            new UserPetInput("Pet");
        } catch (Exception e) {
            assert(e.getClass() == InvalidInputError.class);
        }

        try {
            new UserPetInput("Pet 3 4");
        } catch (Exception e) {
            assert(e.getClass() == InvalidInputError.class);
        }
    }
}
