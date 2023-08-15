// Blog.java
package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class Blog {
    private final ArrayList<Post> posts = new ArrayList<>();
    private SortingType sortingType = SortingType.DEFAULT_ORDER;
    private UUID authorIdForFilterOrNull;
    private String[] tagsForFilter = {};

    public void createPost(Post post) {
        posts.add(post);
    }

    private void sortPosts(ArrayList<Post> posts) {
        switch (sortingType) {
            case DEFAULT_ORDER:
            case CREATED_ASCENDING_ORDER:
                posts.sort(Comparator.comparing(Post::getCreatedDateTime));
                break;
            case CREATED_DESCENDING_ORDER:
                posts.sort((post1, post2) -> post2.getCreatedDateTime().compareTo(post1.getCreatedDateTime()));
                break;
            case MODIFIED_ASCENDING_ORDER:
                posts.sort(Comparator.comparing(Post::getModifiedDateTime));
                break;
            case MODIFIED_DESCENDING_ORDER:
                posts.sort((post1, post2) -> post2.getModifiedDateTime().compareTo(post1.getModifiedDateTime()));
                break;
            case TITLE_ASCENDING_ORDER:
                posts.sort(Comparator.comparing(Post::getTitle));
                break;
            default:
                assert false;
        }
    }

    public void setAuthorIdFilter(UUID userIdOrNull) {
        authorIdForFilterOrNull = userIdOrNull;
    }

    public void setTagFilter(String[] tags) {
        tagsForFilter = tags;
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    public ArrayList<Post> getPosts() {
        if (authorIdForFilterOrNull == null && tagsForFilter.length == 0) {
            sortPosts(posts);
            return posts;
        }
        ArrayList<Post> filteredPosts = filterPosts();
        sortPosts(filteredPosts);
        return filteredPosts;
    }

    private ArrayList<Post> filterPosts() {
        ArrayList<Post> filteredPosts = new ArrayList<>();

        for (Post post : posts) {
            if (tagsForFilter.length == 0) {
                if (post.getAuthorId().equals(authorIdForFilterOrNull)) {
                    filteredPosts.add(post);
                }
                continue;
            }
            if (authorIdForFilterOrNull == null) {
                for (String tag : tagsForFilter) {
                    if (post.getTags().contains(tag)) {
                        filteredPosts.add(post);
                        break;
                    }
                }
                continue;
            }
            for (String tag : tagsForFilter) {
                if (post.getTags().contains(tag) && post.getAuthorId().equals(authorIdForFilterOrNull)) {
                    filteredPosts.add(post);
                }
            }
        }
        return filteredPosts;
    }
}

// Post.java
package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Post {
    private final UUID authorId;
    private String title;
    private String content;
    private final HashSet<String> tags = new HashSet<>();
    private final ArrayList<Comment> comments = new ArrayList<>();
    private final ArrayList<Reaction> reactions = new ArrayList<>();
    private final OffsetDateTime createdDateTime = OffsetDateTime.now();
    private OffsetDateTime modifiedDateTime = createdDateTime;

    public Post(UUID userId, String title, String content) {
        this.authorId = userId;
        this.title = title;
        this.content = content;
    }

    public boolean updateTitleIfChanged(UUID userId, String title) {
        if (!authorId.equals(userId) || this.title.equals(title)) {
            return false;
        }
        this.title = title;
        modifiedDateTime = OffsetDateTime.now();
        return true;
    }

    public boolean updateContentIfChanged(UUID userId, String content) {
        if (!authorId.equals(userId) || this.content.equals(content)) {
            return false;
        }
        this.content = content;
        modifiedDateTime = OffsetDateTime.now();
        return true;
    }

    public boolean addTag(UUID userId, String tag) {
        if (!authorId.equals(userId) || tags.contains(tag)) {
            return false;
        }
        tags.add(tag);
        modifiedDateTime = OffsetDateTime.now();
        return true;
    }

    public boolean addReaction(UUID userId, ReactionType reactionType) {
        for (Reaction reaction : reactions) {
            if (userId.equals(reaction.getReactor()) && reactionType.equals(reaction.getReactionType())) {
                return false;
            }
        }
        Reaction reaction = new Reaction(userId, reactionType);
        reactions.add(reaction);
        return true;
    }

    public boolean removeReaction(UUID userId, ReactionType reactionType) {
        for (Reaction reaction : reactions) {
            if (userId.equals(reaction.getReactor()) && reactionType.equals(reaction.getReactionType())) {
                reactions.remove(reaction);
                return true;
            }
        }
        return false;
    }

    public OffsetDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public OffsetDateTime getModifiedDateTime() {
        return modifiedDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        comments.sort((comment1, comment2) -> comment2.getVote() - comment1.getVote());
        return comments;
    }

    public ArrayList<Reaction> getReactions() {
        return reactions;
    }

    public int getReactionCount(ReactionType reactionType) {
        int count = 0;
        for (Reaction reaction : reactions) {
            if (reaction.getReactionType().equals(reactionType)) {
                ++count;
            }
        }
        return count;
    }
}

// Comment.java
package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private final UUID commenterId;
    private int vote;
    private String content;
    private final ArrayList<Comment> comments = new ArrayList<>();

    public Comment(UUID userId, String content) {
        commenterId = userId;
        this.content = content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public boolean updateCommentIfChanged(UUID userId, String content) {
        if (!this.commenterId.equals(userId) || this.content.equals(content)) {
            return false;
        }
        this.content = content;
        return true;
    }

    public UUID getCommenterId() {
        return commenterId;
    }

    public void upvote() {
        ++vote;
    }

    public void downvote() {
        --vote;
    }

    public int getVote() {
        return vote;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Comment> getComments() {
        comments.sort((comment1, comment2) -> comment2.getVote() - comment1.getVote());
        return comments;
    }
}

// Reaction.java
package academy.pocu.comp2500.assignment1;

import java.util.UUID;

public class Reaction {
    private final UUID reactor;
    private final ReactionType reactionType;

    public Reaction(UUID userId, ReactionType reactionType) {
        this.reactionType = reactionType;
        this.reactor = userId;
    }

    public UUID getReactor() {
        return reactor;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }
}

// ReactionType.java
package academy.pocu.comp2500.assignment1;

public enum ReactionType {
    GREAT,
    SAD,
    ANGRY,
    FUN,
    LOVE,
}

// SortingType.java
package academy.pocu.comp2500.assignment1;

public enum SortingType {
    DEFAULT_ORDER,
    CREATED_ASCENDING_ORDER,
    CREATED_DESCENDING_ORDER,
    MODIFIED_ASCENDING_ORDER,
    MODIFIED_DESCENDING_ORDER,
    TITLE_ASCENDING_ORDER,
}
