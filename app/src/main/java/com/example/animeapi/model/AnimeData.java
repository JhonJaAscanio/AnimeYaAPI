package com.example.animeapi.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimeData implements Serializable {
        private int mal_id;
        private String url;
        private  Images images;
        private boolean approved;
        private String title;
        private String title_english;
        private String title_japanese;
        private ArrayList<String> title_synonyms;
        private String type;
        private String source;
        private int episodes;
        private String status;
        private boolean airing;
        private String duration;
        private String rating;
        private double  score;
        private int scored_by;
        private int rank;
        private int popularity;
        private int members;
        private int favorites;
        private String synopsis;
        private String background;
        private String season;
        private int year;

    public Images getImages () {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public static class Images implements Serializable{
        private Image jpg;
        private Image webp;

        public Image getJpg() {
            return jpg;
        }

        public void setJpg(Image jpg) {
            this.jpg = jpg;
        }

        public Image getWebp() {
            return webp;
        }

        public void setWebp(Image webp) {
            this.webp = webp;
        }
    }

    public static class Image implements Serializable{
        private String image_url;
        private String small_image_url;
        private String large_image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getSmall_image_url() {
            return small_image_url;
        }

        public void setSmall_image_url(String small_image_url) {
            this.small_image_url = small_image_url;
        }

        public String getLarge_image_url() {
            return large_image_url;
        }

        public void setLarge_image_url(String large_image_url) {
            this.large_image_url = large_image_url;
        }
    }



    public int getMal_id() {
        return mal_id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle_japanese() {
        return title_japanese;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getStatus() {
        return status;
    }

    public String getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public double getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getSeason() {
        return season;
    }

    public int getYear() {
        return year;
    }

    public String getBackground() {
        return background;
    }
}


