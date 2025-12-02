# **Intelligent Library Management System (ILMS)**

## **Layihə Məqsədi**

“Intelligent Library Management System” (ILMS) kitabxana idarəçiliyi üçün hazırlanmış **realist, admin-driven və data-driven bir platformadır**. Sistem oxuculara kitabları daha effektiv təqdim etməyi, borrow/return əməliyyatlarını izləməyi və kitabların performansını analiz etməyi təmin edir.

Bu layihənin əsas ideyası: **admin bütün əməliyyatları idarə edir**, oxucular isə yalnız kitabları götürür və geri qaytararkən reytinq verirlər.

---

## **Əsas Funksionallıqlar**

### **1. Admin-Only Workflow**

* **Hesab yaratmaq:** Oxucu gəlir, admin onun adına `User` hesabı açır (ad, soyad, yaş və unikal id).
* **Borrow əməliyyatı:** İstifadəçi kitab götürür → admin mövcud `BookCopy` seçir və **start date** qeyd edir.
* **Return əməliyyatı:** İstifadəçi kitabı qaytarır → admin `returnDate` qeyd edir və oxucudan **rating** soruşur.
* **Rating:** Oxucu verdiyi ulduz sayı `BorrowRecord`-a əlavə olunur → kitabın ümumi reytinqi yenilənir.

---

### **2. Kitab İdarəsi**

* **CRUD əməliyyatları (Admin):** Kitab əlavə etmək, silmək, və.s
* **Nüsxə idarəsi:** Hər kitabdan bir neçə nüsxə ola bilər → hər nüsxə ayrıca `Stock` kimi izlənir.
* **Availability:** Mövcud nüsxələr borrow üçün əlçatan, digər nüsxələr isə artıq borrow edilmiş kimi göstərilir.

---

### **3. Smart Search & Filter**

* İstifadəçilər və admin aşağıdakı kriteriyalara görə kitabları axtara və filtrləyə bilər:

    * Name and Author (`title`)
    * İd (`author`)
    * Mövzu / Janr (`genre`)
    * Nəşr ili (`year`)
    * Populyarlıq / Average Rating (`rating`)
    * Mövcud nüsxə sayı (`availableCopies`)

---

### **4. Borrow Tracking & Oxuma Analizi**

* Hər borrow əməliyyatı `BorrowRecord` ilə qeydə alınır:

  ```java
  class BorrowRecord {
      BookCopy bookCopy;
      User user;
      LocalDate borrowDate;
      LocalDate returnDate;
      int rating;
  }
  ```
* **Oxuma müddəti:** `borrowDate → returnDate` fərqi hesablanır.
* **Ortalama oxuma müddəti:** Kitab üzrə bütün borrow-lar əsasında hesablama aparılır.

---

### **5. Rating & Recommendation System**

* Hər oxucu oxuduğu kitaba **rating** verir → kitabın reytinqi yenilənir.
* Ortalama reytinq və borrow sayına görə **populyar kitablar** sıralanır.
* Recommendation engine: Admin və oxucu üçün oxşar kitablar təklif edə bilər (historical borrow patterns + genre similarity).

---

### **6. Data Persistence**

* **Database:** MySQL / H2 (in-memory test üçün)
* **Saxlanılan məlumatlar:** Users, Books, BookCopies, BorrowRecords, Ratings
* **Alternativ:** JSON / File I/O test və backup üçün

---

## **Business Dəyəri**

* **İstifadəçi təcrübəsi:** Oxucu kitabı tez götürür, admin prosesləri izləyir.
* **Admin effektivliyi:** Borrow/return, overdue, rating və populyarlıq idarəsi bir mərkəzdən aparılır.
* **Data-driven decisions:** Populyar kitablar, nüsxə sayı, oxuma müddəti, reytinqlər əsasında statistik analiz mümkündür.
* **Reallığa uyğun:** Hər nüsxə ayrıca izlənir, borrow/return əməliyyatları manual qeyd olunur, reytinq sistemi realistdir.

---

## **Texnologiyalar**

* **Backend:** Java / Spring Boot
* **Database:** MySQL 
* **OOP & Collections:** `Book`, `BookCopy`, `User`, `BorrowRecord`
* **Service Layer:** `LibraryService`, `OverdueService`, `RecommendationService`
* **REST API Endpoints:**

    * `/admin/books` → add, delete, update, filter, search
    * `/admin/users` → add, delete,update, filter, search
    * `/admin/loan` → borrow/return operations, filter, delete, search

[//]: # (    * `/admin/ratings` → add rating)
    * `/books/search` → filter books by criteria
* **Exception Handling & Validation:** Input validation, unavailable books, missing user/book errors

---

## **Workflow (CEO & Developer View)**

1. Admin istifadəçi hesabı yaradır.
2. Oxucu kitab istəyir → admin mövcud `BookCopy` verir, borrow date və due date qeyd olunur.
3. Kitab qaytarılır → admin return date qeyd edir və oxucudan rating soruşur.
4. Sistem:

    * Kitabın `averageRating` və `averageReadingDays` yenilənir
    * Mövcud nüsxələr və reytinqlər search/filter nəticələrində göstərilir

---