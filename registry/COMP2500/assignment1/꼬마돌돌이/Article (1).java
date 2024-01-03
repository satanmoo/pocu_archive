package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.HashSet;

public class Article {
    private final UUID articleId;
    private final UUID userId;

    private String title;
    private String content;

    private final OffsetDateTime created;
    private OffsetDateTime modified;

    private final HashSet<String> tags;
    private final ArrayList<Comment> comments;
    private final HashMap<ReactionType, HashSet<UUID>> reactions;

    public Article(UUID userId, UUID articleId, String title, String content) {
        assert userId != null;
        assert articleId != null;
        assert title != null;
        assert content != null;

        this.userId = userId;
        this.articleId = articleId;
        this.title = title;
        this.content = content;

        OffsetDateTime now = OffsetDateTime.now();
        this.created = now;
        this.modified = now;

        this.tags = new HashSet<>();
        this.comments = new ArrayList<>();
        this.reactions = new HashMap<>();
        for (ReactionType reactionType : ReactionType.values()) {
            reactions.put(reactionType, new HashSet<>());
        }

    }

    boolean isEqual(Article article) {
        assert article != null : "article must be valid";

        return this.articleId.equals(article.articleId);
    }

    boolean isSameUser(UUID userId) {
        assert userId != null : "userId must be valid";

        return this.userId.equals(userId);
    }

    boolean isTagInclude(ArrayList<String> tags) {
        assert tags != null : "tags must be valid";

        for (String tag : tags) {
            assert tag != null : "each tag must be valid";
            if (this.tags.contains(tag)) {
                return true;
            }
        }

        return false;
    }

    private void updateModifiedDate() {
        this.modified = OffsetDateTime.now();
    }

    public void addTag(UUID userId, String tag) {
        assert userId != null;
        assert tag != null;

        if (!this.userId.equals(userId)) {
            return;
        }

        if (!this.tags.contains(tag)) {
            tags.add(tag);
            updateModifiedDate();
        }
    }

    public void editTitle(UUID userId, String title) {
        assert userId != null;
        assert title != null;

        if (this.userId.equals(userId)) {
            this.title = title;
            updateModifiedDate();
        }
    }

    public void editContent(UUID userId, String content) {
        assert userId != null;
        assert content != null;

        if (this.userId.equals(userId)) {
            this.content = content;
            updateModifiedDate();
        }
    }

    public void addComment(Comment comment) {
        assert comment != null : "comment != null";

        this.comments.add(comment);
    }

    public void addReaction(UUID userId, ReactionType reactionType) {
        assert userId != null;
        assert reactionType != null;

        reactions.get(reactionType).add(userId);
    }

    public void removeReaction(UUID userId, ReactionType reactionType) {
        assert userId != null;
        assert reactionType != null;

        reactions.get(reactionType).remove(userId);
    }

    public UUID getUser() {
        return this.userId;
    }

    public UUID getArticleId() {
        return this.articleId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public OffsetDateTime getCreated() {
        return this.created;
    }

    public OffsetDateTime getModified() {
        return this.modified;
    }

    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<>();

        for (String s : this.tags) {
            tags.add(s);
        }
        return tags;
    }

    public ArrayList<Comment> getComments() {
        int size = comments.size();
        ArrayList<Comment> result = new ArrayList<>(comments.size());

        for (int i = 0; i < size; ++i) {
            result.add(comments.get(i));
        }

        result.sort((o1, o2) -> -(o1.getVoteRate() - o2.getVoteRate()));

        return result;
    }

    public int getReaction(ReactionType reactionType) {
        assert reactionType != null;

        return reactions.get(reactionType).size();
    }


}
