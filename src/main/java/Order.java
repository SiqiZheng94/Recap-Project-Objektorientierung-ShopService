import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.time.Instant;
import java.util.List;


@With
@Builder

public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        Instant orderTimeStamp


) {
}
