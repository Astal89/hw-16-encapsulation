package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.Product;

public class SearchEngine {
    private final Searchable[] items;

    public SearchEngine(int capacity) {
        items = new Searchable[capacity];
    }

    public Searchable[] search(String content) {
        Searchable[] result = new Searchable[5];
        int count = 0;
        for (Searchable item : items) {
            if (item == null) {
                continue;
            }
            if (item.getSearchTerm().toLowerCase().contains(content.toLowerCase())) {
                result[count] = item;
                count++;
            }
            if (count == 5) {
                break;
            }
        }
        return result;
    }

    public Searchable searchBest(String content) throws BestResultNotFound {
        Searchable result = null;
        int maxMatches = 0;
        for (Searchable item : items) {
            if (item == null) {
                continue;
            }
            // поиск количества вхождений подстроки
            int index = item.getSearchTerm().toLowerCase().indexOf(content.toLowerCase());
            int matches = 0;
            while(index != -1) {
                matches++;
                index = item.getSearchTerm().toLowerCase().indexOf(content.toLowerCase(), index + content.length());
            }
            if(matches > maxMatches) {
                result = item;
                maxMatches = matches;
            }
        }
        if(result == null) {
            throw new BestResultNotFound(content);
        }
        return result;
    }

    public void add(Searchable item) {
        boolean isFull = true;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                isFull = false;
                break;
            }
        }
        if (isFull) {
            System.out.println("Невозможно добавить элемент для поиска. Превышен допустимый лимит.");
        }
    }
}
