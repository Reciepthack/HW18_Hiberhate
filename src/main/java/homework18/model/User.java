package homework18.model;

import homework18.dto.BookD;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BookD> books = new ArrayList<>();
}

