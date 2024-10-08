package ru.malkov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malkov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank
    @Size(max=32)
    private String uid;

    @NotBlank
    @Size(max=32)
    private String operationUid;

    private String systemName;

    @NotBlank
    private String systemTime;

    private String source;

    @Min(1)
    @Max(999)
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

    public void checkUid() throws UnsupportedCodeException {
        if ("123".equals(uid)) {
            throw new UnsupportedCodeException("Unsupported uid: " + uid);
        }
    }

}