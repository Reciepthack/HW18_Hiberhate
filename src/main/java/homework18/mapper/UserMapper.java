package homework18.mapper;


import homework18.dto.BookD;
import homework18.dto.UserBaseD;
import homework18.dto.UserD;
import homework18.model.Book;
import homework18.model.User;
import org.mapstruct.*;


@Mapper(uses = BookMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserBaseD toUserBase(User user);

    UserD toUser(User user);

    @AfterMapping
    default void toBookMapping(@MappingTarget User userD) {
        for (BookD book : userD.getBooks()) {
            book.setOwner(userD);
        }
    }

    User fromDToUser(UserD userD, @Context Class clazz);

    @Mapping(target = "books", ignore = true)
    User fromBaseDToUser(UserBaseD userBaseDTO, @Context Class clazz);

    @AfterMapping
    default void toBookEntityMapping(@MappingTarget User user, @Context Class clazz) {
        if (clazz.getCanonicalName().equals(UserD.class.getCanonicalName())) {
            for (Book book : user.getBooks()) {
                book.setOwner(user);
            }
        }
    }
}
