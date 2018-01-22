package com.snappenio.mitosis.snap.repository.impl;

import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.repository.SnapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoSnapRepository implements SnapRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoSnapRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Snap> getSnap(String snapId) {
        Snap snap = mongoTemplate.findById(snapId, Snap.class);
        if (snap != null) {
            return Optional.of(snap);
        }
        return Optional.empty();
    }

    @Override
    public void insertSnap(Snap snap) {
        mongoTemplate.insert(snap);
    }

}
