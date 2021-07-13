package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zmath.back.model.Review;
import ru.zmath.back.service.dto.ReviewDTO;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class,
    SubjectMapper.class,
    StatusMapper.class,
    MethodMapper.class,
    AttachedFileMapper.class
})
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ReviewDTO toDto(Review review);

    @Mapping(source = "userId", target = "user")
    Review toEntity(ReviewDTO reviewDTO);

    default Review fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Review review = new Review();
        review.setId(id);
        return review;
    }
}
