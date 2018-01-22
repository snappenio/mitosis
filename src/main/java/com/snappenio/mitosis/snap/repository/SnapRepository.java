package com.snappenio.mitosis.snap.repository;

import com.snappenio.mitosis.snap.model.Snap;

import java.util.Optional;

public interface SnapRepository {

    Optional<Snap> getSnap(String snapId);

    void insertSnap(Snap snap);

}
