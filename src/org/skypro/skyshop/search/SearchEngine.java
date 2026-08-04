package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items = new LinkedList<>();
    private final int capacity;

    public SearchEngine(int capacity) {
        this.capacity = capacity;
    }

    public List<Searchable> search(String content) {
        List<Searchable> result = new ArrayList<>();
        for (Searchable item : items) {
            if (item == null) {
                continue;
            }
            if (item.getSearchTerm().toLowerCase().contains(content.toLowerCase())) {
                result.add(item);
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
        if(items.size() < capacity) {
            items.add(item);
        } else {
            System.out.println("Невозможно добавить элемент для поиска. Превышен допустимый лимит.");
        }
    }
}
