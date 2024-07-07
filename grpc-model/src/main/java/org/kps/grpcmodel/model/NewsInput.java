import org.kps.grpcmodel.model.Rating;

public record NewsInput(
        String name,
        int pageCount,
        Rating rating,
        String authorId
) {
}
