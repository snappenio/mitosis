package com.snappenio.mitosis.repository.impl;

import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.repository.impl.MongoSnapRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MongoSnapRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    private MongoSnapRepository mongoSnapRepository;

    @Before
    public void setup() {
        mongoSnapRepository = new MongoSnapRepository(mongoTemplate);
    }

    @Test
    public void testGetSnap() {
        Snap snap = new Snap("html", "css", "js");

        when(mongoTemplate.findById("1", Snap.class)).thenReturn(snap);

        Optional<Snap> result = mongoSnapRepository.getSnap("1");

        assertTrue(result.isPresent());
        assertEquals(result.get(), snap);
    }

    @Test
    public void testGetSnapWithNoExistingSnapId() {
        when(mongoTemplate.findById("1", Snap.class)).thenReturn(null);

        Optional<Snap> result = mongoSnapRepository.getSnap("1");

        assertFalse(result.isPresent());
        assertEquals(result, Optional.empty());
    }

}
