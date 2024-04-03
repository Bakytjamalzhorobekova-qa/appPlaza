package peaksoft.appplaza.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LoginResponse {
  private String token;
 private List<String> roleName;
}
