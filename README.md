# 📚 Kütüphane Yönetim RESTful API

Bu proje, Spring Boot 3 ile geliştirilmiş, kütüphane kaynaklarını (Kitapları) yönetmek için tasarlanmış bir RESTful API'dir.

Bu proje, bir Backend geliştirici olarak temel mimari prensiplere ve Spring Boot'un çekirdek özelliklerine hakimiyetimi göstermektedir.


## 🛠️ Kullanılan Teknolojiler ve Özellikler

- **Çatı (Framework):** Spring Boot 3.x
- **Veri Erişimi:** Spring Data JPA & Hibernate
- **Veritabanı:** H2 (Geliştirme için InMemory) / PostgreSQL (Önerilen)
- **Mimari:**
    - **Katmanlı Yapı:** Controller, Service, Repository ayrımı.
    - **Veri Taşıma:** Request ve Response için DTO (Data Transfer Object) kullanımı.
    - **Hata Yönetimi:** `@ControllerAdvice` ve özel istisnalar (`BookNotFoundException`) ile global hata yakalama ve 404/400 gibi doğru HTTP durum kodları döndürme.
    - **Veri Doğrulama:** Bean Validation (`@Valid`, `@NotEmpty`, `@Size`) ile gelen veriyi kontrol etme.
    - **Özel Sorgular:** Yazar adına veya yayınlanma yılına göre arama gibi özel sorgu metodları.


## 📍 API Uç Noktaları (Endpoints)

| HTTP Metodu | Yol (Path) | Açıklama |
| :--- | :--- | :--- |
| **POST** | `/api/books/save` | Yeni bir kitap kaydeder (Request DTO gereklidir). |
| **GET** | `/api/books/list` | Tüm kitapları listeler. |
| **GET** | `/api/books/{id}` | Belirli bir ID'ye sahip kitabı getirir (Bulunamazsa 404 döner). |
| **GET** | `/api/books/findauthor?author=X` | Yazar adına göre harf duyarsız arama yapar. |
| **GET** | `/api/books/published-after?year=X` | Girdiğiniz yıldan sonra yayınlanan kitapları listeler. |
| **DELETE** | `/api/books/delete/{id}` | Belirli bir ID'ye sahip kitabı siler. |
