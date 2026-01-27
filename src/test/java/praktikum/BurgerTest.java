package praktikum;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    @Test
    public void addIngredientShouldAddIngredientToList() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 50);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 50);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientShouldChangeIngredientsOrder() {
        Burger burger = new Burger();
        Ingredient first = new Ingredient(IngredientType.FILLING, "cheese", 50);
        Ingredient second = new Ingredient(IngredientType.FILLING, "salad", 30);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(first, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 50);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptWithoutIngredientsShouldReturnCorrectReceipt() {
        Bun bun = new Bun("black bun", 100);
        Burger burger = new Burger();
        burger.setBuns(bun);

        String expected =
                String.format("(==== %s ====)%n%n(==== %s ====)%n%nPrice: %f%n",
                        bun.getName(), bun.getName(), burger.getPrice());

        assertEquals(expected, burger.getReceipt());
    }

    @RunWith(Parameterized.class)
    public static class SetBunsParameterizedTest {

        private final Bun bun;

        public SetBunsParameterizedTest(Bun bun) {
            this.bun = bun;
        }

        @Parameterized.Parameters
        public static List<Bun> data() {
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
