package academy.pocu.comp2500.lab10;

import academy.pocu.comp2500.lab10.pocuflix.User;

public class Request {
    private String movieTitle;
    private User user;

    public Request(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Request) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Request other = (Request) obj;
        if (user == null && other.getUser() == null) {
            return this.movieTitle.equals(other.movieTitle);
        }
        if (user == null || other.getUser() == null) {
            return false;
        }
        return this.user.equals(other.user) && this.movieTitle.equals(other.movieTitle);
    }

    @Override
    public int hashCode() {
        if (user == null) {
            return this.movieTitle.hashCode();
        }
        return this.movieTitle.hashCode() ^ (this.user.hashCode() << 16);
    }
}
