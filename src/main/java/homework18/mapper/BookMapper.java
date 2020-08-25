package homework18.mapper;


import homework18.dto.BookD;
import homework18.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookMapper {

    List<BookD> toBook(List<Book> books);

    @Mapping(target = "owner", ignore = true)
    BookD toBook(Book book);

    List<Book> toBookEntities(List<Book> books);

    @Mapping(target = "owner", ignore = true)
    Book toBookEntity(Book book);
}