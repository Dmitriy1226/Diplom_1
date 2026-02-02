package praktikum;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    // ---------- addIngredient ----------

    @Test
    public void addIngredientShouldIncreaseIngredientsSize() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    // ---------- removeIngredient ----------

    @Test
    public void removeIngredientByIndexShouldRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    // ---------- moveIngredient ----------

    @Test
    public void moveIngredientShouldMoveFirstToNewIndex() {
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(first, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldMoveSecondToOldIndex() {
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(second, burger.ingredients.get(0));
    }


    // ---------- getPrice ----------

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250f, burger.getPrice(), 0.0001f);
    }

    // ---------- getReceipt ----------

    @Test
    public void getReceiptWithoutIngredientsShouldReturnCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        // Собираем expected тем же форматом, что и в Burger,
        // чтобы не зависеть от запятой/точки в float на разных локалях.
        String expectedReceipt =
                String.format("(==== %s ====)%n", "black bun") +
                        String.format("(==== %s ====)%n", "black bun") +
                        String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    // ---------- setBuns (parameterized) ----------

    @RunWith(Parameterized.class)
    public static class SetBunsParameterizedTest {

        private final String bunName;
        private final float bunPrice;

        public SetBunsParameterizedTest(String bunName, float bunPrice) {
            this.bunName = bunName;
            this.bunPrice = bunPrice;
        }

        @Parameterized.Parameters(name = "Тестовые данные: bunName={0}, bunPrice={1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"black bun", 100f},
                    {"white bun", 80f}
            });
        }

        @Test
        public void setBunsShouldSetCorrectBun() {
            Burger burger = new Burger();

            Bun bun = mock(Bun.class);
            when(bun.getName()).thenReturn(bunName);
            when(bun.getPrice()).thenReturn(bunPrice);

            burger.setBuns(bun);

            assertEquals(bun, burger.bun);
        }
    }
}
