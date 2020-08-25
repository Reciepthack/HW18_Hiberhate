package homework18.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BookD {

    private Long id;

    @NonNull
    private String title;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserD owner;
}
