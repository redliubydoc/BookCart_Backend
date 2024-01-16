package com.raj.ms.product.dto.response;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class ProductResponseData {

  private String isbn;
  private String title;
  private String author;
  private int edition;
  private LanguageResponseData language ;
  private List<GenreResponseData> genres;
  private String description;
  private Date dateOfRelease;
  private double price;
  private byte[] thumbnail;

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getEdition() {
    return edition;
  }

  public void setEdition(int edition) {
    this.edition = edition;
  }

  public LanguageResponseData getLanguage() {
    return language;
  }

  public void setLanguage(LanguageResponseData language) {
    this.language = language;
  }

  public List<GenreResponseData> getGenres() {
    return genres;
  }

  public void setGenres(List<GenreResponseData> genres) {
    this.genres = genres;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDateOfRelease() {
    return dateOfRelease;
  }

  public void setDateOfRelease(Date dateOfRelease) {
    this.dateOfRelease = dateOfRelease;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public byte[] getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(byte[] thumbnail) {
    this.thumbnail = thumbnail;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ProductResponseData.class.getSimpleName() + "[", "]")
      .add("isbn='" + isbn + "'")
      .add("title='" + title + "'")
      .add("author='" + author + "'")
      .add("edition=" + edition)
      .add("language=" + language)
      .add("genres=" + genres)
      .add("description='" + description + "'")
      .add("dateOfRelease=" + dateOfRelease)
      .add("price=" + price)
      .add("thumbnail='" + thumbnail + "'")
      .toString();
  }
}
