// Blog.java
package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Blog {
    private final List<Post> posts = new ArrayList<>();
    private String authorFilter;
    private final List<String> tagFilter = new ArrayList<>();
    private SortingType sortingType = SortingType.CREATED_AT_DESC;

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public List<Post> getPosts() {
        return this.posts.stream()
                .filter(this::filterByTag)
                .filter(this::filterByAuthor)
                .sorted(this.sortingByType())
                .collect(Collectors.toList());
    }

    public void setAuthorFilter(String authorOrNull) {
        this.authorFilter = authorOrNull;
    }

    public void setTagFilter(String tagOrNull) {
        if (tagOrNull == null) {
            this.tagFilter.clear();
        } else {
            this.tagFilter.add(tagOrNull);
        }
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    private boolean filterByAuthor(Post post) {
        if (this.authorFilter == null) {
            return true;
        }
        return post.getAuthor().equals(this.authorFilter);
    }

    private boolean filterByTag(Post post) {
        if (this.tagFilter.isEmpty()) {
            return true;
        }
        return !Collections.disjoint(post.getTags(), this.tagFilter);
    }

    private Comparator<Post> sortingByType() {
        switch (this.sortingType) {
            case CREATED_AT_DESC:
                return Comparator.comparing(Post::getCreatedAt).reversed();
            case CREATED_AT_ASC:
                return Comparator.comparing(Post::getCreatedAt);
            case UPDATED_AT_DESC:
                return Comparator.comparing(Post::getUpdatedAt).reversed();
            case UPDATED_AT_ASC:
                return Comparator.comparing(Post::getUpdatedAt);
            case TITLE_ASC:
                return Comparator.comparing(Post::getTitle);
        }

        return Comparator.comparing(Post::getCreatedAt).reversed();
    }
}

// Comment.java
package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int upvote = 0;
    private int downvote = 0;
    private final String author;
    private String content;
    private final List<Comment> subComments = new ArrayList<>();

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public int getUpvote() {
        return this.upvote;
    }

    public int getDownvote() {
        return this.downvote;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public void addSubComment(Comment comment) {
        this.subComments.add(comment);
    }

    public List<Comment> getSubComments() {
        return this.subComments;
    }

    public void updateComment(String content, String author) {
        if (this.author.equals(author)) {
            this.content = content;
        }
    }

    public void upvote() {
        this.upvote++;
    }

    public void downvote() {
        this.downvote--;
    }
}

// Post.java
package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private final List<String> tags = new ArrayList<>();
    private final String author;
    private final OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String title;
    private String content;
    private final List<Comment> comments = new ArrayList<>();
    private final int[] reactions = new int[ReactionType.values().length];

    public Post(String author, String title, String content) {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getAuthor() {
        return this.author;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void updateTitle(String title, String author) {
        if (this.author.equals(author)) {
            this.title = title;
            this.updatedAt = OffsetDateTime.now();
        }
    }

    public void updateContent(String content, String author) {
        if (this.author.equals(author)) {
            this.content = content;
            this.updatedAt = OffsetDateTime.now();
        }
    }

    public void addTag(String tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public List<Comment> getComments() {
        return this.getSortedComments();
    }

    public List<Comment> getSortedComments() {
        this.comments.sort((a, b) -> (b.getUpvote() - b.getDownvote()) - (a.getUpvote() - a.getDownvote()));
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addReaction(ReactionType reactionType) {
        this.reactions[reactionType.ordinal()]++;
    }

    public void removeReaction(ReactionType reactionType) {
        this.reactions[reactionType.ordinal()]--;
    }

    public int getReaction(ReactionType reactionType) {
        return this.reactions[reactionType.ordinal()];
    }
}

// ReactionType.java
package academy.pocu.comp2500.assignment1;

public enum ReactionType {
    GREAT,
    SAD,
    ANGRY,
    FUN,
    LOVE
}

// SortingType.java
package academy.pocu.comp2500.assignment1;

public enum SortingType {
    CREATED_AT_DESC,
    CREATED_AT_ASC,
    UPDATED_AT_DESC,
    UPDATED_AT_ASC,
    TITLE_ASC,
}
