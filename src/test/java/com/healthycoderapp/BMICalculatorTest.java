package com.healthycoderapp;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {


    @ParameterizedTest(name= "weight={0}, height={1}")
    @CsvSource(value = {"89.0, 1.72", "95.0, 1.75"})
    public void should_returnTrue_when_dietRecommended(Double coderWeight, Double coderHeight) {
        double weight = coderWeight;
        double height = coderHeight;

        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        assertTrue(recommended);
    }


    @ParameterizedTest(name= "weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
    public void should_returnTrue_when_dietRecommended_usingCsvFile(Double coderWeight, Double coderHeight) {
        double weight = coderWeight;
        double height = coderHeight;

        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        assertTrue(recommended);
    }


    @ParameterizedTest
    @ValueSource(doubles = {40.0, 50.0, 30.6})
    public void should_returnFalse_when_dietNotRecommended(Double coderWeight) {
        double weight = coderWeight;
        double height = 1.72;

        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        assertFalse(recommended);
    }


    @Test
    public void should_throwArithmeticException_when_heightZero() {
        double weight = 50.0;
        double height = 0.0;

        Executable recommended = () -> BMICalculator.isDietRecommended(weight, height);

        assertThrows(ArithmeticException.class, recommended);
    }


    @Test
    public void should_returnCoderWithWorstBMI_when_coderListNotEmpty() {

        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        assertAll(
                () -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
                () -> assertEquals(98.0, coderWithWorstBMI.getWeight()));

    }


    @Test
    public void should_returnNullWithWorstBMICoder_when_coderListEmpty() {

        List<Coder> coders = new ArrayList<>();

        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        assertNull(coderWithWorstBMI);
    }


    @Test
    public void should_returnCorrectBMIScoreArray_when_coderLisNottEmpty() {

        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52,29.59,19.53};

        double[] bmiScore = BMICalculator.getBMIScores(coders);

        assertArrayEquals(expected, bmiScore);

    }





}