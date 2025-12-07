package com.erenturhan.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erenturhan.dto.BookRequestDTO;
import com.erenturhan.dto.BookResponseDTO;
import com.erenturhan.exception.BookNotFoundException;
import com.erenturhan.model.Book;
import com.erenturhan.repository.BookRepository;

@Service
public class BookService {
	
	
	@Autowired
	public BookRepository bookRepository;
	
	public BookResponseDTO saveBook(BookRequestDTO requestDTO) {
		
        Book book = new Book();
        book.setTitle(requestDTO.getTitle());
        book.setAuthor(requestDTO.getAuthor());
        book.setPublicationYear(requestDTO.getPublicationYear());

        Book savedBook = bookRepository.save(book);

        BookResponseDTO responseDto = new BookResponseDTO();
        responseDto.setId(savedBook.getId());
        responseDto.setTitle(savedBook.getTitle());
        responseDto.setAuthor(savedBook.getAuthor());

        return responseDto;
	}
	
	public List<BookResponseDTO> getAllBooks(){
		return bookRepository.findAll()
		.stream()
		.map(this::convertToDto)
		.collect(Collectors.toList());
	}
		
		private BookResponseDTO convertToDto(Book book) {
	        BookResponseDTO dto = new BookResponseDTO();
	        dto.setId(book.getId());
	        dto.setTitle(book.getTitle());
	        dto.setAuthor(book.getAuthor());
	        dto.setPublicationYear(book.getPublicationYear());
	        return dto;
	    }
		public BookResponseDTO getBookById(Long id) {
	        Book book = bookRepository.findById(id)
	        .orElseThrow(() -> new BookNotFoundException(id));
	        return convertToDto(book);
		}
		
		public List<BookResponseDTO> getBooksPublishedAfter(String year){
			return bookRepository.findByPublicationYearGreaterThan(year)
					.stream()
					.map(this::convertToDto)
					.collect(Collectors.toList());
		}
		
		public List<BookResponseDTO> getfindByAuthor(String author){
			return bookRepository.findByAuthorIgnoringCase(author)
					.stream()
					.map(this::convertToDto)
					.collect(Collectors.toList());
					
					
		}
		
		public BookResponseDTO dltBookById(Long id){
			Book book = bookRepository.findById(id)
			.orElseThrow(() -> new BookNotFoundException(id));
			bookRepository.deleteById(id);
			return convertToDto(book);
				
		}
			
		}
	

