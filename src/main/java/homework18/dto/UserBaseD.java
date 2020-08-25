package homework18.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserBaseD {
    private Long id;
    private String firstName;
    private String lastName;
}
