package ma.enset.cqrsaxon.commonApi.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetAccountByIdQuery {
    private String id;
}
