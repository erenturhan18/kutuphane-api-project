package com.erenturhan.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
	
	@NotEmpty(message = "Kitap başlığı boş bırakılamaz.")
	@Size(min = 2, max = 100, message = "Başlık 2 ile 100 karakter arasında olmalıdır.")
	@Column(name = "title")
	private String title;
	
	@NotEmpty(message = "Yazar adı boş bırakılamaz.")
	@Column(name = "author")
	private String author;
	
	@Min(value = 1400, message = "Yayın yılı 1400'den küçük olamaz.")
	@Column(name = "publicationYear")
	private String publicationYear;
}
