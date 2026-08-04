package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final List<Product> products = new LinkedList<>();

    // добавление продукта в корзину
    public void addProduct(Product product) {
        products.add(product);
    }

    // удаление продукта из корзины
    public List<Product> deleteProductsByName(String name) {
        List<Product> deleted = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (Objects.equals(p.getName(), name)) {
                deleted.add(p);
                iterator.remove();
            }
        }
        return deleted;
    }

    // получение общей стоимости корзины
    public int getTotalPrice() {
        int totalPrice = 0;
        for (var product : products) {
            if (product == null) {
                continue;
            }
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    // количество специальных продуктов
    public int getSpecialProductsCount() {
        int specialProductsCount = 0;
        for (var product : products) {
            if (product != null && product.isSpecial()) {
                specialProductsCount++;
            }
        }
        return specialProductsCount;
    }

    // печать содержимого корзины
    public void printProducts() {
        boolean isEmpty = true;
        for (var product : products) {
            if (product != null) {
                System.out.println(product);
                isEmpty = false;
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
        for (var product : products) {
            if (product == null) {
                continue;
            }
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // очистка корзины
    public void clear() {
        products.clear();
    }

}
