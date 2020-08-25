package homework18.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String title;

    @NonNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "owner_id")
    private User owner;
}
