package ru.job4j.it;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, el -> el % 2 == 0 && el != 0);
        assertThat(input, is(Arrays.asList(0, 1, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, el -> el % 2 == 0 && el != 0, 5);
        assertThat(input, is(Arrays.asList(0, 1, 5, 3, 5)));
    }

    @Test
    public void whenReplaceAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 2, 3));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(1, 4)));
    }

}