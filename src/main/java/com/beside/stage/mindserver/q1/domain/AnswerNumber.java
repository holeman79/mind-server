package com.beside.stage.mindserver.q1.domain;

import com.beside.stage.mindserver.generic.domain.number.Number;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerNumber extends Number {
    private static final String INDICATION = "A";

    public AnswerNumber(final int number) {
        super(number);
    }

    @Override
    public String toString() {
        return INDICATION + super.getNumber();
    }
}
