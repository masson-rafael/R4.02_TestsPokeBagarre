package com.montaury.pokebagarre.metier;

import com.montaury.pokebagarre.erreurs.ErreurMemePokemon;
import com.montaury.pokebagarre.erreurs.ErreurPokemonNonRenseigne;
import com.montaury.pokebagarre.webapi.PokeBuildApi;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
 * - Si le premier pokemon n'a pas de nom, lance erreur "Le premier pokemon n'est pas renseigne",
 * - Si le second pokemon n'a pas de nom, lance erreur "Le second pokemon n'est pas renseigne",
 * - Si les deux pokemons ont le meme nom, lance erreur "Impossible de faire se bagarrer un pokemon avec lui-meme",
 * - Si les deux pokemons sont differents avec des noms incorrects, lance erreur "Impossible de recuperer les details sur ' nom '",
 * - Si les deux pokemons sont differents avec des noms corrects, determine le vainqueur correctement,
 *
 */
class BagarreTest {
    private static PokeBuildApi mockApi;
    private static Bagarre bagarre;


    @BeforeAll
    public static void suiteSetUp() {
        //GIVEN
        mockApi = Mockito.mock(PokeBuildApi.class);
        bagarre = new Bagarre(mockApi);
    }

    @Test
    public void devrait_lancer_erreur_si_premier_pokemon_vide() {
        //GIVEN
        String nomPremierPokemon = "";
        String nomSecondPokemon = "Pikachu";

        //WHEN
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer(nomPremierPokemon, nomSecondPokemon));

        //THEN
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le premier pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_premier_pokemon_null() {
        //GIVEN
        String nomPremierPokemon = null;
        String nomSecondPokemon = "Pikachu";

        //WHEN
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer(nomPremierPokemon, nomSecondPokemon));

        //THEN
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le premier pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_second_pokemon_vide() {
        //GIVEN
        String nomPremierPokemon = "Pikachu";
        String nomSecondPokemon = "";

        //WHEN
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer(nomPremierPokemon, nomSecondPokemon));

        //THEN
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le second pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_second_pokemon_null() {
        //GIVEN
        String nomPremierPokemon = "Pikachu";
        String nomSecondPokemon = null;

        //WHEN
        Throwable thrown = assertThrows(ErreurPokemonNonRenseigne.class, () -> bagarre.demarrer(nomPremierPokemon, nomSecondPokemon));

        //THEN
        assertThat(thrown).isInstanceOf(ErreurPokemonNonRenseigne.class).hasMessage("Le second pokemon n'est pas renseigne");
    }

    @Test
    public void devrait_lancer_erreur_si_les_deux_noms_pokemons_sont_identiques() {
        //GIVEN
        String nomPremierPokemon = "Pikachu";
        String nomSecondPokemon = "Pikachu";

        //WHEN
        Throwable thrown = assertThrows(ErreurMemePokemon.class, () -> bagarre.demarrer(nomPremierPokemon, nomSecondPokemon));

        //THEN
        assertThat(thrown).isInstanceOf(ErreurMemePokemon.class);
    }

    @Test
    public void devrait_determiner_le_vainqueur_correctement() {
        //GIVEN
        Pokemon pikachu = Mockito.mock(Pokemon.class);
        Pokemon bulbizarre = Mockito.mock(Pokemon.class);

        //WHEN
        when(mockApi.recupererParNom("Pikachu")).thenReturn(CompletableFuture.completedFuture(pikachu));
        when(mockApi.recupererParNom("Bulbizarre")).thenReturn(CompletableFuture.completedFuture(bulbizarre));
        when(pikachu.estVainqueurContre(bulbizarre)).thenReturn(true);

        Pokemon vainqueur = bagarre.demarrer("Pikachu", "Bulbizarre").join();

        //THEN
        assertThat(vainqueur).isEqualTo(pikachu);
    }
}