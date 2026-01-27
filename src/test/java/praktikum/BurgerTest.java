package praktikum;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    // ---------- addIngredient ----------

    @Test
    public void addIngredientShouldIncreaseIngredientsSize() {
        Burger burger = new Burger();
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 50);

        burger.addIngredient(cheese);

        assertEquals(1, burger.ingredients.size());
    }

    // ---------- removeIngredient ----------

    @Test
    public void removeIngredientByIndexShouldRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 50);
        burger.addIngredient(cheese);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    // ---------- moveIngredient ----------

    @Test
    public void moveIngredientShouldChangeOrder() {
        Burger burger = new Burger();
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 50);
        Ingredient salad = new Ingredient(IngredientType.FILLING, "salad", 30);

        burger.addIngredient(cheese);
        burger.addIngredient(salad);

        burger.moveIngredient(0, 1);

        assertEquals(cheese, burger.ingredients.get(1));
    }

    // ---------- getPrice ----------

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 50);

        burger.setBuns(bun);
        burger.addIngredient(cheese);

        assertEquals(250, burger.getPrice(), 0);
    }

    // ---------- getReceipt ----------

    @Test
    public void getReceiptWithoutIngredientsShouldReturnCorrectReceipt() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);

        String expectedReceipt =
                "(==== black bun ====)\n\n" +
                        "(==== black bun ====)\n\n" +
                        "Price: 200,000000\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    // ---------- setBuns (parameterized) ----------

    @RunWith(Parameterized.class)
    public static class SetBunsParameterizedTest {

        private final Bun bun;

        public SetBunsParameterizedTest(Bun bun) {
            this.bun = bun;
        }

        @Parameterized.Parameters
        public static List<Bun> buns() {
            return List.of(
                    new Bun("black bun", 100),
                    new Bun("white bun", 80)
            );
        }

        @Test
        public void setBunsShouldSetCorrectBun() {
            Burger burger = new Burger();

            burger.setBuns(bun);

            assertEquals(bun, burger.bun);
        }
    }
}
