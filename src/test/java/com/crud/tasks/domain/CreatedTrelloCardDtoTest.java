package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreatedTrelloCardDtoTest {

    @Test
    void testCreatedTrelloCardDto() {
        // Given
        CreatedTrelloCardDto.Trello trello = new CreatedTrelloCardDto.Trello();
        trello.setBoard(3);
        trello.setCard(2);

        CreatedTrelloCardDto.AttachmentsByType attachmentsByType = new CreatedTrelloCardDto.AttachmentsByType();
        attachmentsByType.setTrello(trello);

        CreatedTrelloCardDto.Badges badges = new CreatedTrelloCardDto.Badges();
        badges.setVotes(5);
        badges.setAttachmentsByType(attachmentsByType);

        // When
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setId("1");
        createdTrelloCardDto.setName("Test Card");
        createdTrelloCardDto.setShortUrl("http://test.com");
        createdTrelloCardDto.setBadges(badges);

        // Then
        assertNotNull(createdTrelloCardDto);
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("Test Card", createdTrelloCardDto.getName());
        assertEquals("http://test.com", createdTrelloCardDto.getShortUrl());

        CreatedTrelloCardDto.Badges actualBadges = createdTrelloCardDto.getBadges();
        assertNotNull(actualBadges);
        assertEquals(5, actualBadges.getVotes());

        CreatedTrelloCardDto.AttachmentsByType actualAttachmentsByType = actualBadges.getAttachmentsByType();
        assertNotNull(actualAttachmentsByType);

        CreatedTrelloCardDto.Trello actualTrello = actualAttachmentsByType.getTrello();
        assertNotNull(actualTrello);
        assertEquals(3, actualTrello.getBoard());
        assertEquals(2, actualTrello.getCard());
    }
}

