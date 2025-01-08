package dev.kkoncki.cloth.product.color.service;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.forms.CreateColorForm;
import dev.kkoncki.cloth.product.color.repository.ColorRepository;
import dev.kkoncki.cloth.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ColorServiceImpl implements ColorService{

    private final ColorRepository colorRepository;
    private final ProductService productService;

    public ColorServiceImpl(ColorRepository colorRepository, ProductService productService) {
        this.colorRepository = colorRepository;
        this.productService = productService;
    }

    @Override
    public Color save(CreateColorForm form) {
        Optional<Product> productOpt = Optional.ofNullable(productService.getProductById(form.getProductId()));

        if (productOpt.isPresent()) {
            Color color = Color.builder()
                    .id(UUID.randomUUID().toString())
                    .title(form.getTitle())
                    .red(form.getRed())
                    .green(form.getGreen())
                    .blue(form.getBlue())
                    .productId(form.getProductId())
                    .build();

            return colorRepository.save(color, productOpt.get());
        } else {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }
}
