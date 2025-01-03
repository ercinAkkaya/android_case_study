# Android Case Study - Eteration

Bu proje, **Eteration** için 2 gün içerisinde geliştirdiğim bir Android uygulaması olan case study'dir. 
Uygulama, favori öğeleri yerel veritabanında saklamak, listelemek, silmek ve sayısını almak gibi temel işlemleri gerçekleştiren bir sistemin prototipini sunmaktadır. 

Proje, **Clean Architecture** ve **MVVM** (Model-View-ViewModel) mimarileri kullanılarak geliştirilmiştir. Bu sayede uygulama daha sürdürülebilir ve test edilebilir hale getirilmiştir.

## 🚀 Proje Özeti

Bu uygulama, öğeleri **Room** veri tabanında yönetir ve REST API istekleri için **Retrofit** kullanır.
Kullanıcı, favori öğeleri ekleyebilir, silebilir ve favori öğelerin sayısını görebilir. 
Uygulama, **MVVM** ve **Clean Architecture** prensiplerine dayalı olarak geliştirilmiştir. 
Bu mimari, uygulamanın sürdürülebilirliğini artırır ve modüler bir yapıda geliştirilmesini sağlar.

## 🛠️ Kullanılan Teknolojiler ve Araçlar

### Programlama Dili
- **Kotlin**: Android uygulama geliştirme için kullanılan ana dil.

### Android Framework
- **Room Database**: Yerel veri yönetimi için kullanılan veritabanı teknolojisi.
- **LiveData**: Verinin UI bileşenlerine bağlanmasını sağlayan ve UI'nı veri değişikliklerine tepki verecek şekilde güncelleyen Android bileşeni.
- **ViewModel**: UI'yi model katmanından ayırarak veri yönetimini sağlayan bileşen.
- **Hilt**: Bağımlılık enjeksiyonu için kullanılan framework.
- **Retrofit**: REST API işlemleri için kullanılan HTTP istemcisi.

### Mimari
- **Clean Architecture**: Uygulamanın modülerliğini ve test edilebilirliğini artırmak için uygulanan mimari.
  - **Presentation Layer**: UI bileşenleri (ViewModel, Activities/Fragments)
  - **Domain Layer**: İş mantığı ve veri işleme (UseCases, Interactors)
  - **Data Layer**: Veri kaynakları (Retrofit API, Room)
  
### Test Teknolojileri
- **JUnit**: Birim testler için kullanılan framework.
- **Mockito**: Mock objeler oluşturmak için kullanılan test yardımcı kütüphanesi.

### Diğer
- **Gradle**: Proje yapılandırması ve bağımlılık yönetimi.


## 💡 Proje Özellikleri

- **Favori Öğeleri Yönetme**: Uygulama, favori öğeleri ekleyip silebilmenizi sağlar. Eklenen öğeler, Room veri tabanında kalıcı olarak saklanır.
- **Favori Öğelerinin Listelemesi**: Kullanıcı, favori öğelerini listeleyebilir. Liste, veri tabanından çekilir.
- **Favori Öğeleri Silme**: Kullanıcı, listede yer alan öğeleri silebilir. Silinen öğe veri tabanından kaldırılır.
- **Favori Öğesi Sayısı**: Kullanıcı, favori öğelerinin toplam sayısını görüntüleyebilir.
- **Sepete Öğeleri Ekleme ve Çıkarma**: Kullanıcı, favori öğeleri sepete ekleyebilir ve sepetten çıkarabilir. Sepet işlemleri, veri tabanında güncellenir.
- **REST API Entegrasyonu**: Uygulama, harici bir REST API'ye bağlanarak favori öğeleri sunucudan alabilir ve sunucuya gönderir. Retrofit ile API istekleri yapılır.
- 

## 🧩 Clean Architecture

Bu projede, **Clean Architecture** kullanılarak uygulama 3 ana katmana ayrılmıştır:

### 1. **Presentation Layer (UI Katmanı)**
Bu katman, kullanıcı ile etkileşimi sağlayan Activity, Fragment ve ViewModel sınıflarını içerir. 
**ViewModel**, UI'den gelen talepleri **UseCase**'lere ileterek iş mantığını başlatır. UI, değişen verileri **LiveData** kullanarak gözlemler.

### 2. **Domain Layer (İş Mantığı Katmanı)**
Domain katmanı, iş mantığını içeren sınıfları barındırır. 
Bu katmanda **UseCase**'ler yer alır. Her bir use case, uygulamanın belirli bir işlevini yerine getirir ve **Repository** katmanını kullanarak veri işlemleri yapar. 
Domain katmanı, UI ve veri katmanı arasında bağımsız bir arayüz sağlar.

### 3. **Data Layer (Veri Katmanı)**
Data katmanı, uygulamanın veriye erişim katmanıdır. Bu katman, **Retrofit** ile REST API'ye bağlanır ve **Room** ile yerel veritabanı işlemlerini yönetir. 
**Repository** sınıfı, veri kaynaklarına erişimi sağlar ve bu veriyi Domain katmanına iletir.


## 🛠️ Kullanılan Teknolojiler

### 1. **Room Database**: 
### 2. **Retrofit**: 
### 3. **Hilt**: 
### 4. **Kotlin Coroutines**: 
### 5. **LiveData**: 
### 6. **ViewModel**: 
### 7. **Jetpack Navigation**: 
### 8. **Material Components**: 
### 9. **Mockito**: 
### 10. **JUnit**: 



