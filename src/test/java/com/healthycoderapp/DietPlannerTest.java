package com.healthycoderapp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DietPlannerTest {

    private DietPlanner dietPlanner;


    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20, 30 ,50);
    }


    @RepeatedTest(value=10)
    void should_returnCorrectDietPlan_when_correctCoder(){

        Coder coder = new Coder(1.82,75.0,26,Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110,73,275);

        DietPlan actual = dietPlanner.calculateDiet(coder);

        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );

    }


}