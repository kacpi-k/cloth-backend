package dev.kkoncki.cloth.product.color.repository;

import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.forms.CreateColorForm;

public interface ColorRepository {
    Color save(Color color, Product product);
}
