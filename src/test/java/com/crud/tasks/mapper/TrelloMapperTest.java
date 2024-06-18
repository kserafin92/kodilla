package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToBoards() {
        // given
        List<TrelloListDto> lists = Arrays.asList(
                new TrelloListDto("1", "List 1", false),
                new TrelloListDto("2", "List 2", true)
        );
        List<TrelloBoardDto> boardDtos = Arrays.asList(
                new TrelloBoardDto("1", "Board 1", lists),
                new TrelloBoardDto("2", "Board 2", lists)
        );

        // when
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDtos);

        // then
        assertEquals(2, boards.size());
        assertEquals("1", boards.get(0).getId());
        assertEquals("Board 1", boards.get(0).getName());
        assertEquals(2, boards.get(0).getLists().size());
    }
    @Test
    public void testMapToBoardsDto() {
        // given
        List<TrelloList> lists = Arrays.asList(
                new TrelloList("1", "List 1", false),
                new TrelloList("2", "List 2", true)
        );

        List<TrelloBoard> boards = Arrays.asList(
                new TrelloBoard("1", "Board 1", lists),
                new TrelloBoard("2", "Board 2", lists)
        );

        // when
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(boards);

        // then
        assertEquals(2, boardDtos.size());

        assertEquals("1", boardDtos.get(0).getId());
        assertEquals("Board 1", boardDtos.get(0).getName());
        assertEquals(2, boardDtos.get(0).getLists().size());

        assertEquals("2", boardDtos.get(1).getId());
        assertEquals("Board 2", boardDtos.get(1).getName());
        assertEquals(2, boardDtos.get(1).getLists().size());
    }
    @Test
    public void testMapToList() {
        // given
        List<TrelloListDto> listDtos = Arrays.asList(
                new TrelloListDto("1", "List 1", false),
                new TrelloListDto("2", "List 2", true)
        );

        // when
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);

        // then
        assertEquals(2, lists.size());
        assertEquals("1", lists.get(0).getId());
        assertEquals("List 1", lists.get(0).getName());
        assertEquals(false, lists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // given
        List<TrelloList> lists = Arrays.asList(
                new TrelloList("1", "List 1", false),
                new TrelloList("2", "List 2", true)
        );

        // when
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(lists);

        // then
        assertEquals(2, listDtos.size());
        assertEquals("1", listDtos.get(0).getId());
        assertEquals("List 1", listDtos.get(0).getName());
        assertEquals(false, listDtos.get(0).isClosed());
    }
    @Test
    public void testMapToCardDto() {
        // given
        TrelloCard card = new TrelloCard("Card 1", "Description", "top", "1");

        // when
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        // then
        assertEquals("Card 1", cardDto.getName());
        assertEquals("Description", cardDto.getDescription());
        assertEquals("top", cardDto.getPos());
        assertEquals("1", cardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        // given
        TrelloCardDto cardDto = new TrelloCardDto("Card 1", "Description", "top", "1");

        // when
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        // then
        assertEquals("Card 1", card.getName());
        assertEquals("Description", card.getDescription());
        assertEquals("top", card.getPos());
        assertEquals("1", card.getListId());
    }
}
