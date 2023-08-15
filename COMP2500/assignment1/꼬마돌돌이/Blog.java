package academy.pocu.comp2500.assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Blog {
    private final UUID blogId;
    private final UUID userId;

    private final ArrayList<Article> articles;

    private final HashSet<String> tagFilter;
    private UUID authorIdFilter;
    private SortingType sortingType;

    public Blog(UUID userId, UUID blogId) {
        assert blogId != null;
        assert userId != null;

        this.blogId = blogId;
        this.userId = userId;
        this.articles = new ArrayList<>();

        this.tagFilter = new HashSet<>();
        this.authorIdFilter = null;
        this.sortingType = SortingType.CREATED_DESC;
    }

    public void setTagFilter(ArrayList<String> tags) {
        // pre-cond : not allow NULL tag
        assert tags != null;

        tagFilter.clear();

        if (tags.size() == 0) {
            return;
        }

        for (String tag : tags) {
            assert tag != null;
            tagFilter.add(tag);
        }

    }

    public void setAuthorFilter(UUID authorId) {
        assert authorId != null;
        this.authorIdFilter = authorId;
    }

    public void setSortingType(SortingType sortingType) {
        assert sortingType != null;
        this.sortingType = sortingType;
    }

    public void resetAuthorFilter() {
        this.authorIdFilter = null;
    }

    public void resetSortType() {
        this.sortingType = SortingType.CREATED_DESC;
    }

    public void addArticle(Article article) {
        assert article != null : "article != null";
        articles.add(article);
    }

    public ArrayList<Article> getArticles() {
        ArrayList<Article> result = new ArrayList<>(articles);

        if (!tagFilter.isEmpty()) {
            ArrayList<String> tags = new ArrayList<>(tagFilter);
            result.removeIf(article -> !article.isTagInclude(tags));
            tags.clear();
        }

        if (authorIdFilter != null) {
            result.removeIf(article -> article.isSameUser(authorIdFilter) == false);
        }

        switch (sortingType) {
            case CREATED_DESC:
                result.sort((o1, o2) -> -o1.getCreated().compareTo(o2.getCreated()));
                break;
            case CREATED_ASC:
                result.sort((o1, o2) -> o1.getCreated().compareTo(o2.getCreated()));
                break;
            case MODIFIED_DESC:
                result.sort((o1, o2) -> -o1.getModified().compareTo(o2.getModified()));
                break;
            case MODIFIED_ASC:
                result.sort((o1, o2) -> o1.getModified().compareTo(o2.getModified()));
                break;
            case TITLE_ALPHA_ASC:
                result.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                break;
            default:
                assert false : "invalid sortingType";
                break;
        }

        return result;
    }

    public UUID getBlogId() {
        return this.blogId;
    }

    public UUID getUserId() {
        return this.userId;
    }
}
