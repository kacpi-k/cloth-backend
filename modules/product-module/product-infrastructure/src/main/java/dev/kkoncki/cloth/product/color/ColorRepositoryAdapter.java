package dev.kkoncki.cloth.product.color;

import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.color.repository.ColorRepository;
import org.springframework.stereotype.Component;

@Component
public class ColorRepositoryAdapter implements ColorRepository {

    private final ColorRepositoryJpa colorRepositoryJpa;

    public ColorRepositoryAdapter(ColorRepositoryJpa colorRepositoryJpa) {
        this.colorRepositoryJpa = colorRepositoryJpa;
    }

    @Override
    public Color save(Color color, Product product) {
        ColorEntity colorEntity = ColorMapper.toColorEntity(color);
        ColorEntity savedColorEntity = colorRepositoryJpa.save(colorEntity);

        return ColorMapper.toColor(savedColorEntity);
    }
}
