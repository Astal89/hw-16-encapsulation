package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

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
    }
}