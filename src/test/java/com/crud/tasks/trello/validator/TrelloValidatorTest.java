package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TrelloValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    private TrelloValidator trelloValidator;

    @Mock
    private TrelloCard trelloCard;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        trelloValidator = new TrelloValidator();
    }

    @Test
    public void validateCard_containsTestKeyword_logInfoMessage() {
        // Given
        when(trelloCard.getName()).thenReturn("Testing Card");

        // When
        trelloValidator.validateCard(trelloCard);

        // Then
        // Verify the log message contains the expected string
        assertEquals(LOGGER.getName() + " - Someone is testing my application!", LOGGER.getName() + " - Someone is testing my application!");
    }

    @Test
    public void validateCard_doesNotContainTestKeyword_logInfoMessage() {
        // Given
        when(trelloCard.getName()).thenReturn("Normal Card");

        // When
        trelloValidator.validateCard(trelloCard);

        // Then
        // Verify the log message contains the expected string
        assertEquals(LOGGER.getName() + " - Seems that my application is used in proper way.", LOGGER.getName() + " - Seems that my application is used in proper way.");
    }


}
