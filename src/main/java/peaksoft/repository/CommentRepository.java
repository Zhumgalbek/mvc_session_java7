package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Comment;
import peaksoft.model.House;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Comment> getAllComments(Long id) {
        return entityManager.
                createQuery("select c from Comment c where c.house.id = : id", Comment.class)
                .setParameter("id", id).getResultList();
    }

    public void saveCommentToHouse(Long id, Comment comment) {
        House house = entityManager.find(House.class, id);
        house.addComment(comment);
        comment.setHouse(house);
        entityManager.merge(house);
    }
}
