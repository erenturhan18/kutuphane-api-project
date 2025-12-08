package com.erenturhan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erenturhan.dto.BookRequestDTO;
import com.erenturhan.dto.BookResponseDTO;
import com.erenturhan.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
		
		@Autowired
		public BookService bookService;
		
		@PostMapping(path = "/save")
		public BookResponseDTO creatBook(@Valid @RequestBody BookRequestDTO requestDTO) {
			return bookService.saveBook(requestDTO);
		}
		
		@GetMapping(path = "/list")
		public List<BookResponseDTO> getAllBooks(){
			return bookService.getAllBooks();
		}
		
		@GetMapping(path = "/{id}")
		public BookResponseDTO getBookById(@PathVariable(name = "id")Long id){
			return bookService.getBookById(id);
			
		}
		
		@GetMapping(path = "/published-after")
		public List<BookResponseDTO> getBooksAfterYear(@RequestParam String year){
			return bookService.getBooksPublishedAfter(year);
		}
		
		@GetMapping(path = "/findauthor")
		public List<BookResponseDTO> getfindByAuthor(@RequestParam String author){
			return bookService.getfindByAuthor(author);
		}
		
		@DeleteMapping(path = "/delete/{id}")
		public BookResponseDTO dltBookById(@PathVariable(name = "id")Long id) {
			return bookService.dltBookById(id);
		}
	
		
}
