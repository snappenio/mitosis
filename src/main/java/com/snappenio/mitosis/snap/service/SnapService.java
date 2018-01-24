package com.snappenio.mitosis.snap.service;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.repository.SnapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SnapService {

    private final SnapRepository snapRepository;

    @Autowired
    public SnapService(SnapRepository snapRepository) {
        this.snapRepository = snapRepository;
    }

    public Optional<Snap> getSnap(String snapId) {
        return snapRepository.getSnap(snapId);
    }

    public String insertSnap(Codepen codepen) {
        Snap snap = new Snap(codepen.getHtml(), codepen.getCss(), codepen.getJs());
        snapRepository.insertSnap(snap);
        return snap.getSnapId();
    }

}
