package myApp.core.requests;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;

}