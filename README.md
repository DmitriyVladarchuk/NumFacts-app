# NumFacts

Мобильное приложение, предоставляющее интересные факты о числах, датах и годах. Позволяет получать случайные факты или находить информацию о конкретных числах по выбору пользователя

## 📌 Возможности

- 🔢 Получение случайных фактов о числах
- 📅 Поиск фактов по конкретным числам, датам, годам и разные
- 💾 Сохранение понравившихся фактов в избранное
- 🌙 Поддержка светлой и темной темы
- 🔄 Разные категории фактов: математика, даты, годы, разные

## 📸 Скриншоты

### Светлая тема

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center;">
  <img src="img/home_light.jpg" alt="Скриншот домашней страницы, светлая тема" width="200">
  <img src="img/favorite_light.jpg" alt="Скриншот сохраненых фактов, светлая тема" width="200">
  <img src="img/settings_light.jpg" alt="Скриншот настроек, светлая тема" width="200">
</div>

### Темная тема

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center;">
  <img src="img/home_dark.jpg" alt="Скриншот домашней страницы, темная тема" width="200">
  <img src="img/favorite_dark.jpg" alt="Скриншот сохраненых фактов, темная тема" width="200">
  <img src="img/settings_dark.jpg" alt="Скриншот настроек, темная тема" width="200">
</div>

## 🛠 Технологии

- **Язык программирования**: Kotlin
- **UI Framework**: Jetpack Compose
- **Навигация**: Compose Navigation
- **Локальное хранилище**: 
  - Room (для избранных фактов)
  - SharedPreferences (для настроек приложения)
- **DI**: Dagger-Hilt
- **Сеть**: 
  - Retrofit2 
  - OkHttp3 
- **Асинхронность**: Kotlin Coroutines
- **API**: [Numbers API](http://numbersapi.com)
