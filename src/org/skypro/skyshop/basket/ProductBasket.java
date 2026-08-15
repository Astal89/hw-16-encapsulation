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
        int totalPrice = 0;
        for (Map.Entry<String, List<Product>> entry : products.entrySet()) {
            List<Product> productList = entry.getValue();
            for (var product : productList) {
                if (product == null) {
                    continue;
                }
                totalPrice += product.getPrice();
            }
        }
        return totalPrice;
    }

    // количество специальных продуктов
    public int getSpecialProductsCount() {
        int specialProductsCount = 0;
        for (Map.Entry<String, List<Product>> entry : products.entrySet()) {
            List<Product> productList = entry.getValue();
            for (var product : productList) {
                if (product != null && product.isSpecial()) {
                    specialProductsCount++;
                }
            }
        }
        return specialProductsCount;
    }

    // печать содержимого корзины
    public void printProducts() {
        boolean isEmpty = true;
        for (Map.Entry<String, List<Product>> entry : products.entrySet()) {
            List<Product> productList = entry.getValue();
            for (var product : productList) {
                if (product != null) {
                    System.out.println(product);
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) {
            System.out.println("В корзине пусто");
        } else {
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
