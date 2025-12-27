# Bible Reading Tracker

A minimalist web application to track Bible reading progress.

## Features

- Track reading progress from Genesis 1 to Revelation 22
- Multiple users support
- Shows current chapter to read
- Mark chapters as read with date logging
- View reading progress history
- Data stored in CSV file

## Tech Stack

- **Backend**: Java Spring Boot 3.2
- **Frontend**: HTML, CSS, JavaScript (Thymeleaf)
- **Storage**: CSV file

## Requirements

- Java 17 or higher
- Maven 3.6+

## Running the Application

```bash
cd bible-tracker
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

Then open http://localhost:8080 in your browser.

## Usage

1. Click the **+** button to add a new reader
2. Select your name from the dropdown
3. The current book and chapter to read will display
4. Click **Mark as Read** to log your progress
5. Click **See Progress** to view all chapters you've read

## Data Storage

Reading progress is stored in `reading_progress.csv` in the project root directory with the following format:
```
name,book,chapter,date_read
```
