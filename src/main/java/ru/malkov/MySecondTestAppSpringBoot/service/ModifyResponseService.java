package ru.malkov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.malkov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
