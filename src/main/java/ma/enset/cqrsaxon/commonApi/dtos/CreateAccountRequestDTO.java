package ma.enset.cqrsaxon.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateAccountRequestDTO {
     private String currency;
     private double initialBalance;
}
