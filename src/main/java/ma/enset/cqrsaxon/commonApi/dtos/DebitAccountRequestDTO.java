package ma.enset.cqrsaxon.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DebitAccountRequestDTO {
    private String  accountId;
    private String currency;
    private double initialBalance;
}
