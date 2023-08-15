package academy.pocu.comp2500.assignment1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Comment {
    private final UUID commentId;
    private final UUID userId;

    private String content;

    private final OffsetDateTime created;
    private OffsetDateTime modified;

    private final HashMap<UUID, VOTE> votes;
    private final ArrayList<Comment> subComments;

    private enum VOTE {
        UPVOTE,
        DOWNVOTE
    }

    public Comment(UUID userId, UUID commentId, String content) {
        assert userId != null;
        assert commentId != null;
        assert content != null;

        this.commentId = commentId;
        this.userId = userId;
        this.content = content;

        OffsetDateTime now = OffsetDateTime.now();
        this.created = now;
        this.modified = now;

        this.votes = new HashMap<UUID, VOTE>();
        this.subComments = new ArrayList<>();

    }

    boolean isEqual(Comment comment) {
        assert comment != null : "comment must be valid";
        return this.commentId.equals(comment.commentId);
    }

    boolean isSameUser(UUID userId) {
        assert userId != null : "user must be valid";
        return this.userId.equals(userId);
    }

    int getVoteRate() {
        int voteRate = 0;

        for (UUID uuid : votes.keySet()) {
            voteRate += votes.get(uuid) == VOTE.UPVOTE ? 1 : -1;
        }

        return voteRate;
    }

    public void updateModified() {
        this.modified = OffsetDateTime.now();
    }

    public void editContent(UUID userId, String content) {
        assert userId != null;
        assert content != null;

        if (this.userId.equals(userId)) {
            this.content = content;
            updateModified();
        }

    }

    public void upVote(UUID userId) {
        assert userId != null;

        this.votes.put(userId, VOTE.UPVOTE);
    }
    public void downVote(UUID userId) {
        assert userId != null;

        this.votes.put(userId, VOTE.DOWNVOTE);
    }
    public void addSubComment(Comment comment) {
        assert comment != null : "comment != null";

        this.subComments.add(comment);
    }

    public UUID getUserId() {
        return this.userId;
    }

    public UUID getCommentId() {
        return this.commentId;
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

    public int getUpVoteCount() {
        int upVoteCount = 0;
        for (UUID uuid : votes.keySet()) {
            if (votes.get(uuid) == VOTE.UPVOTE) {
                ++upVoteCount;
            }
        }
        return upVoteCount;
    }

    public int getDownVoteCount() {
        int downVoteCount = 0;
        for (UUID uuid : votes.keySet()) {
            if (votes.get(uuid) == VOTE.DOWNVOTE) {
                ++downVoteCount;
            }
        }
        return downVoteCount;
    }

    public ArrayList<Comment> getSubComments() {
        int size = this.subComments.size();
        ArrayList<Comment> result = new ArrayList<>(size);

        for (int i = 0; i < size; ++i) {
            result.add(this.subComments.get(i));
        }

        result.sort((o1, o2) -> -(o1.getVoteRate() - o2.getVoteRate()));

        return result;
    }

}
