package silver.bittiger.cs504.rias.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by vagrant on 4/12/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Embeddable
@RequiredArgsConstructor
public class UserInfo {
    private final String username;

    private String address;

    public UserInfo(String username, String address){
        this.username = username;
        this.address = address;
    }

}
