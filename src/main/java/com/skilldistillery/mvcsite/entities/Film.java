package com.skilldistillery.mvcsite.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;



public class Film {
	private int id;

	private String title;

	private String rating;

	private Integer releaseYear;

	private Integer languageId;

	private String language;

	private String category;

	private String description;

	private Integer length;

	private List<Actor> cast = null;

	private Set<String> specialFeatures;

	private Integer rentalDuration;

	private Double rental_rate;

	private Double replacementCost;

	public Film(int id, String title, String description, Integer releaseYear, Integer languageId, Integer rentalDuration,
			Double rental_rate, Integer length, Double replacementCost, String rating, Set<String> specialFeatures,
			String language) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.language = language;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Film() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(Double rental_rate) {
		this.rental_rate = rental_rate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		if(specialFeatures == null || specialFeatures.size() == 0)
			return "";
		
		String res = specialFeatures.toString();
		res = res.replace("[", "");
		res = res.replace("]", "");
		
		return res;
	}

	public void setSpecialFeatures(Set<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getCast() {
		return cast;
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public List<Actor >setActors(List<Actor> actors) {
		return this.cast = actors;
	}

	public void basicDisplay() {
		System.out.printf("%s(%s) %s Language: %s\n", title, rating, releaseYear, language);
		System.out.println("Description: " + description);
		System.out.printf("Cast: %s\n\n", cast);
	}

	public void detailedView() {
		System.out.println("\n------ Detailed View -------");
		String res = title + "(" + rating + ")" + "id=" + id + " releaseYear=" + releaseYear + ", languageId="
				+ languageId + ", language=" + language + ", category=" + category + ", length=" + length
				+ "\ndescription:" + description + "\ncast: " + cast + "\nspecialFeatures: " + specialFeatures
				+ "\nRental Details: " + "rentalDuration=" + rentalDuration + ", rental_rate=" + rental_rate
				+ ", replacementCost=" + replacementCost + "\n" + "----------------------------";

		System.out.println(res);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", rating=" + rating + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", language=" + language + ", category=" + category + ", description="
				+ description + ", length=" + length + ", cast=" + cast + ", specialFeatures=" + specialFeatures
				+ ", rentalDuration=" + rentalDuration + ", rental_rate=" + rental_rate + ", replacementCost="
				+ replacementCost + "]";
	}

}
