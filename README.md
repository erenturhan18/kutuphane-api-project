# 📚 Kütüphane Yönetim Sistemi – RESTful API

Bu proje, **Spring Boot 3** kullanılarak geliştirilmiş, **JWT tabanlı kimlik doğrulama ve rol bazlı yetkilendirme** içeren bir **Kütüphane Yönetim RESTful API**’dir.

- Katmanlı mimariyi
- Spring Security + JWT mantığını
- RESTful API tasarımını
- Yetkilendirme (USER / ADMIN) kontrolünü

---

## 🚀 Kullanılan Teknolojiler

- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA & Hibernate**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Bean Validation**
- **PostgreSQL / H2 (Development)**
- **Lombok**
- **Maven**

---

## 🧱 Mimari Yapı

- **Controller** → HTTP isteklerini karşılar
- **Service** → İş kuralları (Business Logic)
- **Repository** → Veritabanı işlemleri
- **DTO (Data Transfer Object)** → Request / Response ayrımı
- **Security Layer** → JWT Filter & Role kontrolü
- **Global Exception Handling** → @ControllerAdvice

---

## 🔐 Kimlik Doğrulama & Yetkilendirme

- JWT tabanlı authentication kullanılmıştır
- Roller:
  - **USER** → Sadece okuma (GET)
  - **ADMIN** → Kayıt silme ve ekleme (POST / DELETE)

### HTTP Status Kodları
- **401 Unauthorized** → Token yok / geçersiz
- **403 Forbidden** → Yetki yetersiz (ADMIN gerekli)

---

## 📍 API Uç Noktaları (Endpoints)

### 🔑 Authentication

| Metod | Endpoint | Açıklama |
|-----|---------|---------|
| POST | `/api/auth/register` | Yeni kullanıcı kaydı |
| POST | `/api/auth/login` | Giriş yap ve JWT al |

---

### 📚 Kitap İşlemleri

| Metod | Endpoint | Yetki | Açıklama |
|-----|---------|------|---------|
| POST | `/api/books/save` | ADMIN | Yeni kitap ekler |
| GET | `/api/books/list` | USER / ADMIN | Tüm kitapları listeler |
| GET | `/api/books/{id}` | USER / ADMIN | ID’ye göre kitap getirir |
| GET | `/api/books/findauthor?author=X` | USER / ADMIN | Yazara göre arama |
| GET | `/api/books/published-after?year=X` | USER / ADMIN | Belirtilen yıldan sonra çıkanlar |
| DELETE | `/api/books/delete/{id}` | ADMIN | Kitap siler |

---

## ❗ Hata Yönetimi

- Global Exception Handling uygulanmıştır
- Özel hata sınıfları kullanılmıştır
- Anlamlı ve doğru HTTP cevapları döndürülür

Örnek:
- Kitap bulunamazsa → `404 NOT FOUND`
- Yetkisiz işlem → `403 FORBIDDEN`
- Geçersiz token → `401 UNAUTHORIZED`

---

## 🧪 Test & Kullanım

- API testleri **Postman** ile yapılmıştır
- JWT token `Authorization` header üzerinden gönderilir

