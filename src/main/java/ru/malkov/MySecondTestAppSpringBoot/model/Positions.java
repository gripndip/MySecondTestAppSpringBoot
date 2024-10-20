package ru.malkov.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, true),
    TL(2.6, true),
    DA(2.3, true),
    DE(1.9, false),
    DS(1.8, false);
    private final double positionCoefficient;
    private final boolean isManager;
    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}