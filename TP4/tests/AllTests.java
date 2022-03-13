import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rpg.*;

import java.util.ArrayList;
import java.util.List;


public class AllTests {
    @Test
    public void testListEnemy() {
        List<Hero> heroesList = new ArrayList<>();
        heroesList.add(new Hunter());
        heroesList.add(new Healer());
        heroesList.add(new Warrior());

        Game game = new Game();

        int enemiesSize = (int) game.probabilite(List.of(heroesList.size() - 1, heroesList.size(), heroesList.size() + 1), List.of(10, 80, 10));
        System.out.println(enemiesSize);

        Assertions.assertTrue(enemiesSize == 2 || enemiesSize == 3 || enemiesSize == 4);
    }

    @Test
    public void testProbabilites () {

        Game game2 = new Game();
        List<Object> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);

        List<Integer> listeProbabilites = new ArrayList<>();
        listeProbabilites.add(33);
        listeProbabilites.add(34);
        listeProbabilites.add(35);

        Assertions.assertTrue((int) game2.probabilite(liste, listeProbabilites) != -1);
    }
}
