package ru.malkov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.malkov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.malkov.MySecondTestAppSpringBoot.model.*;
import ru.malkov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.malkov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.malkov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;


@Slf4j
@RestController

public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private static final Logger log = LoggerFactory.getLogger(MyController.class);


    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new DateTimeUtil()))
//                .systemTime(simpleDateFormat.format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
//            response.setCode("failed");
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
//            response.setErrorCode("ValidationException");
            response.setErrorMessage(ErrorMessages.VALIDATION);
//            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
//            response.setCode("failed");
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
//            response.setErrorCode("UnknownException");
            response.setErrorMessage(ErrorMessages.UNKNOWN);
//            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
