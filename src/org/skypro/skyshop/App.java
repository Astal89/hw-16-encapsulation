package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();
        // добавление продуктов в корзину
        basket.addProduct(new SimpleProduct("Батон", 50));
        basket.addProduct(new SimpleProduct("Кофе", 200));
        basket.addProduct(new FixPriceProduct("Сыр"));
        basket.addProduct(new DiscountedProduct("Колбаса", 150, 10));
        basket.addProduct(new SimpleProduct("Масло", 50));
        // добавление продукта в переполненною корзину
        basket.addProduct(new SimpleProduct("Картофель", 50));
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
        SearchEngine engine = new SearchEngine(12);
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
        System.out.println(Arrays.toString(engine.search("кол")));

        System.out.println("SearchEngine поиск по подстроке <а>:");
        System.out.println(Arrays.toString(engine.search("а")));

        System.out.println("SearchEngine поиск по подстроке <фрукты>:");
        System.out.println(Arrays.toString(engine.search("фрукты")));
    }
}