package com.beside.stage.mindserver.generic.domain.number;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Number {
    private static final int MIN = 1;

    private int number = MIN;

    public Number(final int number) {
        if (number < MIN) {
            throw new IllegalArgumentException("Number 값이 최소값 보다 작습니다. number : " + number);
        }
        this.number = number;
    }
}
