package com.lucas.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucas.data.vo.v1.BookVO;
import com.lucas.exceptions.RequiredObjectIsNullException;
import com.lucas.model.Book;
import com.lucas.repositories.BookRepository;
import com.lucas.services.BookServices;
import com.lucas.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookServices service;

	@Mock
	BookRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {

		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);
		var books = service.findAll();

		assertNotNull(books);
		
		assertEquals(14, books.size());
		
		var bookOne = books.get(1);
		
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne);
		assertTrue(bookOne.toString().contains("links: [</book/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25D, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());
		
		var bookFour = books.get(4);
		
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		assertNotNull(bookFour);
		assertTrue(bookFour.toString().contains("links: [</book/4>;rel=\"self\"]"));
		assertEquals("Some Author4", bookFour.getAuthor());
		assertEquals("Some Title4", bookFour.getTitle());
		assertEquals(25D, bookFour.getPrice());
		assertNotNull(bookFour.getLaunchDate());
		
		var bookSeven = books.get(7);
		
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertNotNull(bookSeven);
		assertTrue(bookSeven.toString().contains("links: [</book/7>;rel=\"self\"]"));
		assertEquals("Some Author7", bookSeven.getAuthor());
		assertEquals("Some Title7", bookSeven.getTitle());
		assertEquals(25D, bookSeven.getPrice());
		assertNotNull(bookSeven.getLaunchDate());
		
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		var bookOne = service.findById(1L);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne);
		assertTrue(bookOne.toString().contains("links: [</book/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25D, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);

		Book persisted = entity;
		entity.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var bookOne = service.create(vo);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne);
		assertTrue(bookOne.toString().contains("links: [</book/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25D, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());
	}
	
	@Test
	void testCreateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	
	@Test
	void testUpdate() {
		
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		Book persisted = entity;
		entity.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var bookOne = service.update(vo);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne);
		assertTrue(bookOne.toString().contains("links: [</book/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25D, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());
	}
	
	@Test
	void testUpdateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
		
	}

}
