package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();
    private final int capacity;

    public SearchEngine(int capacity) {
        this.capacity = capacity;
    }

    public Set<Searchable> search(String content) {
        return items.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getSearchTerm().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>((a1, a2) -> {
                    int lengthComparison = Integer.compare(
                            a2.getName().length(),
                            a1.getName().length()
                    );
                    if (lengthComparison == 0) {
                        return a1.getName().compareTo(a2.getName());
                    }
                    return lengthComparison;
                })));
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
