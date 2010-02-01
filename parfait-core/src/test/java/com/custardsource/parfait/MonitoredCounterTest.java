package com.custardsource.parfait;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class MonitoredCounterTest {
    @Test
    public void monitoredCounterHasCorrectSemantics() {
        assertEquals(ValueSemantics.MONOTONICALLY_INCREASING, newCounter().getSemantics());
    }

    @Test
    public void initialValueIsZero() {
        assertEquals(0L, newCounter().get().longValue());
    }

    @Test
    public void incrementIncreasesValueByOne() {
        MonitoredCounter counter = newCounter();
        counter.inc();
        assertEquals(1L, counter.get().longValue());
    }

    @Test
    public void incrementIncreasesValueByProvidedAmount() {
        MonitoredCounter counter = newCounter();
        counter.inc(77L);
        assertEquals(77L, counter.get().longValue());
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotIncrementWithNegativeValue() {
        newCounter().inc(-1);
    }

    @Test
    public void canIncrementByZero() {
        MonitoredCounter counter = newCounter();
        counter.inc(0L);
        assertEquals(0L, counter.get().longValue());
    }

    private MonitoredCounter newCounter() {
        return new MonitoredCounter("A", "aaa", new MonitorableRegistry());
    }
}