package com.montaury.pokebagarre.metier;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * - Si le premier Pokémon a une meilleure attaque que le deuxieme, le premier remporte la bagarre,
 * - Si le premier Pokémon a une moins bonne attaque que le deuxieme, le deuxieme remporte la bagarre,
 * - Si les 2 Pokémon ont la même attaque et si le premier a une meilleure défense que le deuxieme,
 * alors le premier remporte la bagarre,
 * - Si les 2 Pokémon ont la même attaque et si le premier a une moins bonne défense que le deuxieme,
 * alors le deuxieme remporte la bagarre,
 * - Si les 2 Pokémon ont la même attaque et la même défense, le premier renseigné par l’utilisateur a la
priorité et remporte la bagarre
 */
class PokemonTests {

    @Test
    void premier_meilleure_attaque_vainqueur_premier () {
        // GIVEN
        Pokemon pokemon1 = new Pokemon("Libegon","",new Stats(200, 100));
        Pokemon pokemon2 = new Pokemon("Voltali","",new Stats(100, 100));

        // WHEN
        boolean res = pokemon1.estVainqueurContre(pokemon2);

        // THEN
        assertThat(res).isTrue();
    }

    @Test
    void deuxieme_meilleure_attaque_vainqueur_deuxieme () {
        // GIVEN
        Pokemon pokemon1 = new Pokemon("Libegon","",new Stats(100, 100));
        Pokemon pokemon2 = new Pokemon("Voltali","",new Stats(200, 100));

        // WHEN
        boolean res = pokemon1.estVainqueurContre(pokemon2);

        // THEN
        assertThat(res).isFalse();
    }

    @Test
    void attaques_identiques_premier_meilleure_defense_vainqueur_premier () {
        // GIVEN
        Pokemon pokemon1 = new Pokemon("Libegon","",new Stats(100, 200));
        Pokemon pokemon2 = new Pokemon("Voltali","",new Stats(100, 100));

        // WHEN
        boolean res = pokemon1.estVainqueurContre(pokemon2);

        // THEN
        assertThat(res).isTrue();
    }

    @Test
    void attaques_identiques_deuxieme_meilleure_defense_vainqueur_deuxieme () {
        // GIVEN
        Pokemon pokemon1 = new Pokemon("Libegon","",new Stats(100, 100));
        Pokemon pokemon2 = new Pokemon("Voltali","",new Stats(100, 200));

        // WHEN
        boolean res = pokemon1.estVainqueurContre(pokemon2);

        // THEN
        assertThat(res).isFalse();
    }

    @Test
    void attaques_et_defences_identiques_vainqueur_premier () {
        // GIVEN
        Pokemon pokemon1 = new Pokemon("Libegon","",new Stats(100, 100));
        Pokemon pokemon2 = new Pokemon("Voltali","",new Stats(100, 100));

        // WHEN
        boolean res = pokemon1.estVainqueurContre(pokemon2);

        // THEN
        assertThat(res).isTrue();
    }


    @BeforeAll
    static void suiteSetUp() {
    }

    @AfterAll
    static void suiteTearDown() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}