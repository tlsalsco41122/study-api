package hs.dgsw.study_api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorRes {
    private int status;
    private String message;
}

