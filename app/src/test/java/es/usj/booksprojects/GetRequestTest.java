package es.usj.booksprojects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.List;

import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GetRequestTest {
    @Test
    public void testRetrBooks() {
        GetRequest getRequest = new GetRequest();
        String searchName = "velo";

        // Utiliser un callback personnalisé pour traiter les résultats
        getRequest.retrBooks(searchName, new GetRequest.Callback() {
            @Override
            public void onSuccess(List<Book> books) {
                // Vérifier que la liste de livres n'est pas vide
                assertNotNull(books);
                assertFalse(books.isEmpty());

                // Vérifier que la taille de la liste est égale à 100 (ou ajuster selon vos besoins)
                assertEquals(100, books.size());
            }

            @Override
            public void onFailure(Throwable t) {
                // En cas d'échec, émettre une assertion pour signaler une erreur dans le test
                assertFalse("La requête a échoué: " + t.getMessage(), true);
            }
        });
    }
}
