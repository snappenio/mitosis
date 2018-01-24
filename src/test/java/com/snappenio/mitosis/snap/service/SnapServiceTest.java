package com.snappenio.mitosis.snap.service;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.repository.SnapRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SnapServiceTest {

    @Mock
    private SnapRepository snapRepository;

    private SnapService snapService;

    @Before
    public void setup() {
        snapService = new SnapService(snapRepository);
    }

    @Test
    public void testGetSnap() {
        Snap snap = new Snap("html", "css", "js");
        when(snapRepository.getSnap("1")).thenReturn(Optional.of(snap));

        Optional<Snap> snapOptional = snapService.getSnap("1");

        assertTrue(snapOptional.isPresent());
        assertEquals(snapOptional.get(), snap);
    }

    @Test
    public void testGetNotExistingSnap() {
        when(snapRepository.getSnap("1")).thenReturn(Optional.empty());

        Optional<Snap> snapOptional = snapService.getSnap("1");

        assertFalse(snapOptional.isPresent());
    }

    @Test
    public void testInsertSnap() {
        Codepen codepen = new Codepen("html", "css", "js");

        snapService.insertSnap(codepen);

        verify(snapRepository, times(1)).insertSnap(any());
    }

}
