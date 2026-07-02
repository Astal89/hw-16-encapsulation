package org.skypro.skyshop.basket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] products = new Product[5];
    // добавление продукта в корзину
    public void addProduct(Product product) {
        boolean basketIsFull = true;
        for (int i = 0; i < products.length; i++) {
            if(products[i] == null) {
                products[i] = product;
                basketIsFull = false;
                break;
            }
        }
        if (basketIsFull) {
            System.out.println("Невозможно добавить продукт");
        }
    }

    // получение общей стоимости корзины
    public int getTotalPrice() {
        int totalPrice = 0;
        for (var product : products) {
            if(product == null) {
                continue;
            }
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    // печать содержимого корзины
    public void printProducts() {
        boolean isEmpty = true;
        int specialProductsCount = 0;
        for (var product : products) {
            if(product != null) {
                System.out.println(product);
                isEmpty = false;
                if(product.isSpecial()) {
                    specialProductsCount++;
                }
            }
        }
        if (isEmpty) {
            System.out.println("В корзине пусто");
        } else {
            System.out.println("Итого: " + getTotalPrice());
            System.out.println("Специальных товаров: " + specialProductsCount);
        }
    }

    // проверка наличия продукта в корзине
    public boolean hasProduct(String name) {
        for (var product : products) {
            if(product == null) {
                continue;
            }
            if(product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // очистка корзины
    public void clear() {
        products = new Product[5];
    }

}
