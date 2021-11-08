package com.beside.stage.mindserver.q1.domain;

import com.beside.stage.mindserver.generic.domain.number.Number;

import javax.persistence.Embeddable;

@Embeddable
public class QuestionNumber extends Number {
    private static final String INDICATION = "Q";

    public QuestionNumber(final int number) {
        super(number);
    }

    @Override
    public String toString() {
        return INDICATION + super.getNumber();
    }
}
