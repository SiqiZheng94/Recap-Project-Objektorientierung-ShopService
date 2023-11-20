import java.util.List;

public record Order2(String id,
                     List<Product> products,
                     OrderStatus status) {
}
