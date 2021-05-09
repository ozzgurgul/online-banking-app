# Online Bank



- **Müşteri yaratma** :  API'lar aracılığıyla müşteri yaratma, güncelleme ve silme işlemleri gerçekleştirilir. Hesabında parası olan veya kredi kartı borcu olan müşteriler için hesap silme işlemi gerçekleştirilemez.
- **Hesap yönetimi** : Hesap yaratma(Mevduat hesabı ve birikim hesabı), hesap para transfer kısıtlamaları.
- **Kart yönetimi**: Banka kartı ve kredi kartı yaratılması, bu kartların müşteri ve hesaplarla ilişkilendirilmesi, hesaptan borç ödeme, bankamatikten borç ödeme ve ekstre görüntüleme işlemleri.
- **Transfer yönetimi** : Hesaplar arası iban ile para transferi.



### Kullanılan Teknolojiler

- Java8
- Spring Boot
- Spring Web
- Spring DataJPA
- Maven
- Swagger
- MySql



### Entityler

#### Customer

Uygulamada merkez konumda bulunan entity sınıfıdır. Bankacılık sistemi bir müşteri kaydının oluşturulması ile başlar. 

- ColorfulAccount

- DepositAccount

- CreditCard

DepositAccount ve CreditCard ile **OneToOne** , ColorfulAccount  ile **OneToMany** ilişkilidir.



#### Colorful Account

Müşteriye tanımlı birikim hesabıdır. Bu hesaba ait bir kart yoktur. İlk kez bu hesaptan faydalanan müşteriler hoşgeldin faizinden faydalanabilir. Her bir para birimi için farklı faiz oranları uygulanır.

**Sınıf değişkenleri :**

- id: Her bir hesabın veritabanı tablosundaki unique identifier'ıdır.
- accountNumber: Her bir hesabın sahip olduğu hesap numarasıdır.
- ibanNo: Her bir hesabın sahip olduğu iban numarasıdır.
- balance: Hesaptaki anlık para miktarıdır.
- currency: Hesabın para birimidir. 
- expiryDay: Vade süresidir.

Customer ile **ManyToOne** ilişkilidir.


#### Deposit Account

Müşteriye tanımlı banka hesabıdır. Bu hesaba bir banka kartı tanımlanabilir. Tüm transfer işlemlerine izin verilmektedir.

**Sınıf değişkenleri : **

- id: Her bir hesabın veritabanı tablosundaki unique identifier'ıdır.
- accountNumber: Her bir hesabın sahip olduğu hesap numarasıdır.
- ibanNo: Her bir hesabın sahip olduğu iban numarasıdır.
- balance: Hesaptaki anlık para miktarıdır.


 Customer ve Debit Card ile **OneToOne** ilişkilidir.



#### Debit Card

Müşterinin banka hesabına tanımlanan banka kartıdır. Para yatırma, para çekme, alışveriş işlemleri yapılabilir. Bu değişiklikler banka hesabındaki tutar üzerinden gerçekleşir.

**Sınıf değişkenleri :**

- id: Her bir kartın veritabanı tablosundaki unique identifier'ıdır.
- cardNumber: Her bir kartın sahip olduğu kart numarasıdır.

Deposit Account  sınıfı ile **OneToOne** ilişkilidir.



#### Credit Card

Hesaplara tanımlı olmadan, direkt olarak müşteriye tanımlanabilen kredi kartıdır. Alışveriş işlemleri gerçekleştirilebilir. Borç sorgulama, hesaplardan borç ödeme, bankamatikten borç ödeme, ekstre görüntüleme işlemleri yapılabilir.

**Sınıf değişkenleri :**

- id: Her bir kartın veritabanı tablosundaki unique identifier'ıdır.
- cardNumber: Her bir kartın sahip olduğu kart numarasıdır.
- totalLimit: Kredi kartı için belirlenen üst harcama limitidir.
- currentLimit: Kredi kartında anlık olarak kullanılabilecek kalan limittir.

Customer ile **OneToOne** ilişkilidir.



#### Transaction

Hesap özetinin tutulduğu sınıftır.

**Sınıf değişkenleri :**

- id: Her bir hesap özetinin veritabanı tablosundaki unique identifier'ıdır.
- creditCardNumber: İşlem yapılan kartın numarasıdır.
- amount: İşlem tutarıdır.
- txnType: İşlem türüdür.
- date: İşlemin gerçekleşme tarihidir.