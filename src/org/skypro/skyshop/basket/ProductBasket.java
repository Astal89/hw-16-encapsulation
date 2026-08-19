package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    // добавление продукта в корзину
    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new LinkedList<>()).add(product);
    }

    // удаление продукта из корзины
    public List<Product> deleteProductsByName(String name) {
        List<Product> deleted = products.remove(name);
        if(deleted != null) {
            return deleted;
        } else {
            return new LinkedList<>();
        }
    }

    // получение общей стоимости корзины
    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
    }

    // количество специальных продуктов
    public long getSpecialProductsCount() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .filter(Product::isSpecial)
                .count();
    }

    // печать содержимого корзины
    public void printProducts() {

        boolean isEmpty = products.values().stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .findAny()
                .isEmpty();

        if (isEmpty) {
            System.out.println("В корзине пусто");
        } else {
            products.values().stream()
                    .flatMap(List::stream)
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);

            System.out.println("Итого: " + getTotalPrice());
            System.out.println("Специальных товаров: " + getSpecialProductsCount());
        }
    }

    // проверка наличия продукта в корзине
    public boolean hasProduct(String name) {
        return products.containsKey(name);
    }

    // очистка корзины
    public void clear() {
        products.clear();
    }

}
