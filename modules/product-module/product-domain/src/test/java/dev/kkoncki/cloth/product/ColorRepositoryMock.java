package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.repository.ColorRepository;

import java.util.HashMap;
import java.util.Map;

public class ColorRepositoryMock implements ColorRepository {

    Map<String, Color> mockDB = new HashMap<>();

    @Override
    public Color save(Color color, Product product) {
        mockDB.put(color.getId(), color);
        return color;
    }
}
