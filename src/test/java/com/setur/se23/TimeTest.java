package com.setur.se23;

import com.setur.se23.engine.game.timing.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    @Test
    void getInstance_NotInstantiated_ReturnsNull() {
        // Arrange
        // Act
        var instance = Time.getInstance();

        // Assert
        assertNull(instance);
    }

    @Test
    void instantiate_NotInstantiated_InstantiatesRenderer() {
        // Arrange
        // Act
        Time.instantiate();

        // Assert
        assertNotNull(Time.getInstance());
    }

    @Test
    void deltaTime_WhenNeverUpdated_IsZero() {
        // Arrange
        Time.instantiate();

        // Act
        var deltaTime = Time.deltaTime();

        // Assert
        assertEquals(0.0f, deltaTime);
    }

    @Test
    void deltaTime_WhenUpdated_ReturnsDeltaValueOfTwoTimes() {
        // Arrange
        Time.instantiate();
        var expected = 2.0f - 1.0f;

        // Act
        Time.getInstance().updateTime(1.0f);
        Time.getInstance().updateTime(2.0f);

        // Assert
        assertEquals(expected, Time.deltaTime(), 1e+5);
    }

    @Test
    void setTimeScale_WhenReducingTimeScaleByHalf_ReturnsHalfTheDeltaValueOfTheTwoTimes() {
        // Arrange
        Time.instantiate();
        var expected = (2.0f - 1.0f) / 2.0f;

        // Act
        Time.getInstance().setTimeScale(0.5f);

        Time.getInstance().updateTime(1.0f);
        Time.getInstance().updateTime(2.0f);

        // Assert
        assertEquals(expected, Time.deltaTime(), 1e+5);
    }

    @Test
    void setTimeScale_WhenSettingTimeScaleToZero_ReturnsDeltaTimeAsZero() {
        // Arrange
        Time.instantiate();

        // Act
        Time.getInstance().setTimeScale(0.0f);

        Time.getInstance().updateTime(1.0f);
        Time.getInstance().updateTime(2.0f);

        // Assert
        assertEquals(0.0f, Time.deltaTime());
    }

    @AfterEach
    void tearDown() {
        Time.Destroy();
    }
}
