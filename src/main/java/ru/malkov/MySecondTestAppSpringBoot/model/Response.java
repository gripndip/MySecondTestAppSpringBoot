package ru.malkov.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", code=" + code +
                ", errorCode=" + errorCode +
                ", errorMessage=" + errorMessage +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String uid;
        private String operationUid;
        private String systemTime;
        private Codes code;
        private ErrorCodes errorCode;
        private ErrorMessages errorMessage;

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder operationUid(String operationUid) {
            this.operationUid = operationUid;
            return this;
        }

        public Builder systemTime(String systemTime) {
            this.systemTime = systemTime;
            return this;
        }

        public Builder code(Codes code) {
            this.code = code;
            return this;
        }

        public Builder errorCode(ErrorCodes errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(ErrorMessages errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Response build() {
            return new Response(uid, operationUid, systemTime, code, errorCode, errorMessage);
        }
    }
}