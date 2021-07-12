package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Book book1 = new Book(1, "King", 1000, "Author1");
    private Book book2 = new Book(2, "Dawn", 2000, "Author2");
    private Book book3 = new Book(3, "King", 3000, "Author3");
    private Smartphone smartphone1 = new Smartphone(1, "Smart", 5000, "Samsung");
    private Smartphone smartphone2 = new Smartphone(2, "Smart2", 4000, "Nokia");
    private Smartphone smartphone3 = new Smartphone(3, "Smart", 5000, "LG");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test
    public void shouldFindABookByAuthorIfAvailable(){
        String text = "Author1";

        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBookBYNameIfExistTwoProduct(){
        String text = "King";

        Product[] expected = new Product[]{book1, book3};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchForABookById(){
        Book book = new Book();

        book.setId(2);
        assertEquals(2, book.getId());
    }

    @Test
    public void searchFindABookThatDoesNotExist(){
        String text = "нет продукта";

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchShowTwoSmartphoneWithSimilarNames(){
        String text = "Smart";

        Product[] expected = new Product[]{smartphone1, smartphone3};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchFindASmartphoneByManufacturer(){
        String text = "Nokia";

        Product[] expected = new Product[]{smartphone2};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

}