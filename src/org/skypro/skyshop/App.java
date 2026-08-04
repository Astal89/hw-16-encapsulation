package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();
        // добавление продуктов в корзину
        addProductsToBasket(basket);
        // печать содержимого корзины
        basket.printProducts();
        // получение стоимости корзины
        System.out.println("Стоимость корзины: " + basket.getTotalPrice());
        // поиск товара который есть в корзине
        String productName = "Колбаса";
        if (basket.hasProduct(productName)) {
            System.out.println("Продукт " + productName + " присутствует в корзине");
        }
        // поиск товара которого нет в корзине
        productName = "Помидоры";
        if (!basket.hasProduct(productName)) {
            System.out.println("Продукт " + productName + " отсутствует в корзине");
        }
        // очистка корзины
        basket.clear();
        // печать содержимого пустой корзины
        basket.printProducts();
        // получение стоимости пустой корзины
        System.out.println("Стоимость корзины: " + basket.getTotalPrice());
        // поиск товара по имени в пустой корзине
        if (basket.hasProduct("Торт")) {
            System.out.println("Продукт " + productName + " присутствует в корзине");
        }

        System.out.println("SearchEngine заполнение элементами для поиска");
        SearchEngine engine = new SearchEngine(8);
        Searchable[] items = new Searchable[] {
                new FixPriceProduct("Колбаса"),
                new FixPriceProduct("Окорочка"),
                new FixPriceProduct("Зерно"),
                new FixPriceProduct("Сахар"),
                new SimpleProduct("Халва", 200),
                new DiscountedProduct("Батон", 150, 10),
                new Article("Скидка", "В нашем магазине грандиозная скидка на батон."),
                new Article("Новые товары", "С сегодняшнего дня в ассортименте колбаса, зерно и сахар.")
        };
        for(Searchable item: items) {
            engine.add(item);
        }

        System.out.println("SearchEngine поиск по подстроке <кол>:");
        System.out.println(engine.search("кол"));

        System.out.println("SearchEngine поиск по подстроке <а>:");
        System.out.println(engine.search("а"));

        System.out.println("SearchEngine поиск по подстроке <фрукты>:");
        System.out.println(engine.search("фрукты"));

        // Exceptions
        // некорректное указание скидки на продукт
        try {
            Product discounted = new DiscountedProduct("Вода", 100, -1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // некорректное указание цены на продукт
        try {
            Product simple = new SimpleProduct("Масло", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // некорректное указание наименования продукта
        try {
            Product simple = new SimpleProduct(" ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // поиск по лучшему совпадению.
        try {
            Searchable searchable = engine.searchBest("на");
            System.out.println("Результат поиска: " + searchable.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        // ничего не найдено, проверка вывода ошибки
        try {
            Searchable searchable = engine.searchBest("вилка");
            System.out.println("Результат поиска: " + searchable.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        addProductsToBasket(basket);
        basket.printProducts();
        // удаление продуктов из корзины по имени
        for(Product deleted: basket.deleteProductsByName("Сыр")) {
            System.out.println(deleted.getName() + ": продукт удален из корзины.");
        }
        basket.printProducts();
        // удаление несуществующего продукта
        List<Product> deleted = basket.deleteProductsByName("Чай");
        if(deleted.isEmpty()) {
            System.out.println("Список пуст");
        }
        basket.printProducts();
    }

    private static void addProductsToBasket(ProductBasket basket) {
        basket.addProduct(new SimpleProduct("Батон", 50));
        basket.addProduct(new SimpleProduct("Кофе", 200));
        basket.addProduct(new FixPriceProduct("Сыр"));
        basket.addProduct(new DiscountedProduct("Колбаса", 150, 10));
        basket.addProduct(new SimpleProduct("Масло", 50));
        basket.addProduct(new SimpleProduct("Сыр", 200));
    }
}