package pl.kazet.stockmarket.viewmodel.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCredentialsForm {
    private String email;
    private String password;
}
